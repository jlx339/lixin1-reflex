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
