package com.jianping.mymvvm.data.source.http;

import com.jianping.mymvvm.bean.DemoEntity;
import com.jianping.mymvvm.data.source.HttpDataSource;
import com.jianping.mymvvm.data.source.http.service.LoginApiService;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;

public class HttpDataSourceImpl implements HttpDataSource {

    private LoginApiService apiService;
    private volatile static HttpDataSourceImpl INSTANCE = null;

    public static HttpDataSourceImpl getInstance(LoginApiService apiService) {
        if(INSTANCE ==null){
            synchronized (HttpDataSourceImpl.class){
                if(INSTANCE == null){
                    INSTANCE= new HttpDataSourceImpl(apiService);
                }
            }
        }
        return INSTANCE;
    }

    public HttpDataSourceImpl(LoginApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<Object> login() {
        return Observable.just(new Object()).delay(3, TimeUnit.SECONDS);//延迟3秒
    }

    @Override
    public Observable<BaseResponse<DemoEntity>> demoGet() {
        return apiService.demoGet();
    }

    @Override
    public Observable<BaseResponse<DemoEntity>> demoPost(String catalog) {
        return null;
    }
}
