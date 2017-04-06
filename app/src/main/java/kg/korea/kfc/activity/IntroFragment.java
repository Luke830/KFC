package kg.korea.kfc.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import kg.korea.kfc.R;
import kg.korea.kfc.databinding.FragmentIntroBinding;
import kg.korea.kfc.util.UtilComponent;
import kg.korea.network.ApiService;
import retrofit2.Retrofit;

/**
 * Created by itsm02 on 2017. 3. 28..
 */

public class IntroFragment extends Fragment {

    Retrofit retrofit;
    ApiService apiService;
    FragmentIntroBinding fragmentIntroBinding;
    Handler handler;
    Runnable runnable;

//    int x = 100, y = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentIntroBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro, container, false);
        fragmentIntroBinding.txtviewTest.setText("박인준 형 보세요");

        // TODO retrofit2 샘플코드..
//        retrofit = new Retrofit
//                .Builder()
//                .baseUrl(ApiService.SERVER_IP_REAL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        apiService = retrofit.create(ApiService.class);
//
//        Call<ResponseBody> comment = apiService.queryPayMethod("0", "hkd@abcd.com", "2169", "20161011", "1", "3");
//        comment.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                try {
//                    Log.e("DEBUG", "" + response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });


        // TODO 글라이드 샘플 코드
//        Glide.with(fragmentIntroBinding.imgviewGlide.getContext())
//                .load("http://www.gettyimagesgallery.com" + "/Images/Thumbnails/1341/134159.jpg")
//                .crossFade()
//                .into(fragmentIntroBinding.imgviewGlide);

//        fragmentIntroBinding.imgviewGlide.setImageResource(R.drawable.jamie_street);
//        fragmentIntroBinding.imgviewGlide.setScaleType(ImageView.ScaleType.MATRIX);

//        Matrix matrix = new Matrix();
//        matrix.postTranslate(x, y);
//        fragmentIntroBinding.imgviewGlide.setImageMatrix(matrix);

//        fragmentIntroBinding.imgviewGlide.scrollTo(-x, y);

        ObjectAnimator xTranslate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            xTranslate = ObjectAnimator.ofInt(fragmentIntroBinding.imgviewGlide, "scrollX", -100)
                    .setDuration(3000);
            xTranslate.setInterpolator(new AccelerateInterpolator());
            xTranslate.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {

//                    UtilComponent.replaceFragement(getActivity(), R.id.framelayout_fragment_main, new MainFragment());
                    UtilComponent.replaceFragement(getActivity(), R.id.framelayout_fragment_main, new LoginFragment());

                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            xTranslate.start();
        }
//        ObjectAnimator yTranslate = ObjectAnimator.ofInt(mScrollView, "scrollY", y);

//        fragmentIntroBinding.buttonMove1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                move(1);
//
//            }
//        });
//
//        fragmentIntroBinding.buttonMove2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                move(2);
//
//            }
//        });

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

//                move(2);
//                handler.postDelayed(this, 100);

            }
        };
        handler.postDelayed(runnable, 100);

//        TranslateAnimation ani = new TranslateAnimation(
//                Animation.RELATIVE_TO_PARENT, -0.05f,
//                Animation.RELATIVE_TO_PARENT, 0f,
//                Animation.RELATIVE_TO_PARENT, 0.0f,
//                Animation.RELATIVE_TO_PARENT, 0.0f);

//        TranslateAnimation ani = new TranslateAnimation(
//                -500,
//                100,
//                0.0f,
//                0.0f);

//        ani.setDuration(5000);
//        ani.setFillAfter(true);

//        fragmentIntroBinding.imgviewGlide.setScaleType(ImageView.ScaleType.MATRIX);
//        fragmentIntroBinding.imgviewGlide.startAnimation(ani);

//        Glide.with(fragmentIntroBinding.imgviewGlide.getContext())
//                .load(R.raw.aaa)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .into(fragmentIntroBinding.imgviewGlide);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            fragmentIntroBinding.imgviewGlide.animate().translationX(1000);
//            fragmentIntroBinding.imgviewGlide.animate().translationX(-500).setDuration(5000).withLayer();
//        }

//        fragmentIntroBinding.imgviewGlide.scrollTo(x, y);

        return fragmentIntroBinding.getRoot();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        handler.removeCallbacks(runnable);
    }

//    public void move(int flag) {
//        Matrix matrix = new Matrix();
//        switch (flag) {
//            case 1://left
//                Log.i("setMove", "left");
//                x += 2;
//                break;
//
//            case 2://right
//                Log.i("setMove", "right");
//                x -= 2;
//                break;
//        }
//
////        matrix.postTranslate(x, y); //
////        matrix.setTranslate();
////        fragmentIntroBinding.imgviewGlide.setImageMatrix(matrix);
//
//        fragmentIntroBinding.imgviewGlide.scrollTo(x, y);
//
//    }
}
