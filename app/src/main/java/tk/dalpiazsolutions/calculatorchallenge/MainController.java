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
    private TimerThread timerThread;
    private Random random = new Random();
    private int result;
    private ImageView lifeOne;
    private ImageView lifeTwo;
    private ImageView lifeThree;
    private PreferenceManager preferenceManager;

    public MainController(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
        mainModel = new MainModel(mainActivity);
        txtState = mainActivity.findViewById(R.id.textState);
        lifeOne = mainActivity.findViewById(R.id.imageLifeOne);
        lifeTwo = mainActivity.findViewById(R.id.imageLifeTwo);
        lifeThree = mainActivity.findViewById(R.id.imageLifeThree);
        preferenceManager = new PreferenceManager(mainActivity);
        timerThread = new TimerThread(mainActivity, this);
        timerThread.setLifeCounter(3);
        timerThread.run();
    }

    public void startCalc()
    {
        timerThread.setStopThread(false);
        setDifficulty();
        calcNumbers();
        calcResult();
        calcAnswers();
        mainModel.setTexts();
    }

    public void calcNumbers()
    {
        mainModel.setNumberOneCalc(calcRandomNumber(0));
        mainModel.setNumberTwoCalc(calcRandomNumber(1));
        mainModel.setOperatorCalc(calcRandomNumber(2));
    }

    public void calcResult()
    {
        switch (mainModel.getOperatorCalc())
        {
            case 0: result = mainModel.getNumberOneCalc() + mainModel.getNumberTwoCalc(); mainModel.setOperatorText("+"); break;
            case 1: result = mainModel.getNumberOneCalc() - mainModel.getNumberTwoCalc(); mainModel.setOperatorText("-"); break;
            case 2: result = mainModel.getNumberOneCalc() * mainModel.getNumberTwoCalc(); mainModel.setOperatorText("*"); break;
            case 3: while(checkRest(mainModel.getNumberOneCalc(), mainModel.getNumberTwoCalc()) != 0)
                    {
                        mainModel.setNumberOneCalc(calcRandomNumber(1));
                    }
                    result = mainModel.getNumberOneCalc() / mainModel.getNumberTwoCalc();
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
        timerThread.setStopThread(true);
        this.startCalc();
    }

    public void gameOver()
    {
        txtState.setText(String.format(Locale.getDefault(), mainActivity.getString(R.string.endScore), mainModel.getCounter()));
        Toast.makeText(mainActivity.getApplicationContext(), "Game over!", Toast.LENGTH_LONG).show();

        preferenceManager.loadPrefs();
        if(mainModel.getCounter() > preferenceManager.getHighScore())
        {
            preferenceManager.setHighScore(mainModel.getCounter());
            Toast.makeText(mainActivity.getApplicationContext(), mainActivity.getString(R.string.highScore), Toast.LENGTH_LONG).show();
        }
        timerThread.setStopThread(true);
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
        timerThread.setLifeCounter(timerThread.getLifeCounter() - 1);
        if(timerThread.getLifeCounter()==2)
        {
            lifeThree.setVisibility(View.GONE);
        }

        else if(timerThread.getLifeCounter()==1)
        {
            lifeTwo.setVisibility(View.GONE);
        }

        else if(timerThread.getLifeCounter()==0)
        {
            lifeOne.setVisibility(View.GONE);
            mainActivity.gameOver();
            gameOver();
        }
        timerThread.setStopThread(true);
    }

    public void setDifficulty()
    {
        mainModel.setDifficulty(0);
        timerThread.setTimeCounter(20);

        if(mainModel.getCounter() > 10 && mainModel.getCounter() <= 20)
        {
            mainModel.setDifficulty(1);
            timerThread.setTimeCounter(15);
        }

        else if(mainModel.getCounter() > 20 && mainModel.getCounter() <= 30)
        {
            mainModel.setDifficulty(2);
            timerThread.setTimeCounter(10);
        }

        else if(mainModel.getCounter() > 30)
        {
            mainModel.setDifficulty(3);
            timerThread.setTimeCounter(5);
        }
    }

    public int calcRandomNumber(int mode)
    {
        if(mainModel.getDifficulty() == 0)
        {
            if(mode == 1)
            {
                return (random.nextInt(10) + 1);
            }

            else if (mode == 2)
            {
                return random.nextInt(2);
            }

            else
            {
                return random.nextInt(11);
            }
        }

        else if(mainModel.getDifficulty() == 1)
        {
            if(mode == 1)
            {
                return (random.nextInt(10) + 1);
            }

            else if (mode == 2)
            {
                return random.nextInt(3);
            }

            else
            {
                return random.nextInt(11);
            }
        }

        else if(mainModel.getDifficulty() == 2)
        {
            if(mode == 1)
            {
                return (random.nextInt(10) + 1);
            }

            else if (mode == 2)
            {
                return random.nextInt(4);
            }

            else
            {
                return random.nextInt(11);
            }
        }

        else
        {
            if(mode == 1)
            {
                return (random.nextInt(20) + 1);
            }

            else if (mode == 2)
            {
                return random.nextInt(4);
            }

            else
            {
                return random.nextInt(21);
            }
        }
    }
}
