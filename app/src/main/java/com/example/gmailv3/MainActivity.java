package com.example.gmailv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import io.bloco.faker.Faker;

public class MainActivity extends AppCompatActivity {
    List<ContactModel> items;
    List<ContactModel> items2;
    List<ContactModel> item3;
    Button btnSearch;
    EditText edtSearch;


    boolean isCheck = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < 15; i++)
            items.add(new ContactModel(faker.name.name(), faker.lorem.sentence(), faker.lorem.paragraph(), "00:00"));



        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final ImageAdapter adapter = new ImageAdapter(items);
        recyclerView.setAdapter(adapter);


        btnSearch = findViewById(R.id.btn_favorite);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                items2=new ArrayList<>();
                for(int i=0;i<10;i++){
                    if(items.get(i).isFavorite){
                        items2.add(items.get(i));
                    }
                }
                ImageAdapter favoriteAdapter = new ImageAdapter(items2);
                if(!isCheck) {
                    recyclerView.setAdapter(favoriteAdapter);
                }
                else recyclerView.setAdapter(adapter);
                isCheck =!isCheck;
            }
        });

        edtSearch = findViewById(R.id.edt_search);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String charString = s.toString();
                if (count > 1) {
                    item3 = new ArrayList<>();
                    for (int i = 0; i < items.size(); i++) {

                        if (items.get(i).getName().toLowerCase().indexOf(charString) != -1 ||  items.get(i).getSubject().toLowerCase().indexOf(charString) != -1 || items.get(i).getContent().toLowerCase().indexOf(charString) != -1)
                            item3.add(items.get(i));
                    }
                    ImageAdapter searchAdapter = new ImageAdapter(item3);
                    recyclerView.setAdapter(searchAdapter);
                }
                else {
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}