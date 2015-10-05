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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

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

public class GameModeThreePlayers extends AppCompatActivity implements View.OnClickListener{

    private ImageButton modeThreePlayer1Button;
    private ImageButton modeThreePlayer2Button;
    private ImageButton modeThreePlayer3Button;
    private Button modeThreeStartButton;
    private TextView modeThreePlayer1;
    private TextView modeThreePlayer2;
    private TextView modeThreePlayer3;
    private long player1Time;
    private long player2Time;
    private long player3Time;
    private long startingTime;

    private ArrayList<PlayerStatus> playerResult = new ArrayList<PlayerStatus>();

    private static final String FILENAME = "fileGameModeThree.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_mode_three_players);

        modeThreePlayer1Button = (ImageButton) findViewById(R.id.modeThreePlayer1Button);
        modeThreePlayer1Button.setOnClickListener(this);

        modeThreePlayer2Button = (ImageButton) findViewById(R.id.modeThreePlayer2Button);
        modeThreePlayer2Button.setOnClickListener(this);

        modeThreePlayer3Button = (ImageButton) findViewById(R.id.modeThreePlayer3Button);
        modeThreePlayer3Button.setOnClickListener(this);


        modeThreeStartButton = (Button) findViewById(R.id.modeThreeStartButton);
        modeThreeStartButton.setOnClickListener(this);

        modeThreePlayer1Button.setVisibility(View.INVISIBLE);
        modeThreePlayer2Button.setVisibility(View.INVISIBLE);
        modeThreePlayer3Button.setVisibility(View.INVISIBLE);

        modeThreePlayer1 = (TextView) findViewById(R.id.modeThreePlayer1);
        modeThreePlayer2 = (TextView) findViewById(R.id.modeThreePlayer2);
        modeThreePlayer3 = (TextView) findViewById(R.id.modeThreePlayer3);

        modeThreePlayer1.setVisibility(View.INVISIBLE);
        modeThreePlayer2.setVisibility(View.INVISIBLE);
        modeThreePlayer3.setVisibility(View.INVISIBLE);

        startingTime = System.currentTimeMillis();

    }

    public void onStart(){
        super.onStart();
        loadFromFile();
    }

    public void onClick(View view){
        if(view.getId()==R.id.modeThreeStartButton){
            modeThreeStartButton.setVisibility(View.INVISIBLE);

            modeThreePlayer1.setVisibility(View.VISIBLE);
            modeThreePlayer2.setVisibility(View.VISIBLE);
            modeThreePlayer3.setVisibility(View.VISIBLE);
            modeThreePlayer1Button.setVisibility(View.VISIBLE);
            modeThreePlayer2Button.setVisibility(View.VISIBLE);
            modeThreePlayer3Button.setVisibility(View.VISIBLE);

            startingTime = System.currentTimeMillis();
        }

        if(view.getId()==R.id.modeThreePlayer1Button){
            modeThreePlayer1Button.setVisibility(View.INVISIBLE);
            modeThreePlayer2Button.setVisibility(View.INVISIBLE);
            modeThreePlayer3Button.setVisibility(View.INVISIBLE);
            modeThreePlayer1.setVisibility(View.INVISIBLE);
            modeThreePlayer2.setVisibility(View.INVISIBLE);
            modeThreePlayer3.setVisibility(View.INVISIBLE);
            modeThreeStartButton.setVisibility(View.VISIBLE);

            player1Time = (System.currentTimeMillis() - startingTime);

            playerResult.add(new PlayerStatus("Player1", player1Time));
            saveInFile();

            Toast.makeText(this, "Player1 Win!", Toast.LENGTH_SHORT).show();

        }

        if(view.getId()==R.id.modeThreePlayer2Button){
            modeThreePlayer1Button.setVisibility(View.INVISIBLE);
            modeThreePlayer2Button.setVisibility(View.INVISIBLE);
            modeThreePlayer3Button.setVisibility(View.INVISIBLE);
            modeThreePlayer1.setVisibility(View.INVISIBLE);
            modeThreePlayer2.setVisibility(View.INVISIBLE);
            modeThreePlayer3.setVisibility(View.INVISIBLE);
            modeThreeStartButton.setVisibility(View.VISIBLE);

            player2Time = (System.currentTimeMillis() - startingTime);

            playerResult.add(new PlayerStatus("Player2", player2Time));
            saveInFile();

            Toast.makeText(this, "Player2 Win!", Toast.LENGTH_SHORT).show();

        }

        if(view.getId()==R.id.modeThreePlayer3Button){
            modeThreePlayer1Button.setVisibility(View.INVISIBLE);
            modeThreePlayer2Button.setVisibility(View.INVISIBLE);
            modeThreePlayer3Button.setVisibility(View.INVISIBLE);
            modeThreePlayer1.setVisibility(View.INVISIBLE);
            modeThreePlayer2.setVisibility(View.INVISIBLE);
            modeThreePlayer3.setVisibility(View.INVISIBLE);
            modeThreeStartButton.setVisibility(View.VISIBLE);

            player3Time = (System.currentTimeMillis() - startingTime);

            playerResult.add(new PlayerStatus("Player3", player3Time));
            saveInFile();

            Toast.makeText(this, "Player3 Win!", Toast.LENGTH_SHORT).show();

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
