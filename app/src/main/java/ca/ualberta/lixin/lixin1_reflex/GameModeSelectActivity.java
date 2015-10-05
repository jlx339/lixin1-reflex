package ca.ualberta.lixin.lixin1_reflex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameModeSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_mode_select);

        // Navigating different Game Mode by pressing buttons in GameModeSelectActivity
        Button twoPlayerButton = (Button) findViewById(R.id.twoPlayersButton);
        twoPlayerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(GameModeSelectActivity.this, GameModeTwoPlayers.class);
                startActivity(intent);
            }
        });

        Button threePlayerButton = (Button) findViewById(R.id.threePlayersButton);
        threePlayerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(GameModeSelectActivity.this, GameModeThreePlayers.class);
                startActivity(intent);
            }
        });

        Button fourPlayerButton = (Button) findViewById(R.id.fourPlayersButton);
        fourPlayerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(GameModeSelectActivity.this, GameModeFourPlayers.class);
                startActivity(intent);
            }
        });
    }

}
