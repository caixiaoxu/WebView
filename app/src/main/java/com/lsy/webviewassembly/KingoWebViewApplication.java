package com.lsy.webviewassembly;

import com.kingja.loadsir.core.LoadSir;
import com.lsy.kingobase.BaseApplication;
import com.lsy.kingobase.loadsir.CustomCallback;
import com.lsy.kingobase.loadsir.EmptyCallback;
import com.lsy.kingobase.loadsir.ErrorCallback;
import com.lsy.kingobase.loadsir.LoadingCallback;
import com.lsy.kingobase.loadsir.TimeoutCallback;

public class KingoWebViewApplication extends BaseApplication {

    @Override
    public void onCreate(){
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
