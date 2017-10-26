package com.crypticcoder.cleanarchitecture.data;

/**
 * Created by mahbub on 3/5/17.
 */

public interface DeleteListener {
    public void onSuccess();

    /**
     * @param failureMessage
     */
    public void onFailed(String failureMessage);
}
