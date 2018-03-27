package tk.dalpiazsolutions.calculatorchallenge;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();

    TextView txtStandings;
    TextView txtCalculation;
    TextView txtNumberOne;
    TextView txtNumberTwo;
    TextView txtNumberThree;
    TextView txtNumberFour;

    int counter = 10;

    MainController mainController;
    MainModel mainModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtStandings = findViewById(R.id.textStandings);
        txtCalculation = findViewById(R.id.textCalculations);
        txtNumberOne = findViewById(R.id.textNumberOne);
        txtNumberTwo = findViewById(R.id.textNumberTwo);
        txtNumberThree = findViewById(R.id.textNumberThree);
        txtNumberFour = findViewById(R.id.textNumberFour);

        final Thread thread = new Thread() {
            TextView txtTimeLeft = findViewById(R.id.textTimeLeft);
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    txtTimeLeft.setText(String.format(Locale.getDefault(), getString(R.string.number), counter));
                    if(counter==0)
                    {
                        mainController.gameOver();
                        return;
                    }
                    counter--;
                    handler.postDelayed(runnable, 1000);
                }
            };

            @Override
            public void run() {
                super.run();
                handler.postDelayed(runnable, 0);
            }
        };

        mainController = new MainController(this);
        mainController.startCalc();
        thread.start();
    }

    public void update(MainModel mainModel)
    {
        this.mainModel = mainModel;
        counter = 10;
        txtCalculation.setText((String.format(Locale.getDefault(), getString(R.string.number), mainModel.getNumberOneCalc())) + mainModel.getOperatorText() + String.format(Locale.getDefault(), getString(R.string.number), mainModel.getNumberTwoCalc()));
        txtNumberOne.setText(String.format(Locale.getDefault(), getString(R.string.number), mainModel.getAnswerNumberOne()));
        txtNumberTwo.setText(String.format(Locale.getDefault(), getString(R.string.number), mainModel.getAnswerNumberTwo()));
        txtNumberThree.setText(String.format(Locale.getDefault(), getString(R.string.number), mainModel.getAnswerNumberThree()));
        txtNumberFour.setText(String.format(Locale.getDefault(), getString(R.string.number), mainModel.getAnswerNumberFour()));
    }

    public void onClick(View view)
    {
        TextView textView = (TextView) view;
        mainController.checkAnswer(Integer.parseInt(textView.getText().toString()));
        txtStandings.setText(String.format(Locale.getDefault(), getString(R.string.standings), mainModel.getCorrectCounter(), mainModel.getCounter()));
    }
}
