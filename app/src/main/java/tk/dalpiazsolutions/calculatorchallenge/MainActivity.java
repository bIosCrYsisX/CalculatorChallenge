package tk.dalpiazsolutions.calculatorchallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView txtStandings;
    TextView txtCalculation;
    TextView txtNumberOne;
    TextView txtNumberTwo;
    TextView txtNumberThree;
    TextView txtNumberFour;
    Button mbuttonPlayAgain;

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
        mbuttonPlayAgain = findViewById(R.id.buttonPlayAgain);

        mbuttonPlayAgain.setVisibility(View.GONE);

        txtStandings.setText("0");

        mainController = new MainController(this);
        mainController.startCalc();
    }

    public void update(MainModel mainModel)
    {
        this.mainModel = mainModel;
        txtCalculation.setText((String.format(Locale.getDefault(), getString(R.string.calculation), mainModel.getNumberOneCalc(), mainModel.getOperatorText(), mainModel.getNumberTwoCalc())));
        txtNumberOne.setText(String.format(Locale.getDefault(), getString(R.string.number), mainModel.getAnswerNumberOne()));
        txtNumberTwo.setText(String.format(Locale.getDefault(), getString(R.string.number), mainModel.getAnswerNumberTwo()));
        txtNumberThree.setText(String.format(Locale.getDefault(), getString(R.string.number), mainModel.getAnswerNumberThree()));
        txtNumberFour.setText(String.format(Locale.getDefault(), getString(R.string.number), mainModel.getAnswerNumberFour()));
    }

    public void onClick(View view)
    {
        TextView textView = (TextView) view;
        mainController.checkAnswer(Integer.parseInt(textView.getText().toString()));
        txtStandings.setText(String.format(Locale.getDefault(), getString(R.string.number), mainModel.getCounter()));
    }

    public void playAgain(View view)
    {
        this.recreate();
    }

    public void gameOver()
    {
        txtNumberOne.setEnabled(false);
        txtNumberTwo.setEnabled(false);
        txtNumberThree.setEnabled(false);
        txtNumberFour.setEnabled(false);
        mbuttonPlayAgain.setVisibility(View.VISIBLE);
    }
}
