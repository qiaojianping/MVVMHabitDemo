package com.jianping.mymvvm.login.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.jianping.mymvvm.data.MvvmRepository;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * @Author: jianping.qiao 253386581@qq.com
 * @Maintainer: jianping.qiao 253386581@qq.com
 * @Date: 2019/7/16
 * @Copyright: 2019 www.andriodtvdev.com Inc. All rights reserved.
 */
public class LoginViewModel extends BaseViewModel<MvvmRepository> {
    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("");
    //密码的绑定
    public ObservableField<String> pwd = new ObservableField<>("");

    public LoginViewModel(@NonNull final Application application,MvvmRepository repository) {
        super(application,repository);
        //从本地取得数据绑定到View层
        userName.set(model.getUserName());
        pwd.set(model.getPassword());
    }

    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            login();
        }
    });

    private void login() {
        if (TextUtils.isEmpty(userName.get()) || TextUtils.isEmpty(pwd.get())) {
            ToastUtils.showShort("请输入账号和密码！");
            return;
        }

        addSubscribe(model.login()
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog();
                    }
                })
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        dismissDialog();
                        model.saveUserName(userName.get());
                        model.savePassword(pwd.get());
                    }
                }));
    }
}
