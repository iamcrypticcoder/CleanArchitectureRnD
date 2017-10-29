package com.crypticcoder.cleanarchitecture.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.crypticcoder.cleanarchitecture.MyApplication;
import com.crypticcoder.cleanarchitecture.data.book.BookRepository;
import com.crypticcoder.cleanarchitecture.data.di.RepositoryComponent;
import com.crypticcoder.cleanarchitecture.data.user.UserRepository;
import com.crypticcoder.cleanarchitecture.domain.executor.Executor;
import com.crypticcoder.cleanarchitecture.domain.executor.MainThread;
import com.crypticcoder.cleanarchitecture.domain.executor.impl.ThreadExecutor;
import com.crypticcoder.cleanarchitecture.presentation.ui.activities.MainActivityOld;
import com.crypticcoder.cleanarchitecture.data.di.BookRepositoryModule;
import com.crypticcoder.cleanarchitecture.data.di.UserRepositoryModule;
import com.crypticcoder.cleanarchitecture.threading.MainThreadImpl;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;

/**
 * Created by Cryptic Coder on 28,October,2017
 */
/*
@Singleton
@Component(modules = {
        AppModule.class,
        UtilModule.class,
        BookRepositoryModule.class,
        UserRepositoryModule.class
})
public interface AppComponent {
    void inject(MainActivityOld activity);
}
*/

/*
@Singleton
@Component(modules = {
        AppModule.class,
        UtilModule.class
})
public interface AppComponent {
    void inject(MyApplication myApplication);

    Application getApplication();

    Executor getExecutor();

    MainThread getMainThread();
}
*/

@Singleton
@Component(dependencies = RepositoryComponent.class, modules = { AppModule.class, UtilModule.class })
public interface AppComponent {

    // Exposed for parent component that will use this comp as dependency
    Context context();
    SharedPreferences sharedPreferences();
    ThreadExecutor threadExecutor();
    MainThreadImpl mainThread();

    BookRepository bookRepository();
    UserRepository userRepository();
}