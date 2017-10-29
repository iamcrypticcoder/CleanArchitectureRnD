package com.crypticcoder.cleanarchitecture.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Cryptic Coder on 29,October,2017
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}