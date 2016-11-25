package placesafe.placesafe;

import android.app.Activity;
import android.content.Intent;
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
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero   =txtNumero.getText().toString();
                String nickname =txtNickName.getText().toString();
                Toast.makeText(getApplicationContext(),"Este vato se quiere registrar, su numero es: "+numero+" y si nickname es: "+nickname, Toast.LENGTH_SHORT).show();
            }
        });
       // Intent
    }
}
