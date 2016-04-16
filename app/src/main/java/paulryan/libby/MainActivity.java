package paulryan.libby;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import paulryan.libby.books.AdventuresActivity;
import paulryan.libby.books.ClassicsActivity;
import paulryan.libby.books.DetectivesActivity;
import paulryan.libby.books.FantasyActivity;
import paulryan.libby.books.HorrorActivity;
import paulryan.libby.books.PoemsActivity;
import paulryan.libby.books.SciFiActivity;
import paulryan.libby.books.ScienceActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAdventuresClicked(View view) {

        Intent intent = new Intent(MainActivity.this, AdventuresActivity.class);
        startActivity(intent);
    }

    public void onClassicsClicked(View view) {

        Intent intent = new Intent(MainActivity.this, ClassicsActivity.class);
        startActivity(intent);
    }

    public void onDetectivesClicked(View view) {

        Intent intent = new Intent(MainActivity.this, DetectivesActivity.class);
        startActivity(intent);
    }

    public void onHorrorClicked(View view) {

        Intent intent = new Intent(MainActivity.this, HorrorActivity.class);
        startActivity(intent);
    }

    public void onFantasyClicked(View view) {

        Intent intent = new Intent(MainActivity.this, FantasyActivity.class);
        startActivity(intent);
    }

    public void onSciFiClicked(View view) {

        Intent intent = new Intent(MainActivity.this, SciFiActivity.class);
        startActivity(intent);
    }

    public void onScienceClicked(View view) {

        Intent intent = new Intent(MainActivity.this, ScienceActivity.class);
        startActivity(intent);
    }

    public void onPoemsClicked(View view) {

        Intent intent = new Intent(MainActivity.this, PoemsActivity.class);
        startActivity(intent);
    }
}
