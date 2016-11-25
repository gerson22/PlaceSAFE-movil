package placesafe.placesafe;

import android.app.Activity;
import android.content.Intent;

import com.android.volley.Response;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
<<<<<<< HEAD
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


=======


>>>>>>> dc2d8262ce9d59ed028e9cc5bec8a371aead8707
public class mainActivity extends Activity {

    int PLACE_PICKER_REQUEST = 1;
    TextView getPlaces;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPlaces = (TextView) findViewById(R.id.placePick);
        final Activity act = this;
<<<<<<< HEAD
        try {
            pruebaGETVolley();
        } catch (JSONException e) {
            e.printStackTrace();
        }
=======
>>>>>>> dc2d8262ce9d59ed028e9cc5bec8a371aead8707
        getPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                Intent intent;
                try {
                    intent = builder.build(act);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
<<<<<<< HEAD
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this,data);
                String address = String.format("Place: %s", place.getAddress());
                Toast.makeText(this, address, Toast.LENGTH_LONG).show();
            }
        }
    }
    public void pruebaGETVolley() throws JSONException {
        //RequestVolley is a class to make request to the server
        RequestVolley rv = RequestVolley.getInstance(getApplicationContext());

        //Response.Listener is a listener to get success response
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject obj;
                try {
                    obj = new JSONObject(response);
                    int estado = Integer.parseInt(obj.optString("status"));
                    String message = obj.optString("message");
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        /*
        *   El metodo requestString recibe
        *   1.- Methodo
        *   2.- Path o ruta
        *   3.- Escuchador
        *   4.- Datos a enviar (Opcional)
        * */
        rv.requestString("GET", "/android",listener );


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();



    }

=======
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this,data);
                String address = String.format("Place: %s", place.getAddress());
                getPlaces.setText(address);
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

>>>>>>> dc2d8262ce9d59ed028e9cc5bec8a371aead8707

}
