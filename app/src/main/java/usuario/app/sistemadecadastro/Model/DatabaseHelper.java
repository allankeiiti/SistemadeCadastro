package usuario.app.sistemadecadastro.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int VERSAO = 1;
    private static final String NOME_BANCO = "bancodb";
    public static final String TABELA = "cadastros";
    public static final String NOME = "NOME";
    public static final String ENDERECO = "ENDERECO";
    public static final String TELEFONE = "TELEFONE";

    public DatabaseHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    //onCreate é executado quando a aplicação cria o DB pela primeira vez.
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABELA + " ( "
                + NOME + " TEXT, "
                + ENDERECO + " TEXT, "
                + TELEFONE + " TEXT); ";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    //onUpgrade é executado quando já existe um DB. É chamado quando uma atualização é necessária
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        //Cria a tabela novamente
        onCreate(db);
    }

    public ArrayList<Registro> recuperaTodosRegistros(){
        ArrayList<Registro> array = new ArrayList<>();

        String selectQuery = "SELECT " + NOME + " , " + ENDERECO + " , " + TELEFONE + " FROM " + TABELA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Registro reg = new Registro();
                reg.setNome(cursor.getString(cursor.getColumnIndex(NOME)));
                reg.setEndereco(cursor.getString(cursor.getColumnIndex(ENDERECO)));
                reg.setTelefone(cursor.getString(cursor.getColumnIndex(TELEFONE)));
                array.add(reg);
            } while (cursor.moveToNext()) ;
        }
        db.close();
        return array;
    }

    public void insereRegistro(ArrayList<Registro> registro){
        //Writable Database pois queremos escrever dados
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        for (int i = 0; i < registro.size(); i++){
            valores.put(NOME, registro.get(i).getNome());
            valores.put(ENDERECO, registro.get(i).getEndereco());
            valores.put(TELEFONE, registro.get(i).getTelefone());
            db.insert(TABELA, null, valores);
        }
            //insert
            db.close();
    }

    public void limpaTabela(){
        SQLiteDatabase db = this.getWritableDatabase();
        String limpaQuery = "DELETE FROM " + TABELA;
        db.execSQL(limpaQuery);
        System.out.println(TABELA + " TRUNCADA!");
        db.close();
    }

}

//Fonte: https://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
