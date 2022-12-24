package com.example.lessplastic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "lessPlastic.db";
    private static final int DATABASE_VERSION = 1;

    private static final String USUARIO_TABLE = "USUARIOS";
    private static final String PLASTICOS_TABLE = "PLASTICOS";
    private static final String REGISTROS_TABLE = "REGISTROS";

    public static final String ID_USUARIO = "ID_usuario";
    public static final String NOMBRE = "nombre";
    public static final String NOMBRE_USUARIO = NOMBRE + "_usuario";
    public static final String CORREO = "correo";
    public static final String APELLIDO = "apellido";
    public static final String CONTRASEÑA = "contraseña";
    public static final String ID_PLASTICO = "ID_plastico";
    public static final String TIPO = "tipo";
    public static final String CANTIDAD = "cantidad";
    public static final String TAMAÑO = "tamaño";
    public static final String PESO = "peso";
    public static final String FECHA = "fecha";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String usuarioTable = "CREATE TABLE " + USUARIO_TABLE + "(" + ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOMBRE_USUARIO + " TEXT, " + CORREO + " TEXT, " + NOMBRE + " TEXT, " + APELLIDO + " TEXT, " + CONTRASEÑA + " TEXT)";
        db.execSQL(usuarioTable);
        String plasticosTable = "CREATE TABLE " + PLASTICOS_TABLE + "(" + ID_PLASTICO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TIPO + " TEXT, " + CANTIDAD + " INTEGER, " + TAMAÑO + " REAL, " + PESO + " REAL)";
        db.execSQL(plasticosTable);
        String registrosTable = "CREATE TABLE " + REGISTROS_TABLE + "(" + FECHA + " TEXT, " + ID_USUARIO + " INTEGER, " + ID_PLASTICO + " INTEGER, FOREIGN KEY(" + ID_USUARIO + ") REFERENCES " + USUARIO_TABLE + "(" + ID_USUARIO + "), FOREIGN KEY(" + ID_PLASTICO + ") REFERENCES " + PLASTICOS_TABLE + "(" + ID_PLASTICO + "))";
        db.execSQL(registrosTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        if (usuario != null) {
            cv.put(NOMBRE_USUARIO, usuario.getNombreUsuario());
            cv.put(CORREO, usuario.getCorreo());
            cv.put(NOMBRE, usuario.getNombre());
            cv.put(APELLIDO, usuario.getApellido());
            cv.put(CONTRASEÑA, usuario.getContraseña());

            long insert = db.insert(USUARIO_TABLE, null, cv);
            if (insert == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public int checkUsuario(String usuario, String contraseña) {
        SQLiteDatabase db = this.getReadableDatabase();
        String check = "Select " + ID_USUARIO + ", " + NOMBRE_USUARIO + ", " + CONTRASEÑA + " from " + USUARIO_TABLE + " where " + NOMBRE_USUARIO + "= '" + usuario + "' and " + CONTRASEÑA + "='" + contraseña + "'";
        Cursor cursor = db.rawQuery(check, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int id = cursor.getInt(0);
            return id;
        } else
            return -1;

    }

    public String getNombreUsuario(int id_usuario) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select " + NOMBRE_USUARIO + " from " + USUARIO_TABLE + " where " + ID_USUARIO + "= " + id_usuario;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String nombre = cursor.getString(0);
            return nombre;
        } else
            return "";

    }

    public int addPlastic(Plastico plastico) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        if (plastico != null) {
            cv.put(TIPO, plastico.getTipo());
            cv.put(CANTIDAD, plastico.getCantidad());
            cv.put(TAMAÑO, plastico.getTamaño());
            cv.put(PESO, plastico.getPeso());

            long insert = db.insert(PLASTICOS_TABLE, null, cv);
            if (insert == -1) {
                return -1;
            } else {
                db = this.getReadableDatabase();
                String query = "Select last_insert_rowid()";
                Cursor cursor = db.rawQuery(query, null);
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    int id = cursor.getInt(0);
                    return id;
                } else
                    return -1;
            }
        } else {
            return -1;
        }
    }

    public boolean addRegistro(int id_usuario, int id_plastico) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
        String fDate = df.format(date);

        if (id_usuario != 0 && id_plastico != 0) {
            cv.put(FECHA, fDate);
            cv.put(ID_USUARIO, id_usuario);
            cv.put(ID_PLASTICO, id_plastico);

            long insert = db.insert(REGISTROS_TABLE, null, cv);
            if (insert == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public ArrayList<Plastico> getListaPlasticos(int id_usuario) {
        ArrayList<Plastico> lista = new ArrayList<Plastico>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + PLASTICOS_TABLE + " WHERE " + PLASTICOS_TABLE
                + "." + ID_PLASTICO + " IN (SELECT " + ID_PLASTICO + " FROM "
                + REGISTROS_TABLE + " WHERE " + REGISTROS_TABLE+ "."+ID_USUARIO
                + "=" + id_usuario + ")";
        Cursor cursor = db.rawQuery(query, null);
        int iID = cursor.getColumnIndex(ID_PLASTICO);
        int iTipo = cursor.getColumnIndex(TIPO);
        int iCantidad = cursor.getColumnIndex(CANTIDAD);
        int iTamaño = cursor.getColumnIndex(TAMAÑO);
        int iPeso = cursor.getColumnIndex(PESO);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int id = cursor.getInt(iID);
            String tipo = cursor.getString(iTipo);
            int cantidad = cursor.getInt(iCantidad);
            float tamaño = cursor.getFloat(iTamaño);
            float peso = cursor.getFloat(iPeso);
            lista.add(new Plastico(id, tipo, cantidad, tamaño, peso));
        }
        return lista;
    }

}
