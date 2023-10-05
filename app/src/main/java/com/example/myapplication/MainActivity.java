package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;


class Movie {
    String name;
    String year;
    String genre;
    String directed_by;
    String budget;
}

public class MainActivity extends AppCompatActivity {

    ArrayList<Movie> movies;
    LinearLayout FilmLayout;
    TextView FilmName;
    TextView FilmYear;
    TextView FilmGenre;
    TextView DirectedBy;
    TextView Budget;
    Button GetFilm;
    TextView NoFilmsLeft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FilmLayout = findViewById(R.id.FilmLayout);
        FilmName = findViewById(R.id.FilmName);
        FilmYear = findViewById(R.id.FilmYear);
        FilmGenre = findViewById(R.id.FilmGenre);
        DirectedBy = findViewById(R.id.DirectedBy);
        Budget = findViewById(R.id.Budget);
        GetFilm = findViewById(R.id.GetFilm);
        NoFilmsLeft = findViewById(R.id.NoFilmsLeft);

        try {
            InputStream stream = getAssets().open("movies.json");
            InputStreamReader reader = new InputStreamReader(stream);
            Gson gson = new Gson();
            Type movieListType = new TypeToken<ArrayList<Movie>>() {}.getType();
            movies = gson.fromJson(reader, movieListType);

        } catch(IOException e){
            movies = null;
        }

    }

    public void onClick(View view){

        Log.d("actual size", String.valueOf(movies.size()));

        if(movies == null || movies.size() == 0){
            GetFilm.setVisibility(View.GONE);
            FilmLayout.setVisibility(View.GONE);
            NoFilmsLeft.setVisibility(View.VISIBLE);
        } else {

            Random ran = new Random();
            int index = ran.nextInt(movies.size());

            Log.d("actual size", String.valueOf(index));


            Movie movie = movies.get(index);
            movies.remove(index);

            FilmName.setText("Name: " + movie.name);
            FilmYear.setText("Year: " + movie.year);
            FilmGenre.setText("Genre: " + movie.genre);
            DirectedBy.setText("Directed by: " + movie.directed_by);
            Budget.setText("Budget: " + movie.budget);

            if (FilmLayout.getVisibility() == View.GONE) {
                FilmLayout.setVisibility(View.VISIBLE);
            }
        }

    }

}