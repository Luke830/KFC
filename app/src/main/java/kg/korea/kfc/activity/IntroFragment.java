package kg.korea.kfc.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kg.korea.kfc.R;
import kg.korea.kfc.databinding.FragmentIntroBinding;

/**
 * Created by itsm02 on 2017. 3. 28..
 */

public class IntroFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentIntroBinding fragmentIntroBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro, container, false);
        return fragmentIntroBinding.getRoot();

    }
}
