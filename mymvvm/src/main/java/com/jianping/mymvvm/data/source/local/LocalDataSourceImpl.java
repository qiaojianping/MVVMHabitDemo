package com.jianping.mymvvm.data.source.local;

import com.jianping.mymvvm.data.source.LocalDataSource;

import me.goldze.mvvmhabit.utils.SPUtils;

public class LocalDataSourceImpl implements LocalDataSource {
    private volatile static LocalDataSourceImpl INSTANCE = null;

    public LocalDataSourceImpl() {
        //数据库Helper构建
    }

    public static LocalDataSourceImpl getInstance() {
        if(INSTANCE == null){
            synchronized (LocalDataSourceImpl.class){
                if(INSTANCE == null){
                    INSTANCE = new LocalDataSourceImpl();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void saveUserName(String userName) {
        SPUtils.getInstance().put("userName",userName);
    }

    @Override
    public void savePassword(String password) {
        SPUtils.getInstance().put("pwd",password);
    }

    @Override
    public String getUserName() {
        return SPUtils.getInstance().getString("userName");
    }

    @Override
    public String getPassword() {
        return SPUtils.getInstance().getString("pwd");
    }
}
