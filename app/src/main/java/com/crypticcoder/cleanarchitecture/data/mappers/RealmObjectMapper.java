package com.crypticcoder.cleanarchitecture.data.mappers;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public interface RealmObjectMapper<RealmObject, DomainObject> {
    RealmObject toRealmObject(DomainObject object);
    DomainObject toDomainObject(RealmObject object);
}
