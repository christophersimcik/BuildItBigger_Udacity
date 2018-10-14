package com.udacity.gradle.builditbigger.backend;

import com.example.jokelibrary.JokeLibrary;

/** The object model for the data we are sending through endpoints */
public class MyJoke {
    private String myJoke;
    JokeLibrary jokeLibrary ;
    public MyJoke() {
        jokeLibrary = new JokeLibrary();
    }
    public void setJoke(String joke){
        myJoke = joke;
    }
    public String getMyJoke(){
        return myJoke;
    }
}