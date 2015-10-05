package ca.ualberta.lixin.lixin1_reflex;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class PractiseModeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView practiseTime;
    private ImageButton buzzButton;
    private Button restartButton;

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
                saveInFile(editTime, new Date(System.currentTimeMillis()));
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
    // https://github.com/jlx339/lonelyTwitter/blob/master/app/src/main/java/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java
    private void saveInFile(String text, Date date) {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_APPEND);
            fos.write(new String(date.toString() + " | " + text)
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
