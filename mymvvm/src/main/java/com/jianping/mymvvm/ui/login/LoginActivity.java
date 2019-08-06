package com.jianping.mymvvm.ui.login;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import com.jianping.mymvvm.R;
import com.jianping.mymvvm.BR;
import com.jianping.mymvvm.app.AppViewModelFactory;
import com.jianping.mymvvm.databinding.ActivityLoginBinding;

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

    @Override
    public void initViewObservable() {
        viewModel.uiChangeObservable.pwdEvent.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(viewModel.uiChangeObservable.pwdEvent.getValue()){
                    binding.ivSwichPasswrod.setImageResource(R.mipmap.show_psw_press);
                    binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    binding.ivSwichPasswrod.setImageResource(R.mipmap.show_psw);
                    binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }
}
