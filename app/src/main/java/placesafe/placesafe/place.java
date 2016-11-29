package placesafe.placesafe;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gerson on 29/11/16.
 */
public class place extends Activity {

    ImageButton btnBack;
    Button btnComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_lugar);

        btnBack = (ImageButton) findViewById(R.id.back);
        btnComment = (Button) findViewById(R.id.send_comment);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comment();
            }
        });

        Resources res = getResources();

        TabHost tabs = (TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("comentarios");
        spec.setContent(R.id.comentarios);
        spec.setIndicator("Opiniones", res.getDrawable(android.R.drawable.ic_dialog_dialer));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("comentar");
        spec.setContent(R.id.comentar);
        spec.setIndicator("Opinar", res.getDrawable(android.R.drawable.ic_dialog_dialer));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("reaccionar");
        spec.setContent(R.id.reaccionar);
        spec.setIndicator("Reaccionar", res.getDrawable(android.R.drawable.ic_menu_edit));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        RecyclerView rv = (RecyclerView) findViewById(R.id.opinionesList);
        LinearLayoutManager lym = new LinearLayoutManager(this);
        rv.setLayoutManager(lym);

        List<Opinion> opinions = new ArrayList<>();

        AdapterOpinions adapterOpinions = new AdapterOpinions(fillPlaces(opinions));
        rv.setAdapter(adapterOpinions);


    }

    public List<Opinion> fillPlaces(final List<Opinion> opinions){
        RequestVolley req = RequestVolley.getInstance(getApplicationContext());
        HashMap<String, String> data = new HashMap<>();
        //final TextView opinion = (TextView) findViewById(R.id.opinionText);
        //data.put("opinionText", String.valueOf(opinion.getText()));
        req.requestString("POST", "/opinions", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject obj;
                try {
                    obj = new JSONObject(response);
                    Toast.makeText(place.this,response, Toast.LENGTH_LONG).show();
                    opinions.add(new Opinion("Roger", "Esto esta de pelos"));
                    opinions.add(new Opinion("Gerson","La verdad no es la gran cosa"));
                    //for(int i = 0; i < obj.length(); i++){

                    //}
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return opinions;
    }

    protected void back(){
        final Intent intentMap = new Intent(getApplicationContext(), Map.class);
        startActivity(intentMap);
    }

    protected void comment(){
        RequestVolley req = RequestVolley.getInstance(getApplicationContext());
        HashMap<String, String> data = new HashMap<>();
        data.put("Comment", "Nuevo Comentario");
        req.requestString("POST", "/commentNew", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(place.this, "Hola", Toast.LENGTH_LONG).show();
//                JSONObject obj;
//                try {
//                    obj = new JSONObject(response);
//                    Toast.makeText(place.this, obj.toString(), Toast.LENGTH_LONG).show();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }, data);
    }
}
