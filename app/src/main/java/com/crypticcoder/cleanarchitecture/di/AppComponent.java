package com.crypticcoder.cleanarchitecture.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.crypticcoder.cleanarchitecture.di.components.MainActivityComponent;
import com.crypticcoder.cleanarchitecture.di.modules.AppModule;
import com.crypticcoder.cleanarchitecture.di.modules.InteractorModule;
import com.crypticcoder.cleanarchitecture.di.modules.MainActivityModule;
import com.crypticcoder.cleanarchitecture.di.modules.UtilModule;
import com.crypticcoder.cleanarchitecture.domain.executor.impl.ThreadExecutor;
import com.crypticcoder.cleanarchitecture.di.modules.BookRepositoryModule;
import com.crypticcoder.cleanarchitecture.di.modules.UserRepositoryModule;
import com.crypticcoder.cleanarchitecture.domain.interactors.base.Interactor;
import com.crypticcoder.cleanarchitecture.threading.MainThreadImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Cryptic Coder on 28,October,2017
 */
@Singleton
@Component(modules = {
        AppModule.class,
        UtilModule.class,
        BookRepositoryModule.class,
        UserRepositoryModule.class
})
public interface AppComponent {

    MainActivityComponent plus(InteractorModule interactorModule, MainActivityModule mainActivityModule);

    // Exposed for parent component that will use this comp as dependency
    /*
    Context context();
    SharedPreferences sharedPreferences();
    ThreadExecutor threadExecutor();
    MainThreadImpl mainThread();
    */
}