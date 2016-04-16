package paulryan.libby.database;

/**
 * Created by Ryan on 16.04.2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import paulryan.libby.books.Book;

public class DatabaseHelperScience extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_BOOKS_ADVENTURES = "myScienceDB";

    // Book table name
    private static final String TABLE_ADVENTURES = "science_books";

    private static final String KEY_ID = "id";
    private static final String KEY_BOOK = "book";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_YEAR = "year";

    public DatabaseHelperScience(Context context) {

        super(context, DATABASE_BOOKS_ADVENTURES, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FRIEND_TABLE = "CREATE TABLE " + TABLE_ADVENTURES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_BOOK + " TEXT,"
                + KEY_AUTHOR + " TEXT, " + KEY_YEAR + " TEXT" +  ")";
        db.execSQL(CREATE_FRIEND_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADVENTURES);

        // Create tables again
        onCreate(db);
    }

    // Adding a new record (book) to table
    public void addNewFriend(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BOOK, book.getBook());
        values.put(KEY_AUTHOR, book.getAuthor());
        values.put(KEY_YEAR, book.getYear());

        // inserting this record
        db.insert(TABLE_ADVENTURES, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Friends in Table of Database
    public List<Book> getAllFriends() {
        List<Book> bookList = new ArrayList<>();

        // select query
        String selectQuery = "SELECT  * FROM " + TABLE_ADVENTURES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all table records and adding to list
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setBook(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setYear(cursor.getString(3));

                // Adding book to list
                bookList.add(book);
            } while (cursor.moveToNext());
        }

        return bookList;
    }

    // Updating a record in database table
    public int updateFriend(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BOOK, book.getBook());
        values.put(KEY_AUTHOR, book.getAuthor());
        values.put(KEY_YEAR, book.getYear());

        // updating row
        return db.update(TABLE_ADVENTURES, values, KEY_ID + " = ?", new String[]{String.valueOf(book.getId())});
    }

    // Deleting a record in database table
    public void deleteFriend(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ADVENTURES, KEY_ID + " = ?", new String[]{String.valueOf(book.getId())});
        db.close();
    }

    // getting number of records in table
    public int getContactsCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor dataCount = db.rawQuery("select " + KEY_ID + " from " + TABLE_ADVENTURES, null);

        int count = dataCount.getCount();
        dataCount.close();
        db.close();

        return count;
    }
}
