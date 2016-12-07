package placesafe.placesafe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import java.util.HashMap;


public class registro extends Activity {
    EditText txtNumero,txtNickName;
    Button btnOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_confirmacion);
        txtNumero   =  (EditText)findViewById(R.id.numero);
        txtNickName =  (EditText)findViewById(R.id.nickname);
        btnOk       =  (Button)findViewById(R.id.btnOK);
        usuarioSqlLiteHelper dbh = usuarioSqlLiteHelper.getInstance(this, "DBUsuarios", null, 1);
        final SQLiteDatabase databse = dbh.getWritableDatabase();
        if(btnOk!=null){
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!txtNickName.getText().toString().equals("") && !txtNumero.getText().toString().equals("")){
                        if(txtNumero.getText().length()==10){
                            if (txtNickName.getText().length() <= 10){
                                final String encript = Security.encriptTo_md5(txtNumero.getText().toString());
                                if(encript!=null){
                                    final String numero   =txtNumero.getText().toString();
                                    final String nickname =txtNickName.getText().toString();
                                    RequestVolley request = RequestVolley.getInstance(getApplicationContext());
                                    final HashMap<String,String> datos = new HashMap<>();
                                    datos.put("lada",numero.substring(0,3));
                                    datos.put("telefono",encript);
                                    datos.put("nickname", nickname);
                                    btnOk.setEnabled(false);
                                    btnOk.setText("Registrando...");
                                    btnOk.setEnabled(true);
                                    request.requestString("POST", "/sendData", new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String o) {
                                            btnOk.setEnabled(true);
                                            btnOk.setText("CONFIRMAR");
                                            if (o.equals("OK")) {
                                                Intent inte = new Intent(getApplicationContext(), Map.class);
                                                Toast.makeText(getApplicationContext(), "El usuario se ha registrado exitosamente", Toast.LENGTH_LONG).show();
                                                startActivity(inte);
                                                databse.execSQL("INSERT INTO usuarios values(null,'" + encript + "','" + nickname + "')");
                                                finish();
                                            } else {
                                                Toast.makeText(getApplicationContext(),o, Toast.LENGTH_LONG).show();
                                                System.out.print(o);
                                            }
                                        }
                                    }, datos);
                                }
                            }
                            else{
                                txtNickName.setError( "Nickname demasiado largo \n(10 caracteres max.)" );
                            }

                            //databse.execSQL("INSERT INTO usuarios values(null,'"+numero+"','"+nickname+"')");
                        }else{
                            txtNumero.setError( "Número teléfonico incorrecto" );
                        }
                    }else{
                        txtNumero.setError( "No puedes dejar campos vacios" );
                        txtNickName.setError( "No puedes dejar campos vacios" );
                    }
                }
            });
        }
    }
}