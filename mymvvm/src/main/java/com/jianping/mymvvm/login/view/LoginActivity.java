package com.jianping.mymvvm.login.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import com.jianping.mymvvm.R;
import com.jianping.mymvvm.BR;
import com.jianping.mymvvm.app.AppViewModelFactory;
import com.jianping.mymvvm.databinding.ActivityLoginBinding;
import com.jianping.mymvvm.login.viewmodel.LoginViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    public int initContentView(final Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModle;
    }

    @Override
    public LoginViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this,factory).get(LoginViewModel.class);
    }
}
