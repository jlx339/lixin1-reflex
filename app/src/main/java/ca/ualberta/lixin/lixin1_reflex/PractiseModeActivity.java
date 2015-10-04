package ca.ualberta.lixin.lixin1_reflex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

import org.w3c.dom.Text;

public class PractiseModeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView practiseTime;
    private ImageButton buzzButton;
    private long reflexTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practise_mode);

        buzzButton = (ImageButton) findViewById(R.id.buzzButton);
        buzzButton.setOnClickListener(this);

        practiseTime = (TextView) findViewById(R.id.practiseTime);
        practiseTime.setVisibility(View.INVISIBLE);
    }

    public void onClick(View view){
        if(view.getId()==R.id.buzzButton){
            practiseTime.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practise_mode, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
