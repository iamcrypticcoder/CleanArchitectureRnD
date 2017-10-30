package com.crypticcoder.cleanarchitecture.data.mappers.impl;

import com.crypticcoder.cleanarchitecture.data.mappers.JSONObjectMapper;
import com.crypticcoder.cleanarchitecture.domain.model.User;

import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by Cryptic Coder on 30,October,2017
 */

public class JSONObjectUserMapper implements JSONObjectMapper<JSONObject, User> {

    @Inject
    public JSONObjectUserMapper() {

    }

    @Override
    public JSONObject toJSONObject(User obj) {
        return null;
    }

    @Override
    public User toDomainObject(JSONObject obj) {
        return null;
    }
}
