package paulryan.libby.books;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import paulryan.libby.R;
import paulryan.libby.adapters.ListViewAdapterAdventures;
import paulryan.libby.database.DatabaseHelperAdventures;

public class AdventuresActivity extends AppCompatActivity {

    private ListView listView;
    private ListViewAdapterAdventures adapter;
    private DatabaseHelperAdventures databaseHelperAdventures;
    private List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        listView = (ListView) findViewById(R.id.adventures_list_view);

        databaseHelperAdventures = new DatabaseHelperAdventures(this);
        bookList = new ArrayList<>();
        reloadingDatabase(); //loading table of DB to ListView
    }

    public void reloadingDatabase() {
        bookList = databaseHelperAdventures.getAllFriends();
        if (bookList.size() == 0) {
            Toast.makeText(this, R.string.no_record_found, Toast.LENGTH_SHORT).show();
        }
        adapter = new ListViewAdapterAdventures(this, R.layout.book_item, bookList, databaseHelperAdventures);
        listView.setAdapter(adapter);
    }

    private void addingNewFriendDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AdventuresActivity.this);
        alertDialog.setTitle(R.string.add_new_book);

        LinearLayout layout = new LinearLayout(this);
        layout.setPadding(10, 10, 10, 10);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText nameBook = new EditText(this);
        nameBook.setHint(R.string.dialog_book);
        layout.addView(nameBook);

        final EditText authorBox = new EditText(this);
        authorBox.setHint(R.string.dialog_author);
        layout.addView(authorBox);

        final EditText yearBox = new EditText(this);
        yearBox.setHint(R.string.dialog_year);
        layout.addView(yearBox);

        alertDialog.setView(layout);

        alertDialog.setPositiveButton(R.string.dialog_OK, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Book book = new Book(getText(nameBook), getText(authorBox), getText(yearBox));
                databaseHelperAdventures.addNewFriend(book);

                reloadingDatabase(); //reload the db to view
            }
        });

        alertDialog.setNegativeButton(R.string.dialog_cancel, null);

        //show alert
        alertDialog.show();
    }

    //get text available in TextView/EditText
    private String getText(TextView textView) {
        return textView.getText().toString().trim();
    }

    public void onAddButtonClicked(View view) {
        addingNewFriendDialog();
    }
}
