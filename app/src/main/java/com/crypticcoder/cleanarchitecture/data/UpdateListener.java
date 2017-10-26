package com.crypticcoder.cleanarchitecture.data;

/**
 * Created by mahbub on 3/5/17.
 */

public interface UpdateListener<T> {
    /**
     * @param entity
     */
    void onSuccess(T entity);

    /**
     * @param failureMessage
     */
    void onFailed(String failureMessage);
}
