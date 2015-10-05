/*
    lixin-reflex
    Copyright (C) 2015  Lixin Jin lixin1@ualberta.ca

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package ca.ualberta.lixin.lixin1_reflex;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PractiseModeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView practiseTime;
    private ImageButton buzzButton;
    private Button restartButton;

    private ArrayList<PlayerStatus> playerResult = new ArrayList<PlayerStatus>();

    private long reflexTime;        // record single practise time
    private long startingTime;      // save the time when the mode is started

    private String editTime;        // used to edit the practise time text view

    private static final String FILENAME = "filePractiseMode.sav";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practise_mode);

        buzzButton = (ImageButton) findViewById(R.id.buzzButton);
        buzzButton.setOnClickListener(this);

        practiseTime = (TextView) findViewById(R.id.practiseTime);
        practiseTime.setVisibility(View.INVISIBLE);

        restartButton = (Button) findViewById(R.id.restartButton);
        restartButton.setVisibility(View.INVISIBLE);
        restartButton.setOnClickListener(this);

        // Start timing as soon as entering the Practise Mode
        startingTime = System.currentTimeMillis();

    }

    protected void onStart(){
        super.onStart();
        loadFromFile();
    }
    public void onClick(View view){
        if(view.getId()==R.id.buzzButton){
            reflexTime = (System.currentTimeMillis() - startingTime);
            if (reflexTime < 10){
                // Restart the timer if the reflex time is less than 10ms
                editTime = "EARLY";
                startingTime = System.currentTimeMillis();
            }
            if (reflexTime > 2000){
                // Reflex time is over 2000ms, will display "over"
                editTime = "OVER";
            }
            else {
                editTime = String.valueOf(reflexTime) + "ms";
                playerResult.add(new PlayerStatus(reflexTime));
                saveInFile();
            }

            practiseTime.setText(editTime);
            practiseTime.setVisibility(View.VISIBLE);

            buzzButton.setVisibility(View.INVISIBLE);
            restartButton.setVisibility(View.VISIBLE);
        }

        if(view.getId()==R.id.restartButton){
            // Restart the game by hitting the restart button
            startingTime = System.currentTimeMillis();

            practiseTime.setVisibility(View.INVISIBLE);
            buzzButton.setVisibility(View.VISIBLE);
            restartButton.setVisibility(View.INVISIBLE);
        }
    }

    // class saveInFile is from the lonelyTwiiter app in CMPUT301 lab
    //https://github.com/jlx339/lonelyTwitter/blob/f15IOlab/app/src/main/java/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(playerResult, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com
            Type arrayListType = new TypeToken<ArrayList<PlayerStatus>>() {}.getType();
            playerResult = gson.fromJson(in,arrayListType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            playerResult = new ArrayList<PlayerStatus>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
}
