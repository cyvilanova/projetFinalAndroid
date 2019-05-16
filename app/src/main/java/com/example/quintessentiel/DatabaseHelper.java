package com.example.quintessentiel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Fichier: DatabaseHelper.java
 * Auteur: Cynthia Vilanova
 * Fonctionnalité: Object creating a table in the local database and accessing it
 * Date : 14-5-2019
 *
 * Vérification :
 * Date               Nom                   Approuvé
 *
 * Historique de modifications :
 * Date               Nom     Description
 * 14-5-2019          CV      Création
 * 15-5-2019          CV      Ajouter à MgrProduct pour synchroniser
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Product
    public static final String PRODUCT_TABLE = "product";

    // Table columns
    public static final String _ID = "id_product";
    public static final String NAME = "name";
    public static final String DESC = "description";
    public static final String PRICE = "price";
    public static final String QUANTITY = "quantity";
    public static final String IMAGE = "image_path";

    // Database Information
    static final String DB_NAME = "QUIN_PRODUCTS.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + PRODUCT_TABLE + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + DESC + " TEXT, " + PRICE + " DECIMAL(7,2) NOT NULL DEFAULT 0.00, "
            + QUANTITY + " INTEGER DEFAULT 0, " + IMAGE + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE);
        onCreate(db);
    }
}
