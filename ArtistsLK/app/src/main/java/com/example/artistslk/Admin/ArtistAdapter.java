package com.example.artistslk.Admin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.artistslk.Model.Artist;
import com.example.artistslk.R;

import java.util.List;


public class ArtistAdapter extends ArrayAdapter<Artist> {

    private Activity context;
    private List<Artist> artistList;

    public ArtistAdapter(Activity context, List<Artist> artistList) {
        super(context, R.layout.artist_single_row,artistList);
        this.context=context;
        this.artistList=artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View singleRow = inflater.inflate(R.layout.artist_single_row,null,true);

        TextView artistListName = singleRow.findViewById(R.id.artistListName);
        TextView artistListGenre = singleRow.findViewById(R.id.artistListGenre);

        Artist artist = artistList.get(position);

        artistListName.setText(artist.getArtistName());
        artistListGenre.setText(artist.getArtistGenre());


        return singleRow;
    }
}
