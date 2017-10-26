import android.content.Context;
import android.support.multidex.MultiDexApplication;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public class MyApplication extends MultiDexApplication {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

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
}
