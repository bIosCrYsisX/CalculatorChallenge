package tk.dalpiazsolutions.calculatorchallenge;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Christoph on 28.03.2018.
 */

public class PreferenceManager {

    Context context;
    SharedPreferences prefs;
    String PREF_FILE = "scorePrefs";
    public PreferenceManager(Context context)
    {
        this.context = context;
    }

    public void loadPrefs()
    {
        prefs = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
    }

    public int getHighScore()
    {
        return prefs.getInt("highscore", 0);
    }

    public void setHighScore(int score)
    {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("highscore", score);
        editor.apply();
    }
}
