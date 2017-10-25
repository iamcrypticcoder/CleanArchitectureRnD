package com.crypticcoder.cleanarchitecture.threading;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

import android.os.Handler;
import android.os.Looper;

import com.crypticcoder.cleanarchitecture.domain.executor.MainThread;

/**
 * This class makes sure that the runnable we provide will be run on the main UI thread.
 */
public class MainThreadImpl implements MainThread {

    private static MainThread sMainThread;

    private Handler mHandler;

    private MainThreadImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static MainThread getInstance() {
        if (sMainThread == null) {
            sMainThread = new MainThreadImpl();
        }

        return sMainThread;
    }
}