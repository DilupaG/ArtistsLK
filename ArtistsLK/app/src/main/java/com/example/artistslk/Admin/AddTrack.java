package com.example.artistslk.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artistslk.MainActivity;
import com.example.artistslk.Model.Track;
import com.example.artistslk.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTrack extends AppCompatActivity {

    //defining
    TextView artistName;
    EditText trackName;
    SeekBar seekBarRating;
    Button addTrack;
    Context context;

    ListView trackList;

    DatabaseReference databaseTrackRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track);

        //initializing
        artistName = findViewById(R.id.trackTitle);
        trackName = findViewById(R.id.addTrackName);
        seekBarRating = findViewById(R.id.trackRating);
        trackList = findViewById(R.id.TrackListView);
        addTrack = findViewById(R.id.addTrackBtn);
        context =this;

        //getting selected artist name of mainActivity
        Intent intent = getIntent();

        String id = intent.getStringExtra(MainActivity.ARTIST_ID);
        String name = intent.getStringExtra(MainActivity.ARTIST_NAME);

        System.out.println(id);
        //setting text view
        artistName.setText("Add "+name+"'s new track ");

        //database track
        databaseTrackRef = FirebaseDatabase.getInstance().getReference("tracks").child(name);



        //handling add Track button
        addTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTracks();
            }
        });

    }

    //addTrac method
    public void addTracks(){

        String trackT = trackName.getText().toString().trim();
        int rating = seekBarRating.getProgress();

        if(TextUtils.isEmpty(trackT)){

            Toast.makeText(context, "Please Enter Track name", Toast.LENGTH_SHORT).show();

        }else{
            String id = databaseTrackRef.push().getKey();
            Track track = new Track(trackT,id,rating);

            databaseTrackRef.child(id).setValue(track);
            Toast.makeText(context, "Track added", Toast.LENGTH_SHORT).show();


        }

    }
}