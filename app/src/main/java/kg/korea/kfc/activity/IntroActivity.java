package kg.korea.kfc.activity;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

        // TODO (완료) 글라이드 라이브러리 추가
        // TODO (완료) 레트로핏2 추가
        // TODO (완료) 데이터 바인딩 추가

        // TODO 네이버, 페이스북 간편 로그인 라이브러리 추가
        // TODO QR코드 라이브러리
        // TODO -> http://fallingstar.tistory.com/151
        // TODO -> https://repo1.maven.org/maven2/com/google/zxing/core/3.3.0/
        // TODO XML Parsing Jsoup

        // TODO jUnit 및 TDD 코드 추가
        // TODO MVP 패턴 (구글 아키텍처 기반 샘플코드) 추가
        // TODO 안드로이드 firebase 크래쉬 리포트
        // TODO 안드로이드 firebase 어날리틱스
        // TODO MP Android Chart library

        ActivityIntroBinding activityIntroBinding = DataBindingUtil.setContentView(this, R.layout.activity_intro);

        UtilComponent.replaceFragement(this, R.id.framelayout_fragment_main, new IntroFragment());


//        int apiVersion = Build.VERSION.SDK_INT;
//        if (apiVersion >= Build.VERSION_CODES.M) {
//            if (UtilComponent.checkCameraPermission(this)) {
//                ScanBar(null);
//            } else {
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
//            }
//        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1:
                boolean isGranted = (grantResults[0] == PackageManager.PERMISSION_GRANTED);
                if (isGranted) {
                    ScanBar(null);
                } else {
                    finish();
                }
                break;
        }
    }


    static final String SCAN = "com.google.zxing.client.android.SCAN";

    public void ScanBar(View view) {
        try {
            //this intent is used to call start for bar code
            Intent in = new Intent(SCAN);
            in.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(in, 0);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
//            showDialog(MainActivity.this, "No scanner found", "Download Scanner code Activity?", " Yes", "No").show();
        }
    }

}
