package placesafe.placesafe;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class welcome extends Activity {
    private Button btn=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_cargando);
        btn = (Button)findViewById(R.id.btnNext);
        usuarioSqlLiteHelper usdbh = usuarioSqlLiteHelper.getInstance(this, "DBUsuarios", null, 1);
        final SQLiteDatabase db = usdbh.getWritableDatabase();
        if(btn!=null){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor micursor =  db.rawQuery("select * from usuarios",null);
                    if(micursor.moveToNext()){// si el usuario ya se a registrado en la aplicaco�n lo mandamos a la pagina del mapa
                        //String sqlCreate = "Create table usuarios(id integer primary key AUTOINCREMENT,telefono integer unique not null,nombre text not null);";
                        //db.execSQL("drop table if exists usuarios");
                        //db.execSQL(sqlCreate);
                        final Intent miintent = new Intent(getApplicationContext(),mainActivity.class);
                        startActivity(miintent);
                    }else {// si no se ha registrado lo mandamos a la vista d registro
                        final Intent miintent = new Intent(getApplicationContext(), registro.class);
                        startActivity(miintent);
                    }
                }
            });
        }
    }
}
