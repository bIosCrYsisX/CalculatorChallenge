package tk.dalpiazsolutions.calculatorchallenge;

import android.widget.Toast;

import java.util.Random;

/**
 * Created by Christoph on 27.03.2018.
 */

public class MainController {

    private MainActivity mainActivity;
    private MainModel mainModel;
    private Random random = new Random();
    private int result;

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
            mainModel.setCorrectCounter(mainModel.getCorrectCounter() + 1);
        }

        mainModel.setCounter(mainModel.getCounter() + 1);
        mainModel.setGameCounter(mainModel.getGameCounter() + 1);
        this.startCalc();
    }

    public void gameOver()
    {
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
}
