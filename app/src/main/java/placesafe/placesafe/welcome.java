package placesafe.placesafe;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.logging.LogRecord;


public class welcome extends Activity {
    Handler mhandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_cargando);
        usuarioSqlLiteHelper usdbh = usuarioSqlLiteHelper.getInstance(this, "DBUsuarios", null, 1);
        final SQLiteDatabase db = usdbh.getWritableDatabase();
        Cursor micursor =  db.rawQuery("select * from usuarios",null);
        //String sqlCreate = "Create table usuarios(id integer primary key AUTOINCREMENT,telefono integer unique not null,nombre text not null);";
        //db.execSQL("drop table if exists usuarios");
        //db.execSQL(sqlCreate);
        mhandler = new Handler();
        if(micursor.moveToNext()){// si el usuario ya se a registrado en la aplicaco�n lo mandamos a la pagina del mapa
            mhandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    final Intent miintent = new Intent(getApplicationContext(), mainActivity.class);
                    startActivity(miintent);
                }
            },5000);// 5 de espera para ejecutar la funcion
        }else {// si no se ha registrado lo mandamos a la vista d registro
            mhandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    final Intent miintent = new Intent(getApplicationContext(), registro.class);
                    startActivity(miintent);
                }
            }, 5000);// 5 de espera para ejecutar la funcion

        }
    }
}
