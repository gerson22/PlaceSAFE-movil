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


public class registro extends Activity {
    EditText txtNumero,txtNickName;
    Button btnOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        txtNumero   =  (EditText)findViewById(R.id.numero);
        txtNickName =  (EditText)findViewById(R.id.nickname);
        btnOk       =  (Button)findViewById(R.id.btnOk);
        usuarioSqlLiteHelper dbh = usuarioSqlLiteHelper.getInstance(this, "DBUsuarios", null, 1);
        final SQLiteDatabase databse = dbh.getWritableDatabase();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtNickName.getText().equals("") && !txtNumero.getText().equals("")){
                    if(txtNumero.getText().length()==10){
                        String numero   =txtNumero.getText().toString();
                        String nickname =txtNickName.getText().toString();
                        //String sqlCreate = "Create table usuarios(id integer primary key AUTOINCREMENT,telefono integer unique not null,nombre text not null);";
                        //databse.execSQL("drop table if exists");
                        //databse.execSQL(sqlCreate);
                        databse.execSQL("INSERT INTO usuarios values(null,'"+numero+"','"+nickname+"')");
                    }else{
                       Toast.makeText(getApplicationContext(),"Debes ingresar diez digitos en el campo de telefono",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"No puedes dejar campos vacios",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
