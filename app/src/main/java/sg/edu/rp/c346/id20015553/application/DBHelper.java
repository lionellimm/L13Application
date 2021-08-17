package sg.edu.rp.c346.id20015553.application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shoppingCart.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CART = "Cart";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_CHECK = "checker";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE Song
        // (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT,
        // singers TEXT, stars INTEGER, year INTEGER );
        String createSongTableSql = "CREATE TABLE " + TABLE_CART + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_CHECK + " BOOLEAN, "
                + COLUMN_PRICE + " FLOAT )";
        db.execSQL(createSongTableSql);
        Log.i("info", createSongTableSql + "\ncreated tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }

    public long insertCart(String name, String description, float price) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_PRICE, price);
        // Insert the row into the TABLE_SONG
        long result = db.insert(TABLE_CART, null, values);
        // Close the database connection
        db.close();
        Log.d("SQL Insert","" + result);
        return result;
    }

    public ArrayList<Cart> getAllCart() {
        ArrayList<Cart> cartList = new ArrayList<Cart>();
        String selectQuery = "SELECT "
                + COLUMN_NAME + "," + COLUMN_DESCRIPTION + ","
                + COLUMN_PRICE + " FROM " + TABLE_CART;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                String description = cursor.getString(1);
                float price = cursor.getFloat(2);

                Cart newCart = new Cart(name, description, price, false);
                cartList.add(newCart);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cartList;
    }

    public void clear(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_CART);
    }

//    public void changeCheckTrue(Cart cart){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(COLUMN_CHECK, true);
//
//        db.update(TABLE_CART, cv, "_id = ?", new String[]{String.valueOf(cart.getId())});
//        System.out.println(cart.getId());
//        System.out.println(cart.isPurchased());
//    }
//    public void changeCheckFalse(Cart cart){
//        cart.setPurchased(false);
//    }

}
