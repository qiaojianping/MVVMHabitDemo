package com.jianping.mymvvm.app;

import com.jianping.mymvvm.data.MvvmRepository;
import com.jianping.mymvvm.data.source.HttpDataSource;
import com.jianping.mymvvm.data.source.LocalDataSource;
import com.jianping.mymvvm.data.source.http.HttpDataSourceImpl;
import com.jianping.mymvvm.data.source.http.service.LoginApiService;
import com.jianping.mymvvm.data.source.local.LocalDataSourceImpl;
import com.jianping.mymvvm.utils.RetrofitClient;

/**
 * 注入全局的数据仓库，可以考虑使用Dagger2。（根据项目实际情况搭建，千万不要为了架构而架构）
 */
public class Injection {
    public static MvvmRepository provideMvvmRepository(){
        //网络API服务
        LoginApiService apiService = RetrofitClient.getInstance().create(LoginApiService.class);
        //网络数据源
        HttpDataSource httpDataSource = HttpDataSourceImpl.getInstance(apiService);
        //本地数据源
        LocalDataSource localDataSource = LocalDataSourceImpl.getInstance();
        return MvvmRepository.getInstance(localDataSource,httpDataSource);
    }
}
