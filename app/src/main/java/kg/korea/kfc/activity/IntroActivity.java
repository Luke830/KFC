package kg.korea.kfc.activity;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

        // TODO 글라이드 라이브러리 추가
        // TODO 레트로핏2 추가
        // 네이버, 페이스북 간편 로그인 추가

        ActivityIntroBinding activityIntroBinding = DataBindingUtil.setContentView(this, R.layout.activity_intro);

        UtilComponent.replaceFragement(this, R.id.framelayout_fragment_main, new IntroFragment());

    }

}
