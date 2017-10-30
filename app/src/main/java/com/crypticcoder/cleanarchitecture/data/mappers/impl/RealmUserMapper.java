package com.crypticcoder.cleanarchitecture.data.mappers.impl;

import com.crypticcoder.cleanarchitecture.data.mappers.RealmObjectMapper;
import com.crypticcoder.cleanarchitecture.data.models.RealmUser;
import com.crypticcoder.cleanarchitecture.domain.model.User;

import javax.inject.Inject;

/**
 * Created by Cryptic Coder on 30,October,2017
 */

public class RealmUserMapper implements RealmObjectMapper<RealmUser, User> {

    @Inject
    public RealmUserMapper() {

    }

    @Override
    public RealmUser toRealmObject(User object) {
        return null;
    }

    @Override
    public User toDomainObject(RealmUser object) {
        return null;
    }
}
