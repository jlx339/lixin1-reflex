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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class MyStatisticPractise extends AppCompatActivity {

    private ArrayList<PlayerStatus> playerResult = new ArrayList<PlayerStatus>();

    private TextView minTime10;
    private TextView minTime100;
    private TextView maxTime10;
    private TextView maxTime100;
    private TextView avrTime10;
    private TextView avrTime100;
    private TextView medTime10;
    private TextView medTime100;

    private int total;
    private ArrayList calculateList;

    private String FILENAME = "filePractiseMode.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_statistic_practise);

        minTime10 = (TextView) findViewById(R.id.minTime10);

        minTime100 = (TextView) findViewById(R.id.minTime100);

        maxTime10 = (TextView) findViewById(R.id.maxTime10);

        maxTime100 = (TextView) findViewById(R.id.maxTime100);

        avrTime10 = (TextView) findViewById(R.id.avrTime10);

        avrTime100 = (TextView) findViewById(R.id.avrTime100);

        medTime10 = (TextView) findViewById(R.id.medTime10);

        medTime100 = (TextView) findViewById(R.id.medTime100);
    }

    protected void onStart(){
        super.onStart();
        loadFromFile();

        total = playerResult.size();

        for(int i=0; i<=total; i++) {
            calculateList.add(playerResult.get(i).getReflexTime());
        }

        // sort
        Collections.sort(calculateList);

        // minTime
        minTime10.setText("MinTime10: " + String.valueOf(calculateList.get(0)));
        minTime100.setText("MinTime100: " + String.valueOf(calculateList.get(0)));

        //maxTime
        if (total >= 10 && total >= 100) {
            maxTime10.setText("MaxTime10: " + String.valueOf(calculateList.get(10 - 1)));
            maxTime100.setText("MaxTime100: " + String.valueOf(calculateList.get(total - 1)));
        }
        if (total < 10) {
            maxTime10.setText("MaxTime10: " + String.valueOf(calculateList.get(total - 1)));
            maxTime100.setText("MaxTime100: " + String.valueOf(calculateList.get(total - 1)));
        }
        if (total >10 && total < 100){
            maxTime10.setText("MaxTime10: " + String.valueOf(calculateList.get(10 - 1)));
            maxTime10.setText("MaxTime100: " + String.valueOf(calculateList.get(total - 1)));
        }

//        for(int i=0; i<=10 || i <=total; i++) {
 //           calculateList.get(0);
   //     }
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
