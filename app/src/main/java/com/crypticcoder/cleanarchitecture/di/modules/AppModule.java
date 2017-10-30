package com.crypticcoder.cleanarchitecture.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.crypticcoder.cleanarchitecture.MyApplication;
import com.crypticcoder.cleanarchitecture.domain.executor.Executor;
import com.crypticcoder.cleanarchitecture.domain.executor.MainThread;
import com.crypticcoder.cleanarchitecture.domain.executor.impl.ThreadExecutor;
import com.crypticcoder.cleanarchitecture.threading.MainThreadImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cryptic Coder on 28,October,2017
 */

@Module
public class AppModule {
    private final MyApplication mApplication;

    public AppModule(MyApplication application) {
        mApplication = application;
    }

    @Provides
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    Context providesApplicationContext() {
        return mApplication;
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences("shared-pref-name", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    Executor provideExecutor(ThreadExecutor threadExecutor) {
        return threadExecutor;
    }

    @Provides
    @Singleton
    MainThread provideMainThread(MainThreadImpl mainThread) {
        return mainThread;
    }
}