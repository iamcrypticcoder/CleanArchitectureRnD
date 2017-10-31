package com.crypticcoder.cleanarchitecture.di.components;

import com.crypticcoder.cleanarchitecture.di.ActivityScope;
import com.crypticcoder.cleanarchitecture.di.modules.InteractorModule;
import com.crypticcoder.cleanarchitecture.di.modules.MainActivityModule;
import com.crypticcoder.cleanarchitecture.presentation.ui.activities.MainActivity;
import com.crypticcoder.cleanarchitecture.presentation.ui.fragments.BookListFragment;
import com.crypticcoder.cleanarchitecture.presentation.ui.fragments.BookDetailFragment;
import com.crypticcoder.cleanarchitecture.presentation.ui.fragments.BookListFragmentNew;

import dagger.Subcomponent;

/**
 * Created by Cryptic Coder on 30,October,2017
 */

@ActivityScope
@Subcomponent(modules = {InteractorModule.class, MainActivityModule.class})
public interface MainActivityComponent {
    void inject(MainActivity activity);
    void inject(BookDetailFragment fragment);
    void inject(BookListFragment fragment);

    void inject(BookListFragmentNew fragment);
}
