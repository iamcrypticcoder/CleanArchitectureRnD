package com.crypticcoder.cleanarchitecture.data;

import java.util.List;

/**
 * Created by MAHBUB on 6/19/2016.
 */
public interface DataListListener<T> {
    /**
     * When data list loaded successfully, this callback will be invoked
     */
    void onDataListLoaded(List<T> dataList);

    void onDataNotAvailable(String errorMessage);
}
