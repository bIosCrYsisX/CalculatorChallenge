package tk.dalpiazsolutions.calculatorchallenge;

import android.os.Handler;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Christoph on 28.03.2018.
 */

public class TimerThread extends Thread {
    private MainActivity mainActivity;
    private MainController mainController;
    private Handler handler = new Handler();
    private int lifeCounter;
    private int timeCounter;
    TextView txtTimeLeft;
    private boolean stopThread = false;

    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(stopThread==true)
            {
                return;
            }
            txtTimeLeft.setText(String.format(Locale.getDefault(), mainActivity.getString(R.string.number), timeCounter));
            if (timeCounter == 0) {
                mainController.kill();
                if (lifeCounter == 0) {
                    return;
                } else {
                    mainController.startCalc();
                }
            }
            timeCounter--;
            handler.postDelayed(runnable, 1000);
        }
    };

    public TimerThread(MainActivity mainActivity, MainController mainController)
    {
        this.mainActivity = mainActivity;
        this.mainController = mainController;
        txtTimeLeft = mainActivity.findViewById(R.id.textTimeLeft);
    }

    @Override
    public void run() {
        super.run();
        handler.post(runnable);
    }

    public int getLifeCounter() {
        return lifeCounter;
    }

    public void setLifeCounter(int lifeCounter) {
        this.lifeCounter = lifeCounter;
    }

    public int getTimeCounter() {
        return timeCounter;
    }

    public void setTimeCounter(int timeCounter) {
        this.timeCounter = timeCounter;
    }

    public boolean isStopThread() {
        return stopThread;
    }

    public void setStopThread(boolean stopThread) {
        this.stopThread = stopThread;
    }
}
