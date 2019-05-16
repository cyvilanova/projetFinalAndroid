package com.example.quintessentiel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.quintessentiel.Product.Product;

/**
 * Fichier: LocalDbManager.java
 * Auteur: Cynthia Vilanova
 * Fonctionnalité: Object managing the connection to the local database
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
public class LocalDBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public LocalDBManager(Context c) {
        context = c;
    }

    public LocalDBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(Product product) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, product.getName());
        contentValue.put(DatabaseHelper.DESC, product.getDescription());
        contentValue.put(DatabaseHelper.PRICE, product.getPrice());
        contentValue.put(DatabaseHelper.QUANTITY, product.getQuantity());
        contentValue.put(DatabaseHelper.IMAGE, product.getImagePath());
        database.insert(DatabaseHelper.PRODUCT_TABLE, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[]{DatabaseHelper.NAME, DatabaseHelper.DESC, DatabaseHelper.PRICE, DatabaseHelper.QUANTITY, DatabaseHelper.IMAGE};
        Cursor cursor = database.query(DatabaseHelper.PRODUCT_TABLE, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

}
