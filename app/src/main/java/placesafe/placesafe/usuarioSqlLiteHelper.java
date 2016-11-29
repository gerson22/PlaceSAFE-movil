package placesafe.placesafe;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by roger on 25/11/2016.
 */

public class usuarioSqlLiteHelper extends SQLiteOpenHelper {
    //base de datos
    private static usuarioSqlLiteHelper database;
    //*************Creamos la estructura de la tabla donde guardaremos los daotos del usuarios ***//////
    private String sqlCreate = "Create table usuariios(" +
                                "id integer primary key autoincrement,"+
                                "telefono integer unique not null,"+
                                "text nickname not null);";
    //************************************************************************************************////////
    /***********Constructor de privado para no crear muchas instancias de***********************************/
    private usuarioSqlLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public static usuarioSqlLiteHelper getInstance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        database = (database==null)? new usuarioSqlLiteHelper(context,name,factory,version) : database;
        return database;
    }
    // metodo que al ejecutarse creará la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Usuarios");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }
}
