package com.jianping.mymvvm.ui.main;

import android.os.Bundle;

import com.jianping.mymvvm.BR;
import com.jianping.mymvvm.R;
import com.jianping.mymvvm.databinding.ActivityMainBinding;

import me.goldze.mvvmhabit.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding,MainViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModle;
    }
}
