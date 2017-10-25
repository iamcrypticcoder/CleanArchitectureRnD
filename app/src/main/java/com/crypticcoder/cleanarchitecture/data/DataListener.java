package com.crypticcoder.cleanarchitecture.data;


public interface DataListener<T> {
    /**
     * When data loaded successfully, this callback will be invoked
     */
    void onDataLoaded(T data);

    void onDataNotAvailable(String errorMessage);
}
