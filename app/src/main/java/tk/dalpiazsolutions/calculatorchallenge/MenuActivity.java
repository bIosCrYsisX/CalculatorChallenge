package tk.dalpiazsolutions.calculatorchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MenuActivity extends AppCompatActivity {

    TextView txtHighscore;
    Button mbuttonStart;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        txtHighscore = findViewById(R.id.textHighScore);
        mbuttonStart = findViewById(R.id.buttonStart);

        preferenceManager = new PreferenceManager(this);
        preferenceManager.loadPrefs();

        txtHighscore.setText(String.format(Locale.getDefault(), getString(R.string.highScoreValue), preferenceManager.getHighScore()));

        mbuttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
