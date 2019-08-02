package com.jianping.mymvvm.data.source;

import com.jianping.mymvvm.bean.DemoEntity;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;

public interface HttpDataSource {
    Observable<Object> login();

    Observable<BaseResponse<DemoEntity>> demoGet();

    Observable<BaseResponse<DemoEntity>> demoPost(String catalog);
}
