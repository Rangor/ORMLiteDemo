//package no.example.ormlitedemo;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
///**
// * Created with IntelliJ IDEA.
// * User: martinmi
// * Date: 03.06.12
// * Time: 16.34
// * To change this template use File | Settings | File Templates.
// */
//public class OldDBExample extends SQLiteOpenHelper{
//
//    public static final String KEY_PLACEID="_id";
//    public static final String KEY_PLACENAME="place_name";
//
//    private static final String TAG = "NotesDbAdapter";
//    private OldDBExample mDbHelper;
//    private SQLiteDatabase mDb;
//
//    private static final String DATABASE_PLACE_TABLE = "places";
//    private static final String DATABASE_CREATE_PLACE =
//            "create table places (_id integer primary key not null, place_name varchar(150) unique not null);";
//
//    public OldDBExample(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//        db.execSQL(DATABASE_CREATE_PLACE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
//        //Todo make smart uppgrade script!
//    }
//
//    public long createPlace(String place){
//        ContentValues initialValues = new ContentValues();
//        initialValues.put(KEY_PLACENAME, place);
//
//        return mDb.insert(DATABASE_PLACE_TABLE, null, initialValues);
//    }
//
//    public Cursor fetchAllPlaces(){
//        return mDb.query(true, DATABASE_PLACE_TABLE, new String[]{KEY_PLACEID,KEY_PLACENAME},null, null, null, null, null, null);
//    }
//
//    public void populateBusstopList() {
//
//        stopsCursor = mDbHelper.fetchAllBusstops();
//        bussStopList = new ArrayList<OverlayItem>();
//        if (stopsCursor.moveToFirst()) {
//            int nameColumn = stopsCursor.getColumnIndex(QueryDb.KEY_STOP_NAME);
//            int latColumn = stopsCursor.getColumnIndex(QueryDb.KEY_STOP_LATITUDE);
//            int lngColumn = stopsCursor.getColumnIndex(QueryDb.KEY_STOP_LONGITUDE);
//
//            do {
//                GeoPoint tempGeoPoint =
//                        new GeoPoint(
//                                (int) (stopsCursor.getDouble(latColumn) * 1E6),
//                                (int) (stopsCursor.getDouble(lngColumn) * 1E6));
//
//                bussStopList.add(new OverlayItem(tempGeoPoint, stopsCursor.getString(nameColumn), "Bussholdeplass"));
//
//            } while (stopsCursor.moveToNext());
//
//        }
//
//        new populateMapWithBusstopsTask().execute("String");
//    }
//}
