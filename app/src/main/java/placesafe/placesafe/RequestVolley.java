package placesafe.placesafe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gerson on 25/11/16.
 */
public class RequestVolley {
    private static RequestVolley _rv = null;
    private Context context;
    private final String URI = "http://placesafe.curiosity.com.mx";

    private HashMap<String, Integer> methods = new HashMap<String, Integer>();
    private RequestVolley(Context context){
        this.context = context;
        initMethods();
    }

    public static RequestVolley getInstance(Context context){
        _rv = (_rv == null) ? new RequestVolley(context) : _rv;
        return _rv;
    }

    private void initMethods(){
        methods.put("GET",Request.Method.GET);
        methods.put("POST",Request.Method.POST);
        methods.put("HEAD",Request.Method.HEAD);
        methods.put("DELETE",Request.Method.DELETE);
        methods.put("PUT",Request.Method.PUT);

    }

    public void requestString(String method,String path, Response.Listener listener){
        StringRequest stringRequest = new StringRequest(methods.get(method),URI + path ,
                listener,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return null;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void requestString(String method,String path, Response.Listener listener, final HashMap<String,String> params){
        StringRequest stringRequest = new StringRequest(methods.get(method),URI + path ,
                listener,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
    public void requestString(String method,String path, Response.Listener listener,Response.ErrorListener error){
        StringRequest stringRequest = new StringRequest(methods.get(method),URI + path ,
                listener,error){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return null;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void requestString(String method,String path, Response.Listener listener,Response.ErrorListener errorListener, final HashMap<String,String> params){
        StringRequest stringRequest = new StringRequest(methods.get(method),URI + path ,
                listener,
                errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void requestArray(String method,String path, Response.Listener listener, final HashMap<String,String> params){
        final JsonArrayRequest jsonArrayRequest = new
                JsonArrayRequest(methods.get(method),URI + path,
                        new com.android.volley.Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray response) {
                                Log.d("TAG", response.toString());

                                try {
                                    for(int i=0;i<response.length();i++){
                                        JSONObject jresponse = response.getJSONObject(i);
                                        String nickname = jresponse.getString("nickname");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                //pDialog.dismiss();

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {

                            @Override
                            public String getBodyContentType() {
                                return "application/json; charset=utf-8";
                            }

                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                return params;
                            }


                    };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
    }


    public void requestArray(String method,String path, Response.Listener listener){
        final JsonArrayRequest jsonArrayRequest = new
                JsonArrayRequest(methods.get(method),URI + path,
                        listener, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {

                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        return null;
                    }


                };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
    }


    public void requestImage(String IMAGE_URL,NetworkImageView mNetworkImageView){
        ImageLoader mImageLoader;


// Get the ImageLoader through your singleton class.
        mImageLoader = SingletonVolley.getInstance(context).getImageLoader();

// Set the URL of the image that should be loaded into this view, and
// specify the ImageLoader that will be used to make the request.
        mNetworkImageView.setImageUrl(IMAGE_URL, mImageLoader);
    }



}
