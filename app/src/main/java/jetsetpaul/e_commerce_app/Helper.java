package jetsetpaul.e_commerce_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pauljoiner on 6/27/16.
 */
public class Helper extends SQLiteOpenHelper {
    public Helper(Context context) {
        super(context, "db", null, 1);
    }

    private static Helper INSTANCE;
    public static final String[] SEARCHED_COLUMNS = {DataEntryProduct.COLUMN_TITLE, DataEntryProduct.COLUMN_ARTIST, DataEntryProduct.COLUMN_DESCRIPTION};

    public static synchronized Helper getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = new Helper(context.getApplicationContext());
        return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_PRODUCT);
        db.execSQL(SQL_CREATE_ENTRIES_BAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_PRODUCT);
        db.execSQL(SQL_DELETE_ENTRIES_BAND);
        onCreate(db);
    }

    public static abstract class DataEntryProduct implements BaseColumns {
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_ARTIST = "artist";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_IMAGE_ID = "image_id";
        public static final String COLUMN_MEDIA = "media";
    }

    public static abstract class DataEntryBand implements BaseColumns {
        public static final String TABLE_NAME = "band";
        public static final String COLUMN_ARTIST = "artist";
        public static final String COLUMN_GENRE = "genre";
        public static final String COLUMN_LOCATION = "location";
    }

    private static final String SQL_CREATE_ENTRIES_PRODUCT = "CREATE TABLE " +
            DataEntryProduct.TABLE_NAME + " (" +
            DataEntryProduct._ID + " INTEGER PRIMARY KEY, " +
            DataEntryProduct.COLUMN_TITLE + " TEXT, " +
            DataEntryProduct.COLUMN_ARTIST + " TEXT, " +
            DataEntryProduct.COLUMN_CATEGORY + " TEXT, " +
            DataEntryProduct.COLUMN_DESCRIPTION + " TEXT, " +
            DataEntryProduct.COLUMN_PRICE + " TEXT, " +
            DataEntryProduct.COLUMN_IMAGE_ID + " INTEGER, " +
            DataEntryProduct.COLUMN_MEDIA + " TEXT" + ")";

    private static final String SQL_DELETE_ENTRIES_PRODUCT = "DROP TABLE IF EXISTS " +
            DataEntryProduct.TABLE_NAME;

    private static final String SQL_CREATE_ENTRIES_BAND = "CREATE TABLE " +
            DataEntryBand.TABLE_NAME + " (" +
            DataEntryBand._ID + " INTEGER PRIMARY KEY," +
            DataEntryBand.COLUMN_ARTIST + " TEXT," +
            DataEntryBand.COLUMN_GENRE + " TEXT," +
            DataEntryBand.COLUMN_LOCATION + " TEXT" + ")";

    private static final String SQL_DELETE_ENTRIES_BAND = "DROP TABLE IF EXISTS " +
            DataEntryBand.TABLE_NAME;

    public void insertRow(Product product) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataEntryProduct.COLUMN_TITLE, product.getTitle());
        values.put(DataEntryProduct.COLUMN_ARTIST, product.getArtist());
        values.put(DataEntryProduct.COLUMN_CATEGORY, product.getCategory());
        values.put(DataEntryProduct.COLUMN_DESCRIPTION, product.getDescription());
        values.put(DataEntryProduct.COLUMN_PRICE, product.getPrice());
        values.put(DataEntryProduct.COLUMN_IMAGE_ID, product.getImageId());
        values.put(DataEntryProduct.COLUMN_MEDIA, product.getMedia());

        db.insertOrThrow(DataEntryProduct.TABLE_NAME, null, values);
    }

    public void insertRowBand(Band band) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataEntryBand.COLUMN_ARTIST, band.getArtist());
        values.put(DataEntryBand.COLUMN_GENRE, band.getGenre());
        values.put(DataEntryBand.COLUMN_LOCATION, band.getLocation());

        db.insertOrThrow(DataEntryBand.TABLE_NAME, null, values);
    }

    public void cleanDatabase() {
        SQLiteDatabase db = getWritableDatabase();
        String productCleanQuery = "DELETE FROM product;";
        db.execSQL(productCleanQuery);
        String bandCleanQuery = "DELETE FROM band;";
        db.execSQL(bandCleanQuery);

    }

    public List<Product> getAllProduct() {
        List<Product> productDetail = new ArrayList<>();
        String PRODUCT_SELECT_QUERY = "SELECT * FROM " + DataEntryProduct.TABLE_NAME;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(PRODUCT_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    String title = cursor.getString(cursor.getColumnIndex(DataEntryProduct.COLUMN_TITLE));
                    String artist = cursor.getString(cursor.getColumnIndex(DataEntryProduct.COLUMN_ARTIST));
                    String category = cursor.getString(cursor.getColumnIndex(DataEntryProduct.COLUMN_CATEGORY));
                    String description = cursor.getString(cursor.getColumnIndex(DataEntryProduct.COLUMN_DESCRIPTION));
                    String price = cursor.getString(cursor.getColumnIndex(DataEntryProduct.COLUMN_PRICE));
                    Integer imageId = cursor.getInt(cursor.getColumnIndex(DataEntryProduct.COLUMN_IMAGE_ID));
                    String media = cursor.getString(cursor.getColumnIndex(DataEntryProduct.COLUMN_MEDIA));
                    productDetail.add(new Product(title, artist, category, description, price, imageId, media));

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("Paul's App", "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return productDetail;
    }
    public ArrayList<Product> searchProducts(String userQuery){

        SQLiteDatabase db = this.getReadableDatabase();
        // Building query using INNER JOIN keyword
        String query = "SELECT * FROM PRODUCT INNER JOIN BAND ON PRODUCT.ARTIST = BAND.ARTIST WHERE BAND.LOCATION  LIKE '%" + userQuery
                + "%' OR PRODUCT.TITLE LIKE '%" + userQuery
                +"%' OR PRODUCT.ARTIST LIKE '%" + userQuery + "%'";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        List<Product> resultItems = new ArrayList<Product>();
        while(!cursor.isAfterLast()){
            resultItems.add(new Product(cursor.getString(cursor.getColumnIndex(DataEntryProduct.COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndex(DataEntryProduct.COLUMN_ARTIST)),
                    cursor.getString(cursor.getColumnIndex(DataEntryProduct.COLUMN_CATEGORY)),
                    cursor.getString(cursor.getColumnIndex(DataEntryProduct.COLUMN_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndex(DataEntryProduct.COLUMN_PRICE)),
                    cursor.getInt(cursor.getColumnIndex(DataEntryProduct.COLUMN_IMAGE_ID)),
                    cursor.getString(cursor.getColumnIndex(DataEntryProduct.COLUMN_IMAGE_ID))));
            cursor.moveToNext();
        }
        cursor.close();
        return (ArrayList<Product>) resultItems;
    }
}

