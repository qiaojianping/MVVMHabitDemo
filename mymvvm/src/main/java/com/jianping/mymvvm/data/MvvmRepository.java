package com.jianping.mymvvm.data;

import com.jianping.mymvvm.bean.DemoEntity;
import com.jianping.mymvvm.data.source.HttpDataSource;
import com.jianping.mymvvm.data.source.LocalDataSource;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.base.BaseModel;
import me.goldze.mvvmhabit.http.BaseResponse;

public class MvvmRepository extends BaseModel implements LocalDataSource, HttpDataSource {
    private volatile static MvvmRepository INSTANCE = null;

    private final LocalDataSource mLocalDataSource;
    private final HttpDataSource mHttpDataSource;

    public MvvmRepository(LocalDataSource localDataSource, HttpDataSource httpDataSource) {
        this.mLocalDataSource = localDataSource;
        this.mHttpDataSource = httpDataSource;
    }

    public static MvvmRepository getInstance(LocalDataSource localDataSource, HttpDataSource httpDataSource) {
        if (INSTANCE == null) {
            synchronized (MvvmRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MvvmRepository(localDataSource, httpDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void saveUserName(String userName) {
        mLocalDataSource.saveUserName(userName);
    }

    @Override
    public void savePassword(String password) {
        mLocalDataSource.savePassword(password);
    }

    @Override
    public String getUserName() {
        return mLocalDataSource.getUserName();
    }

    @Override
    public String getPassword() {
        return mLocalDataSource.getPassword();
    }

    @Override
    public Observable<Object> login() {
        return mHttpDataSource.login();
    }

    @Override
    public Observable<BaseResponse<DemoEntity>> demoGet() {
        return mHttpDataSource.demoGet();
    }

    @Override
    public Observable<BaseResponse<DemoEntity>> demoPost(String catalog) {
        return mHttpDataSource.demoPost(catalog);
    }
}
