package placesafe.placesafe;

import android.app.Application;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gerson on 30/11/16.
 */
public class OpinionController {
    public static void saveOpinion(final Application app,HashMap<String,String> data){
        RequestVolley req = RequestVolley.getInstance(app.getApplicationContext());
        req.requestString("POST", "/saveOpinion", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("OK")) {
                    Toast.makeText(app.getApplicationContext(), "Gracias por tu opinion", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(app.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                }
            }
        }, data);
    }
}
