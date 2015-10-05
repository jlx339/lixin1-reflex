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

public class MyStatisticSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_statistic_select);

        // Navigating to different MyStatistic Mode by buttons.
        Button practiseStatisticsButton = (Button) findViewById(R.id.practiseStatisticsButton);
        practiseStatisticsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MyStatisticSelectActivity.this, MyStatisticPractise.class);
                startActivity(intent);
            }
        });

        Button gameStatisticsButton = (Button) findViewById(R.id.gameStatisticsButton);
        gameStatisticsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MyStatisticSelectActivity.this, MyStatisticGame.class);
                startActivity(intent);
            }
        });

    }

}
