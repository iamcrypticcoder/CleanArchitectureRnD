package com.crypticcoder.cleanarchitecture.domain.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cryptic Coder on 27,October,2017
 */

public class BookListFilter {

    public Set<Long> bookIds;

    public String title;

    public Set<String> authors;

    public Long beforeId;

    public Long afterId;

    public BookListFilter() {
        bookIds = new HashSet<>();
        authors = new HashSet<>();
    }
}
