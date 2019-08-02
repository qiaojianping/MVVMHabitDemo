package com.jianping.mymvvm.app;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.jianping.mymvvm.data.MvvmRepository;
import com.jianping.mymvvm.login.viewmodel.LoginViewModel;

public class AppViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private volatile static AppViewModelFactory INSTANCE;
    private final Application mApplication;
    private final MvvmRepository mRepository;

    public AppViewModelFactory(Application application, MvvmRepository repository) {
        this.mApplication = application;
        this.mRepository = repository;
    }

    public static AppViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (AppViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppViewModelFactory(application,Injection.provideMvvmRepository());
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(mApplication, mRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
