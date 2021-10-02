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
import com.example.artistslk.Model.Track;
import com.example.artistslk.R;

import java.util.List;

public class TrackAdapter extends ArrayAdapter<Track> {

    private Activity context;
    private List<Track> trackList;

    public TrackAdapter(@NonNull Activity context, List<Track> trackList) {
        super(context, R.layout.track_single_row,trackList);
        this.context=context;
        this.trackList=trackList;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View singleRow = inflater.inflate(R.layout.track_single_row,null,true);

        TextView trackListName = singleRow.findViewById(R.id.trackListName);
        TextView trackListRating = singleRow.findViewById(R.id.trackListRating);

        Track track = trackList.get(position);

        trackListName.setText(track.getTrackName());
        trackListRating.setText(String.valueOf(track.getTrackRating()));


        return singleRow;
    }
}
