package placesafe.placesafe;

import android.app.Activity;
import android.content.Intent;
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
        setContentView(R.layout.activity_welcome);
        btn = (Button)findViewById(R.id.btnNext);
        usuarioSqlLiteHelper usdbh = usuarioSqlLiteHelper.getInstance(this, "DBUsuarios", null, 1);
        if(btn!=null){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent miintent = new Intent(getApplicationContext(),registro.class);
                    startActivity(miintent);
                }
            });
        }
    }
}
