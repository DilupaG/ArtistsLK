package com.example.artistslk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.artistslk.Admin.AddTrack;
import com.example.artistslk.Admin.ArtistAdapter;
import com.example.artistslk.Model.Artist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //declaring
    EditText artistName;
    Spinner artistGenre;
    Button add;
    Context context;

    public static final String ARTIST_ID = "k";
    public static final String ARTIST_NAME = "D";

    ListView artistList;

    List<Artist> artistArrayList;

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
        artistList = findViewById(R.id.TrackListView);
        artistArrayList = new ArrayList<>();

        artistsDatabaseRef = FirebaseDatabase.getInstance().getReference().child("artists");

        //add button handling
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addArtist();
            }
        });

        //handling onclick on list
        artistList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Artist artist = artistArrayList.get(i);

                Intent intent = new Intent(getApplicationContext(), AddTrack.class);
                intent.putExtra(ARTIST_ID,artist.getArtistID());
                intent.putExtra(ARTIST_NAME,artist.getArtistName());
                startActivity(intent);



            }
        });

        artistList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Artist artist = artistArrayList.get(i);

                showEditDialog(artist.getArtistID(),artist.getArtistName()); 
                return false;
            }
        });


    }
    //retrieving
    @Override
    protected void onStart() {
        super.onStart();

        //checking for value changes inside database. if any changes happens, the this methods will be executed
        artistsDatabaseRef.addValueEventListener(new ValueEventListener() {

            //when we change anything inside database
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                artistArrayList.clear();

                for(DataSnapshot artistSnapshot: snapshot.getChildren()){

                    Artist artist = artistSnapshot.getValue(Artist.class);
                    artistArrayList.add(artist);

                }

                ArtistAdapter adapter = new ArtistAdapter(MainActivity.this,artistArrayList);
                artistList.setAdapter(adapter);

            }
            //execute when if there is a error
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void showEditDialog(String id, String name){

        System.out.println(id);
        System.out.println(name);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.edit_dialog,null);

        dialogBuilder.setView(dialogView);

        final EditText editTextName = dialogView.findViewById(R.id.editArtistName);
        final Spinner editSpinnerGenre = dialogView.findViewById(R.id.editArtistGenre);
        final Button editButton = dialogView.findViewById(R.id.editArtistBtn);

        dialogBuilder.setTitle("Update "+name+"'s Details");

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String namez = editTextName.getText().toString().trim();
                String genrez = editSpinnerGenre.getSelectedItem().toString();

                if(TextUtils.isEmpty(namez)){

                    Toast.makeText(context, "Enter Name", Toast.LENGTH_SHORT).show();


                }else{

                    updateArtist(id,namez,genrez);
                    alertDialog.dismiss();

                }

            }
        });
        

    }

    public boolean updateArtist(String id, String name, String genre){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("artists").child(id);

        Artist artist = new Artist(id,name,genre);

        databaseReference.setValue(artist);

        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();

        return true;
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