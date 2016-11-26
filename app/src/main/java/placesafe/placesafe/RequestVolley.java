package placesafe.placesafe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
    private final String URI = "https://www.curiosity.com.mx";

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

    public void requestString(String method,String path, Response.Listener<String> listener){
        Toast.makeText(context,method + " - "+ path,Toast.LENGTH_LONG).show();
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

    public void requestImage(String imageHttp,ImageView img){
        URL imageUrl = null;
        Bitmap loadedImage;
        try {
            imageUrl = new URL(imageHttp);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();
            loadedImage = BitmapFactory.decodeStream(conn.getInputStream());
            img.setImageBitmap(loadedImage);
        } catch (IOException e) {
            Toast.makeText(context, "Error cargando la imagen: "+e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }



}
