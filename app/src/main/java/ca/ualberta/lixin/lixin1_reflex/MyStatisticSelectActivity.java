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

    }

}
