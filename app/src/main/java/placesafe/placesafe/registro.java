package placesafe.placesafe;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                            String numero   =txtNumero.getText().toString();
                            String nickname =txtNickName.getText().toString();
                            RequestVolley request = RequestVolley.getInstance(getApplicationContext());
                            HashMap<String,String> datos = new HashMap<>();
                            datos.put("lada",numero.substring(0,3));
                            datos.put("telefono",numero);
                            datos.put("nickname",nickname);
                            request.requestString("POST","/sendData", new Response.Listener<String>() {
                                @Override
                                public void onResponse(String o) {
                                    if(o.equals("OK")){
                                        Intent inte = new Intent(getApplicationContext(),Map.class);
                                        Toast.makeText(getApplicationContext(),"El usuario se ha registrado exitosamente",Toast.LENGTH_LONG).show();
                                        startActivity(inte);
                                        //databse.execSQL("INSERT INTO usuarios values(null,'"+numero+"','"+nickname+"')");
                                    }else{
                                        Toast.makeText(getApplicationContext(),o,Toast.LENGTH_LONG).show();
                                        System.out.print(o);
                                    }
                                }
                            },datos);
                            //databse.execSQL("INSERT INTO usuarios values(null,'"+numero+"','"+nickname+"')");
                        }else{
                            Toast.makeText(getApplicationContext(),"Debes a ingresar diez digitos en el campo de telefono",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"No puedes dejar campos vacios",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


    }
}
