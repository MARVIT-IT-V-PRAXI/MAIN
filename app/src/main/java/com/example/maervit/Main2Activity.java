package com.example.maervit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem i) {

        //SimplifiableIfStatement
        switch (i.getItemId()){
            case R.id.about:
                Toast.makeText(this, "About us", Toast.LENGTH_LONG).show();
                return true;
            case R.id.faq:
                Toast.makeText(this, "Faq", Toast.LENGTH_LONG).show();
                return true;
        }

        return super.onOptionsItemSelected(i);
    }
}
