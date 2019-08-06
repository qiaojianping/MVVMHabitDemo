package com.jianping.mymvvm.ui.login;

import android.app.Application;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.jianping.mymvvm.data.MvvmRepository;
import com.jianping.mymvvm.ui.main.MainActivity;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
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
    //用户名清除
    public ObservableInt clearUserName = new ObservableInt();
    //密码的绑定
    public ObservableField<String> pwd = new ObservableField<>("");
    //封装一个界面发生改变的观察者
    public UIChangeObservable uiChangeObservable = new UIChangeObservable();

    public class UIChangeObservable{
        public SingleLiveEvent<Boolean> pwdEvent = new SingleLiveEvent<>();
    }

    public LoginViewModel(@NonNull final Application application,MvvmRepository repository) {
        super(application,repository);
        //从本地取得数据绑定到View层
        userName.set(model.getUserName());
        pwd.set(model.getPassword());
    }

    public BindingCommand clearUserNameOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            userName.set("");
        }
    });

    public BindingCommand<Boolean> onFocusChangeCommand = new BindingCommand<>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean hasFocus) {
            if(hasFocus){
                clearUserName.set(View.VISIBLE);
            }else {
                clearUserName.set(View.INVISIBLE);
            }
        }
    });

    public BindingCommand showPwdOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uiChangeObservable.pwdEvent.setValue(uiChangeObservable.pwdEvent.getValue() == null ||
                    !uiChangeObservable.pwdEvent.getValue());
        }
    });

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
                        startActivity(MainActivity.class);
                        finish();
                    }
                }));
    }
}
