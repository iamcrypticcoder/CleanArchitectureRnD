package com.crypticcoder.cleanarchitecture.di.modules;

import com.crypticcoder.cleanarchitecture.di.ActivityScope;
import com.crypticcoder.cleanarchitecture.presentation.presenters.BookListPresenter;
import com.crypticcoder.cleanarchitecture.presentation.presenters.DetailBookPresenter;
import com.crypticcoder.cleanarchitecture.presentation.presenters.impl.BookListPresenterImpl;
import com.crypticcoder.cleanarchitecture.presentation.presenters.impl.DetailBookPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cryptic Coder on 30,October,2017
 */

@Module
public class MainActivityModule {

    @Provides
    @ActivityScope
    BookListPresenter provideBookListPresenter(BookListPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    DetailBookPresenter provideDetailBookPresenter(DetailBookPresenterImpl presenter) {
        return presenter;
    }
}
