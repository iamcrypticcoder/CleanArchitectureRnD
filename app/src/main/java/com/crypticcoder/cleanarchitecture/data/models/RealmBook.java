package com.crypticcoder.cleanarchitecture.data.models;

import java.util.Date;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public class RealmBook extends RealmObject {

    @PrimaryKey
    public Long id;

    public String title;

    public List<String> authors;

    public Date publishedDate;

    public Date syncTime;
}
