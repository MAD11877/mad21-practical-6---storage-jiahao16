package sg.edu.np.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class UserDbHandler extends SQLiteOpenHelper {

    public static ArrayList<User> userList = new ArrayList<>();
    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "userDB.db";
    public static String USERS = "Users";
    public static String COLUMN_NAME = "Username";
    public static String COLUMN_DESCRIPTION = "Description";
    public static String COLUMN_ID = "Id";
    public static String COLUMN_FOLLOWED = "Followed";

    public UserDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + USERS + "(" + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT," + COLUMN_ID + " TEXT," + COLUMN_FOLLOWED + " TEXT" +")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ USERS);
        onCreate(db);
    }

    public void addUser(User u){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, u.getName());
        values.put(COLUMN_DESCRIPTION, u.getDesc());
        values.put(COLUMN_ID, u.getId());
        values.put(COLUMN_FOLLOWED, u.getFollowed());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(USERS, null, values);
        db.close();
    }

    public ArrayList<User> getUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USERS;
        Cursor cursor = db.rawQuery(query, null);


        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            User queryData = new User();
            queryData.setName(cursor.getString(0));
            queryData.setDesc(cursor.getString(1));
            queryData.setId(cursor.getInt(2));
            queryData.setFollowed(cursor.getInt(3) == 1);
            userList.add(queryData);

            cursor.moveToNext();
        }
        db.close();
        return userList;
    }

    public void updateUser(User user){
        String query = "SELECT * FROM " + USERS + " WHERE " + COLUMN_NAME + "=\"" + user.getName() + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        User queryData = new User();

        if (cursor.moveToFirst())
        {
            queryData.setFollowed(user.getFollowed());
            cursor.close();
        }
        db.close();
    }
}
