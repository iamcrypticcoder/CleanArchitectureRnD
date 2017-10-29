package com.crypticcoder.cleanarchitecture.data.di;

import com.crypticcoder.cleanarchitecture.data.book.BookRepository;
import com.crypticcoder.cleanarchitecture.data.user.UserRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Cryptic Coder on 30,October,2017
 */

@Singleton
@Component(modules = { BookRepositoryModule.class, UserRepositoryModule.class })
public interface RepositoryComponent {

    // Exposed for parent component
    BookRepository bookRepository();
    UserRepository userRepository();
}
