package com.example.shoplist2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    public void newList(View view){
        startActivity(new Intent(this, Items.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textHello=findViewById(R.id.textHello);
        textHello.setTranslationY(-3000);
        textHello.animate().translationYBy(3000).setDuration(1000);

        db=new DatabaseHelper(this);
    }
}
