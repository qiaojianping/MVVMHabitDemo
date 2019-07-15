package com.jianping.mymvvm.login;

import android.os.Bundle;
import com.jianping.mymvvm.R;
import com.jianping.mymvvm.BR;
import com.jianping.mymvvm.databinding.ActivityLoginBinding;
import me.goldze.mvvmhabit.base.BaseActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding,LoginViewModel> {

    @Override
    public int initContentView(final Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModle;
    }
}
