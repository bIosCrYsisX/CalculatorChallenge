package tk.dalpiazsolutions.calculatorchallenge;

import android.widget.Toast;

import java.util.Random;
import java.util.logging.Handler;

/**
 * Created by Christoph on 27.03.2018.
 */

public class MainController {

    private MainActivity mainActivity;
    private MainModel mainModel;
    Random random = new Random();
    int randomCorrectAnswer;
    int result;


    public MainController(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
        mainModel = new MainModel(mainActivity);
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
            case 3: while (mainModel.getNumberTwoCalc()==0) { calcRandomNumberTwo(); }  result = Math.round(mainModel.getNumberOneCalc() / (float) mainModel.getNumberTwoCalc()); mainModel.setOperatorText("/");  break;
        }
        mainModel.setResult(result);
    }

    public void calcAnswers()
    {
        randomCorrectAnswer = random.nextInt(4);

        switch (randomCorrectAnswer){
            case 0: mainModel.setAnswerNumberOne(mainModel.getResult());
                    mainModel.setAnswerNumberTwo(random.nextInt(20));
                    mainModel.setAnswerNumberThree(random.nextInt(20));
                    mainModel.setAnswerNumberFour(random.nextInt(20));

            case 1: mainModel.setAnswerNumberTwo(mainModel.getResult());
                    mainModel.setAnswerNumberOne(random.nextInt(20));
                    mainModel.setAnswerNumberThree(random.nextInt(20));
                    mainModel.setAnswerNumberFour(random.nextInt(20));

            case 2: mainModel.setAnswerNumberThree(mainModel.getResult());
                    mainModel.setAnswerNumberOne(random.nextInt(20));
                    mainModel.setAnswerNumberTwo(random.nextInt(20));
                    mainModel.setAnswerNumberFour(random.nextInt(20));

            case 3: mainModel.setAnswerNumberFour(mainModel.getResult());
                    mainModel.setAnswerNumberOne(random.nextInt(20));
                    mainModel.setAnswerNumberTwo(random.nextInt(20));
                    mainModel.setAnswerNumberThree(random.nextInt(20));
        }
    }

    public void checkAnswer(int answer)
    {
        if(answer == mainModel.getResult())
        {
            mainModel.setCorrectCounter(mainModel.getCorrectCounter() + 1);
        }

        mainModel.setCounter(mainModel.getCounter() + 1);

        if(mainModel.getGameCounter() < 9)
        {
            mainModel.setGameCounter(mainModel.getGameCounter() + 1);
            this.startCalc();
        }

        else
        {
            gameOver();
        }
    }

    public void gameOver()
    {
        Toast.makeText(mainActivity.getApplicationContext(), "Game over!", Toast.LENGTH_LONG).show();
    }
}
