package layout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

import d4.deadline.R;

/**
 * Created by Tomi on 5.4.2017.
 */

public class Pop extends Activity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

        setContentView(R.layout.popwindow);

        //Set window size
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigh = dm.heightPixels;

        getWindow().setLayout((int) (width*.5), (int)(heigh*.5));




    }
}
