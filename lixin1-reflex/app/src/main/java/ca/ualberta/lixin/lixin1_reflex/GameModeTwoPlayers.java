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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
import java.util.List;

public class GameModeTwoPlayers extends AppCompatActivity implements View.OnClickListener {

    private ImageButton modeTwoPlayer1Button;
    private ImageButton modeTwoPlayer2Button;
    private Button modeTwoStartButton;
    private TextView modeTwoPlayer1;
    private TextView modeTwoPlayer2;
    private long player1Time;
    private long player2Time;
    private long startingTime;

    private ArrayList<PlayerStatus> playerResult = new ArrayList<PlayerStatus>();

    private static final String FILENAME = "fileGameModeTwo.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_mode_two_players);

        modeTwoPlayer1Button = (ImageButton) findViewById(R.id.modeTwoPlayer1Button);
        modeTwoPlayer1Button.setOnClickListener(this);

        modeTwoPlayer2Button = (ImageButton) findViewById(R.id.modeTwoPlayer2Button);
        modeTwoPlayer2Button.setOnClickListener(this);

        modeTwoStartButton = (Button) findViewById(R.id.modeTwoStartButton);
        modeTwoStartButton.setOnClickListener(this);

        modeTwoPlayer1Button.setVisibility(View.INVISIBLE);
        modeTwoPlayer2Button.setVisibility(View.INVISIBLE);

        modeTwoPlayer1 = (TextView) findViewById(R.id.modeTwoPlayer1);
        modeTwoPlayer2 = (TextView) findViewById(R.id.modeTwoPlayer2);

        modeTwoPlayer1.setVisibility(View.INVISIBLE);
        modeTwoPlayer2.setVisibility(View.INVISIBLE);

        startingTime = System.currentTimeMillis();

    }

    public void onStart(){
        super.onStart();
        loadFromFile();
    }

    public void onClick(View view){
        if(view.getId()==R.id.modeTwoStartButton){
            modeTwoStartButton.setVisibility(View.INVISIBLE);

            modeTwoPlayer1.setVisibility(View.VISIBLE);
            modeTwoPlayer2.setVisibility(View.VISIBLE);
            modeTwoPlayer1Button.setVisibility(View.VISIBLE);
            modeTwoPlayer2Button.setVisibility(View.VISIBLE);

            startingTime = System.currentTimeMillis();
        }

        if(view.getId()==R.id.modeTwoPlayer1Button){
            modeTwoPlayer1Button.setVisibility(View.INVISIBLE);
            modeTwoPlayer2Button.setVisibility(View.INVISIBLE);
            modeTwoPlayer1.setVisibility(View.INVISIBLE);
            modeTwoPlayer2.setVisibility(View.INVISIBLE);
            modeTwoStartButton.setVisibility(View.VISIBLE);

            player1Time = (System.currentTimeMillis() - startingTime);

            playerResult.add(new PlayerStatus("Player1", player1Time));
            saveInFile();

            Toast.makeText(this, "Player1 Win!", Toast.LENGTH_SHORT).show();

        }

        if(view.getId()==R.id.modeTwoPlayer2Button){
            modeTwoPlayer1Button.setVisibility(View.INVISIBLE);
            modeTwoPlayer2Button.setVisibility(View.INVISIBLE);
            modeTwoPlayer1.setVisibility(View.INVISIBLE);
            modeTwoPlayer2.setVisibility(View.INVISIBLE);
            modeTwoStartButton.setVisibility(View.VISIBLE);

            player2Time = (System.currentTimeMillis() - startingTime);

            playerResult.add(new PlayerStatus("Player2", player2Time));
            saveInFile();

            Toast.makeText(this, "Player2 Win!", Toast.LENGTH_SHORT).show();

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
