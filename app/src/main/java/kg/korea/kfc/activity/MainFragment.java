package kg.korea.kfc.activity;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kg.korea.kfc.R;
import kg.korea.kfc.databinding.FragmentMainBinding;

/**
 * Created by itsm02 on 2017. 3. 28..
 */

public class MainFragment extends Fragment {

    FragmentMainBinding fragmentMainBinding;

    private AnimationDrawable animationDrawable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        fragmentMainBinding.imgLoading.setBackgroundResource(R.drawable.loading);

        animationDrawable = (AnimationDrawable) fragmentMainBinding.imgLoading.getBackground();

        animationDrawable.start();

        return fragmentMainBinding.getRoot();

    }


}
