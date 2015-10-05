package ca.ualberta.lixin.lixin1_reflex;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private boolean player1Buzzed;
    private boolean player2Buzzed;
    private PlayerStatus player1;
    private PlayerStatus player2;
    private static final String FILENAME = "fileGameMode.sav";

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
        player1Buzzed = false;
        player2Buzzed = false;

    }

    public void onClick(View view){
        if(view.getId()==R.id.modeTwoStartButton){
            modeTwoStartButton.setVisibility(View.INVISIBLE);

            modeTwoPlayer1.setVisibility(View.VISIBLE);
            modeTwoPlayer2.setVisibility(View.VISIBLE);
            modeTwoPlayer1Button.setVisibility(View.VISIBLE);
            modeTwoPlayer2Button.setVisibility(View.VISIBLE);

            startingTime = System.currentTimeMillis();
            player1Buzzed = false;
            player2Buzzed = false;
        }

        if(view.getId()==R.id.modeTwoPlayer1Button){
            modeTwoPlayer1Button.setVisibility(View.INVISIBLE);
            player1Buzzed = true;

            player1Time = (System.currentTimeMillis() - startingTime);

        }

        if(view.getId()==R.id.modeTwoPlayer2Button){
            modeTwoPlayer2Button.setVisibility(View.INVISIBLE);
            player2Buzzed = true;

            player2Time = (System.currentTimeMillis() - startingTime);

        }

        if (player1Buzzed && player2Buzzed){
            modeTwoStartButton.setVisibility(View.VISIBLE);
            modeTwoPlayer1.setVisibility(View.INVISIBLE);
            modeTwoPlayer2.setVisibility(View.INVISIBLE);

            if (player1Time <= player2Time) {
                saveInFile("Player1, Player2", new Date(System.currentTimeMillis()));
                Toast.makeText(this, "Player1 Win!", Toast.LENGTH_SHORT).show();
            }
            if (player1Time > player2Time) {
                saveInFile("Player2, Player1", new Date(System.currentTimeMillis()));
                Toast.makeText(this, "Player2 Win!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveInFile(String text, Date date) {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_APPEND);
            fos.write(new String(" | 2 " + date.toString() + " | " + text)
                    .getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
