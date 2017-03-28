package kg.korea.kfc.activity;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import kg.korea.kfc.R;
import kg.korea.kfc.databinding.ActivityIntroBinding;
import kg.korea.kfc.util.UtilComponent;

/**
 * Created by itsm02 on 2017. 3. 28..
 */

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityIntroBinding activityIntroBinding = DataBindingUtil.setContentView(this, R.layout.activity_intro);

        UtilComponent.replaceFragement(this, R.id.framelayout_fragment_main, new IntroFragment());

    }

}
