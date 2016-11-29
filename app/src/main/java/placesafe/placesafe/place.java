package placesafe.placesafe;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

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
