package com.example.shoplist2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Items extends AppCompatActivity implements RecyclerViewClickListener {

    static int list_no, item_no;
    DatabaseHelper db=new DatabaseHelper(this);
    RecyclerView rvData;
    ItemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        rvData=findViewById(R.id.rvData);//RecyclerView

        Cursor data=db.viewData(list_no);//Fetch items of current list for display in recyclerview
        final List<ItemCard> items=new ArrayList<>();//List containing items to be displayed

        if(data.getCount()==0);//If currently there are no items
        else{
            while(data.moveToNext()) {
                //Create an object of ItemCard class and add data to it from database
                ItemCard newCard=new ItemCard(data.getString(3), Integer.parseInt(data.getString(5)), Integer.parseInt(data.getString(4)));

                items.add(newCard);//Add the new object to the list of items to be displayed
            }
            adapter=new ItemsAdapter(items);
            rvData.setAdapter(adapter);
            rvData.setLayoutManager(new LinearLayoutManager(Items.this));
        }

        final AutoCompleteTextView item_name=findViewById(R.id.inputItem);
        final EditText price=findViewById(R.id.inputPrice);
        final EditText quantity=findViewById(R.id.inputQuantity);
        Button add=findViewById(R.id.buttonAdd);

        list_no=db.getListNumber();
        item_no=1;

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Data from input fields is fetched and inserted in the database
                if(db.insertData(list_no,
                              item_no,
                              item_name.getText().toString(),
                              Integer.parseInt(quantity.getText().toString()),
                              Integer.parseInt(price.getText().toString())
                              )) {
                    Toast.makeText(Items.this, "Successful!", Toast.LENGTH_SHORT).show();
                    item_no++;
                }
                else
                    Toast.makeText(Items.this, "Not successful", Toast.LENGTH_SHORT).show();
                Cursor data=db.viewData(list_no);
                if(data.getCount()==0);
                else{
                    items.clear();
                    while(data.moveToNext()) {
                        ItemCard newCard=new ItemCard(data.getString(3), Integer.parseInt(data.getString(5)), Integer.parseInt(data.getString(4)));

                        items.add(newCard);
                    }

                    adapter=new ItemsAdapter(items);
                    rvData.setAdapter(adapter);
                    rvData.setLayoutManager(new LinearLayoutManager(Items.this));
                }
            }
        });
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        adapter=new ItemsAdapter(this, this);
    }
}