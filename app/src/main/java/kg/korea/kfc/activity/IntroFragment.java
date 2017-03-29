package kg.korea.kfc.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.io.IOException;

import kg.korea.kfc.R;
import kg.korea.kfc.databinding.FragmentIntroBinding;
import kg.korea.network.ApiService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by itsm02 on 2017. 3. 28..
 */

public class IntroFragment extends Fragment {

    Retrofit retrofit;
    ApiService apiService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentIntroBinding fragmentIntroBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro, container, false);
        fragmentIntroBinding.txtviewTest.setText("박인준 형 보세요");

        // TODO retrofit2 샘플코드..
        retrofit = new Retrofit
                .Builder()
                .baseUrl(ApiService.SERVER_IP_REAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        Call<ResponseBody> comment = apiService.queryPayMethod("0", "hkd@abcd.com", "2169", "20161011", "1", "3");
        comment.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    Log.e("DEBUG", "" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


        // TODO 글라이드 샘플 코드
        Glide.with(fragmentIntroBinding.imgviewGlide.getContext())
                .load("http://www.gettyimagesgallery.com" + "/Images/Thumbnails/1341/134159.jpg")
                .crossFade()
                .into(fragmentIntroBinding.imgviewGlide);


        return fragmentIntroBinding.getRoot();

    }
}
