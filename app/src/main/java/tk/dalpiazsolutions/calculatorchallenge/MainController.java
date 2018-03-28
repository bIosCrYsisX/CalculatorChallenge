package tk.dalpiazsolutions.calculatorchallenge;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

/**
 * Created by Christoph on 27.03.2018.
 */

public class MainController {

    private TextView txtState;
    private MainActivity mainActivity;
    private MainModel mainModel;
    private Random random = new Random();
    private int result;
    private int lifeCounter = 3;
    ImageView lifeOne;
    ImageView lifeTwo;
    ImageView lifeThree;

    public MainController(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
        mainModel = new MainModel(mainActivity);
        txtState = mainActivity.findViewById(R.id.textState);
        lifeOne = mainActivity.findViewById(R.id.imageLifeOne);
        lifeTwo = mainActivity.findViewById(R.id.imageLifeTwo);
        lifeThree = mainActivity.findViewById(R.id.imageLifeThree);
    }

    public void startCalc()
    {
        calcRandomNumberOne();
        calcRandomNumberTwo();
        calcOperator();
        calcResult();
        calcAnswers();
        mainModel.setTexts();
    }

    public void calcOperator()
    {
        mainModel.setOperatorCalc(random.nextInt(4));
    }

    public void calcRandomNumberOne()
    {
        mainModel.setNumberOneCalc(random.nextInt(21));
    }

    public void calcRandomNumberTwo()
    {
        mainModel.setNumberTwoCalc(random.nextInt(21));
    }

    public void calcResult()
    {
        switch (mainModel.getOperatorCalc())
        {
            case 0: result = mainModel.getNumberOneCalc() + mainModel.getNumberTwoCalc(); mainModel.setOperatorText("+"); break;
            case 1: result = mainModel.getNumberOneCalc() - mainModel.getNumberTwoCalc(); mainModel.setOperatorText("-"); break;
            case 2: result = mainModel.getNumberOneCalc() * mainModel.getNumberTwoCalc(); mainModel.setOperatorText("*"); break;
            case 3: while (mainModel.getNumberTwoCalc() == 0)
                    {
                        calcRandomNumberTwo();
                    }

                    while(checkRest(mainModel.getNumberOneCalc(), mainModel.getNumberTwoCalc()) != 0)
                    {
                        calcRandomNumberOne();
                    }
                    result = Math.round(mainModel.getNumberOneCalc() / (float) mainModel.getNumberTwoCalc());
                    mainModel.setOperatorText("/");
                    break;
        }
        mainModel.setResult(result);
    }

    public void calcAnswers()
    {
        switch (random.nextInt(4)){
            case 0: mainModel.setAnswerNumberOne(mainModel.getResult());
                    mainModel.setAnswerNumberTwo(generateRandomAnswer());
                    mainModel.setAnswerNumberThree(generateRandomAnswer());
                    mainModel.setAnswerNumberFour(generateRandomAnswer());
                    break;

            case 1: mainModel.setAnswerNumberTwo(mainModel.getResult());
                    mainModel.setAnswerNumberOne(generateRandomAnswer());
                    mainModel.setAnswerNumberThree(generateRandomAnswer());
                    mainModel.setAnswerNumberFour(generateRandomAnswer());
                    break;

            case 2: mainModel.setAnswerNumberThree(mainModel.getResult());
                    mainModel.setAnswerNumberOne(generateRandomAnswer());
                    mainModel.setAnswerNumberTwo(generateRandomAnswer());
                    mainModel.setAnswerNumberFour(generateRandomAnswer());
                    break;

            case 3: mainModel.setAnswerNumberFour(mainModel.getResult());
                    mainModel.setAnswerNumberOne(generateRandomAnswer());
                    mainModel.setAnswerNumberTwo(generateRandomAnswer());
                    mainModel.setAnswerNumberThree(generateRandomAnswer());
                    break;
        }
    }

    public void checkAnswer(int answer)
    {
        if(answer == mainModel.getResult())
        {
            txtState.setText(mainActivity.getString(R.string.correct));
            mainModel.setCounter(mainModel.getCounter() + 1);
        }

        else
        {
            txtState.setText(mainActivity.getString(R.string.wrong));
            kill();
        }

        this.startCalc();
    }

    public void gameOver()
    {
        txtState.setText(String.format(Locale.getDefault(), mainActivity.getString(R.string.number), mainModel.getCounter()));
        Toast.makeText(mainActivity.getApplicationContext(), "Game over!", Toast.LENGTH_LONG).show();

    }

    public int generateRandomAnswer()
    {
        int randomNumber = random.nextInt(5);

        if(mainModel.getResult() <= 10)
        {
            while(randomNumber == mainModel.getResult())
            {
                randomNumber = random.nextInt(5);
            }

            return randomNumber;
        }

        else
        {
            return ((random.nextInt((int)(mainModel.getResult() * 0.5)) + ((int)(mainModel.getResult() * 0.5))));
        }
    }

    public int checkRest(int a, int b)
    {
        return (a % b);
    }

    public void kill()
    {
        lifeCounter--;
        if(lifeCounter==2)
        {
            lifeThree.setVisibility(View.GONE);
        }

        else if(lifeCounter==1)
        {
            lifeTwo.setVisibility(View.GONE);
        }

        else if(lifeCounter==0)
        {
            lifeOne.setVisibility(View.GONE);
            mainActivity.gameOver();
            gameOver();
        }
    }
}
