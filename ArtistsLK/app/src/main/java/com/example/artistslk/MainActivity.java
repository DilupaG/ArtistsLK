package com.example.artistslk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.artistslk.Model.Artist;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //declaring
    EditText artistName;
    Spinner artistGenre;
    Button add;
    Context context;

    //Firebase DatabaseReference
    DatabaseReference artistsDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing
        artistName= findViewById(R.id.addArtistName);
        artistGenre = findViewById(R.id.addArtistGenre);
        add = findViewById(R.id.addArtistBtn);
        context = this;

        artistsDatabaseRef = FirebaseDatabase.getInstance().getReference().child("artists");

        //add button handling
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addArtist();
            }
        });

    }

    public void addArtist(){

        //getting user inputs
        String name = artistName.getText().toString().trim();
        String genre = artistGenre.getSelectedItem().toString();

        //validation
        if(TextUtils.isEmpty(name)){
            Toast.makeText(context, "Please enter artist name", Toast.LENGTH_SHORT).show();
        }else{

            //getting a unique key/ID for a artist
            String id = artistsDatabaseRef.push().getKey();

            //creating artist object using our values
            Artist artist = new Artist(id,name,genre);

            //inserting artist into the database under the obtained ID
            artistsDatabaseRef.child(id).setValue(artist);

            //getting conformation about adding
            Toast.makeText(context, "Artist added", Toast.LENGTH_SHORT).show();
        }

    }

}