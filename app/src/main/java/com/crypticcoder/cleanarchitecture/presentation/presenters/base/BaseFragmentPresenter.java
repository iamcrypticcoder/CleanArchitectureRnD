package com.crypticcoder.cleanarchitecture.presentation.presenters.base;

/**
 * Created by Cryptic Coder on 29,October,2017
 */

public interface BaseFragmentPresenter extends BasePresenter {
    void onCreateView();
    void onStart();
    void onResume();
    void onPause();
    void onStop();
}
