package ca.ualberta.lixin.lixin1_reflex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PractiseModeInstructionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practise_mode_instruction);

        // Navigating to PractiseModeActivity by button.
        Button startPractiseButton = (Button) findViewById(R.id.startPractiseButton);
        startPractiseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(PractiseModeInstructionActivity.this, PractiseModeActivity.class);
                startActivity(intent);
            }
        });
    }
}
