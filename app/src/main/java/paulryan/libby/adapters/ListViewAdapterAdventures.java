package paulryan.libby.adapters;

/**
 * Created by Ryan on 10.04.2016.
 */
import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import paulryan.libby.R;
import paulryan.libby.books.AdventuresActivity;
import paulryan.libby.books.Book;
import paulryan.libby.database.DatabaseHelperAdventures;

public class ListViewAdapterAdventures extends ArrayAdapter<Book> {

    private AdventuresActivity activity;
    private DatabaseHelperAdventures databaseHelperAdventures;
    private List<Book> bookList;

    public ListViewAdapterAdventures(AdventuresActivity context, int resource, List<Book> objects, DatabaseHelperAdventures helper) {
        super(context, resource, objects);
        this.activity = context;
        this.databaseHelperAdventures = helper;
        this.bookList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.book_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.book.setText(getItem(position).getBook());
        holder.author.setText(getItem(position).getAuthor());
        holder.year.setText(getItem(position).getYear());

        //Delete an item
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelperAdventures.deleteBook(getItem(position)); //delete in db
                Toast.makeText(activity, R.string.deleted, Toast.LENGTH_SHORT).show();

                //reload the database to view
                activity.reloadingDatabase();
            }
        });

        //Edit/Update an item
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
                alertDialog.setTitle(R.string.update_book);

                LinearLayout layout = new LinearLayout(activity);
                layout.setPadding(15, 15, 15, 15);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText bookBox = new EditText(activity);
                bookBox.setHint(R.string.dialog_book);
                layout.addView(bookBox);

                final EditText authorBox = new EditText(activity);
                authorBox.setHint(R.string.dialog_author);
                layout.addView(authorBox);

                final EditText yearBox = new EditText(activity);
                yearBox.setHint(R.string.dialog_year);
                layout.addView(yearBox);

                bookBox.setText(getItem(position).getBook());
                authorBox.setText(getItem(position).getAuthor());
                yearBox.setText(getItem(position).getYear());

                alertDialog.setView(layout);

                alertDialog.setPositiveButton(R.string.dialog_OK, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Book book = new Book(bookBox.getText().toString(), authorBox.getText().toString(),
                                yearBox.getText().toString());
                        book.setId(getItem(position).getId());
                        databaseHelperAdventures.updateBook(book); //update to db
                        Toast.makeText(activity, R.string.updated, Toast.LENGTH_SHORT).show();

                        //reload the database to view
                        activity.reloadingDatabase();
                    }
                });

                alertDialog.setNegativeButton(R.string.dialog_cancel, null);

                //show alert dialog
                alertDialog.show();
            }
        });

        //show details when each row item clicked
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
                alertDialog.setTitle(R.string.dialog_book);

                LinearLayout layout = new LinearLayout(activity);
                layout.setPadding(10, 10, 10, 10);
                layout.setOrientation(LinearLayout.VERTICAL);

                TextView bookBox = new TextView(activity);
                layout.addView(bookBox);

                TextView authorBox = new TextView(activity);
                layout.addView(authorBox);

                TextView yearBox = new TextView(activity);
                layout.addView(yearBox);

                bookBox.setText(R.string.dialog_book + getItem(position).getBook());
                authorBox.setText(R.string.dialog_author + getItem(position).getAuthor());
                yearBox.setText(R.string.dialog_year + getItem(position).getYear());

                alertDialog.setView(layout);
                alertDialog.setNegativeButton(R.string.dialog_OK, null);

                //show alert
                alertDialog.show();
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        private TextView book;
        private TextView author;
        private TextView year;
        private View btnDelete;
        private View btnEdit;

        public ViewHolder (View v) {
            book = (TextView)v.findViewById(R.id.book_text_view);
            author = (TextView)v.findViewById(R.id.author_text_view);
            year = (TextView)v.findViewById(R.id.year_text_view);
            btnDelete = v.findViewById(R.id.delete_image_view);
            btnEdit = v.findViewById(R.id.edit_image_view);
        }
    }
}