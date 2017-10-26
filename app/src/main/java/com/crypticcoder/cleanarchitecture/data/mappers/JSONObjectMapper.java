package com.crypticcoder.cleanarchitecture.data.mappers;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public interface JSONObjectMapper<JSONObject, DomainObject> {
    JSONObject toJSONObject(DomainObject obj);
    DomainObject toDomainObject(JSONObject obj);
}
