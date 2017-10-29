package com.crypticcoder.cleanarchitecture;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.crypticcoder.cleanarchitecture.data.di.BookRepositoryModule;
import com.crypticcoder.cleanarchitecture.data.di.UserRepositoryModule;
import com.crypticcoder.cleanarchitecture.di.AppComponent;
import com.crypticcoder.cleanarchitecture.di.AppModule;
import com.crypticcoder.cleanarchitecture.di.DaggerAppComponent;
import com.crypticcoder.cleanarchitecture.di.UtilModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public class MyApplication extends MultiDexApplication {

    private static Context context;

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        buildAppComponent();

        /*
        mRepositoryComponent = DaggerRepositoryComponent.builder()
                .appModule(new AppModule(this))
                .bookRepositoryModule(new BookRepositoryModule())
                .build();
        */

        MyApplication.context = getApplicationContext();

        // Configure Realm
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }

    protected void buildAppComponent() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .utilModule(new UtilModule())
                .build();

    }

    public AppComponent appComponent() {
        return mAppComponent;
    }
}
