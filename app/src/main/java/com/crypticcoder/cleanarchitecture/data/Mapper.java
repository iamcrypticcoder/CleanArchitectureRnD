package com.crypticcoder.cleanarchitecture.data;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public interface Mapper<From, To> {
    To map(From obj);
}
