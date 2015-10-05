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

public class GameModeFourPlayers extends AppCompatActivity implements View.OnClickListener{

    private ImageButton modeFourPlayer1Button;
    private ImageButton modeFourPlayer2Button;
    private ImageButton modeFourPlayer3Button;
    private ImageButton modeFourPlayer4Button;
    private Button modeFourStartButton;
    private TextView modeFourPlayer1;
    private TextView modeFourPlayer2;
    private TextView modeFourPlayer3;
    private TextView modeFourPlayer4;
    private long player1Time;
    private long player2Time;
    private long player3Time;
    private long player4Time;
    private long startingTime;

    private ArrayList<PlayerStatus> playerResult = new ArrayList<PlayerStatus>();

    private static final String FILENAME = "fileGameModeFour.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_mode_four_players);

        modeFourPlayer1Button = (ImageButton) findViewById(R.id.modeFourPlayer1Button);
        modeFourPlayer1Button.setOnClickListener(this);

        modeFourPlayer2Button = (ImageButton) findViewById(R.id.modeFourPlayer2Button);
        modeFourPlayer2Button.setOnClickListener(this);

        modeFourPlayer3Button = (ImageButton) findViewById(R.id.modeFourPlayer3Button);
        modeFourPlayer3Button.setOnClickListener(this);

        modeFourPlayer4Button = (ImageButton) findViewById(R.id.modeFourPlayer4Button);
        modeFourPlayer4Button.setOnClickListener(this);

        modeFourStartButton = (Button) findViewById(R.id.modeFourStartButton);
        modeFourStartButton.setOnClickListener(this);

        modeFourPlayer1Button.setVisibility(View.INVISIBLE);
        modeFourPlayer2Button.setVisibility(View.INVISIBLE);
        modeFourPlayer3Button.setVisibility(View.INVISIBLE);
        modeFourPlayer4Button.setVisibility(View.INVISIBLE);

        modeFourPlayer1 = (TextView) findViewById(R.id.modeFourPlayer1);
        modeFourPlayer2 = (TextView) findViewById(R.id.modeFourPlayer2);
        modeFourPlayer3 = (TextView) findViewById(R.id.modeFourPlayer3);
        modeFourPlayer4 = (TextView) findViewById(R.id.modeFourPlayer4);

        modeFourPlayer1.setVisibility(View.INVISIBLE);
        modeFourPlayer2.setVisibility(View.INVISIBLE);
        modeFourPlayer3.setVisibility(View.INVISIBLE);
        modeFourPlayer4.setVisibility(View.INVISIBLE);

        startingTime = System.currentTimeMillis();

    }

    public void onStart(){
        super.onStart();
        loadFromFile();
    }

    public void onClick(View view){
        if(view.getId()==R.id.modeFourStartButton){
            modeFourStartButton.setVisibility(View.INVISIBLE);

            modeFourPlayer1.setVisibility(View.VISIBLE);
            modeFourPlayer2.setVisibility(View.VISIBLE);
            modeFourPlayer3.setVisibility(View.VISIBLE);
            modeFourPlayer4.setVisibility(View.VISIBLE);
            modeFourPlayer1Button.setVisibility(View.VISIBLE);
            modeFourPlayer2Button.setVisibility(View.VISIBLE);
            modeFourPlayer3Button.setVisibility(View.VISIBLE);
            modeFourPlayer4Button.setVisibility(View.VISIBLE);

            startingTime = System.currentTimeMillis();
        }

        if(view.getId()==R.id.modeFourPlayer1Button){
            modeFourPlayer1Button.setVisibility(View.INVISIBLE);
            modeFourPlayer2Button.setVisibility(View.INVISIBLE);
            modeFourPlayer3Button.setVisibility(View.INVISIBLE);
            modeFourPlayer4Button.setVisibility(View.INVISIBLE);
            modeFourPlayer1.setVisibility(View.INVISIBLE);
            modeFourPlayer2.setVisibility(View.INVISIBLE);
            modeFourPlayer3.setVisibility(View.INVISIBLE);
            modeFourPlayer4.setVisibility(View.INVISIBLE);
            modeFourStartButton.setVisibility(View.VISIBLE);

            player1Time = (System.currentTimeMillis() - startingTime);

            playerResult.add(new PlayerStatus("Player1", player1Time));
            saveInFile();

            Toast.makeText(this, "Player1 Win!", Toast.LENGTH_SHORT).show();

        }

        if(view.getId()==R.id.modeFourPlayer2Button){
            modeFourPlayer1Button.setVisibility(View.INVISIBLE);
            modeFourPlayer2Button.setVisibility(View.INVISIBLE);
            modeFourPlayer3Button.setVisibility(View.INVISIBLE);
            modeFourPlayer4Button.setVisibility(View.INVISIBLE);
            modeFourPlayer1.setVisibility(View.INVISIBLE);
            modeFourPlayer2.setVisibility(View.INVISIBLE);
            modeFourPlayer3.setVisibility(View.INVISIBLE);
            modeFourPlayer4.setVisibility(View.INVISIBLE);
            modeFourStartButton.setVisibility(View.VISIBLE);

            player2Time = (System.currentTimeMillis() - startingTime);

            playerResult.add(new PlayerStatus("Player2", player2Time));
            saveInFile();

            Toast.makeText(this, "Player2 Win!", Toast.LENGTH_SHORT).show();

        }

        if(view.getId()==R.id.modeFourPlayer3Button){
            modeFourPlayer1Button.setVisibility(View.INVISIBLE);
            modeFourPlayer2Button.setVisibility(View.INVISIBLE);
            modeFourPlayer3Button.setVisibility(View.INVISIBLE);
            modeFourPlayer4Button.setVisibility(View.INVISIBLE);
            modeFourPlayer1.setVisibility(View.INVISIBLE);
            modeFourPlayer2.setVisibility(View.INVISIBLE);
            modeFourPlayer3.setVisibility(View.INVISIBLE);
            modeFourPlayer4.setVisibility(View.INVISIBLE);
            modeFourStartButton.setVisibility(View.VISIBLE);

            player3Time = (System.currentTimeMillis() - startingTime);

            playerResult.add(new PlayerStatus("Player3", player3Time));
            saveInFile();

            Toast.makeText(this, "Player3 Win!", Toast.LENGTH_SHORT).show();

        }

        if(view.getId()==R.id.modeFourPlayer4Button){
            modeFourPlayer1Button.setVisibility(View.INVISIBLE);
            modeFourPlayer2Button.setVisibility(View.INVISIBLE);
            modeFourPlayer3Button.setVisibility(View.INVISIBLE);
            modeFourPlayer4Button.setVisibility(View.INVISIBLE);
            modeFourPlayer1.setVisibility(View.INVISIBLE);
            modeFourPlayer2.setVisibility(View.INVISIBLE);
            modeFourPlayer3.setVisibility(View.INVISIBLE);
            modeFourPlayer4.setVisibility(View.INVISIBLE);
            modeFourStartButton.setVisibility(View.VISIBLE);

            player4Time = (System.currentTimeMillis() - startingTime);

            playerResult.add(new PlayerStatus("Player4", player3Time));
            saveInFile();

            Toast.makeText(this, "Player4 Win!", Toast.LENGTH_SHORT).show();
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
