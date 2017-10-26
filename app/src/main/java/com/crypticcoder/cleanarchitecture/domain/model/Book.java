package com.crypticcoder.cleanarchitecture.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mahbub on 25/10/17.
 */

public class Book {

    private Long id;

    private String title;

    private List<String> authors;

    private Date publishedDate;

    public Book() {
        authors = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }
}
