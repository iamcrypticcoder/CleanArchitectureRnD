package com.crypticcoder.cleanarchitecture.data.book;

import com.crypticcoder.cleanarchitecture.data.models.RealmBook;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public class BookDemoData {
    public static JSONArray bookJsonArray;
    public static RealmBook[] realmBooks;


    static {
        bookJsonArray = new JSONArray();
        realmBooks = new RealmBook[5];

        realmBooks[0] = new RealmBook();
        realmBooks[0].id = 1L;
        realmBooks[0].title = "War and Peace";
        realmBooks[0].authors = Arrays.asList("Leo Tolstoy");
        realmBooks[0].publishedDate = new Date();

        realmBooks[1] = new RealmBook();
        realmBooks[0].id = 2L;
        realmBooks[1].title = "Hamlet";
        realmBooks[1].authors = Arrays.asList("William Shakespeare");
        realmBooks[1].publishedDate = new Date();

        realmBooks[2] = new RealmBook();
        realmBooks[0].id = 3L;
        realmBooks[2].title = "Pride and Prejudice";
        realmBooks[2].authors = Arrays.asList("Jane Austen");
        realmBooks[2].publishedDate = new Date();

        realmBooks[3] = new RealmBook();
        realmBooks[0].id = 4L;
        realmBooks[3].title = "Anna Karenina";
        realmBooks[3].authors = Arrays.asList("Leo Tolstoy");
        realmBooks[3].publishedDate = new Date();

        realmBooks[4] = new RealmBook();
        realmBooks[0].id = 5L;
        realmBooks[4].title = "Gulliver's Travels";
        realmBooks[4].authors = Arrays.asList("Jonathan Swift");
        realmBooks[4].publishedDate = new Date();

        try {
            JSONObject obj = new JSONObject();
            obj.put("id", 1L);
            obj.put("title", "War and Peace");
            obj.put("authors", "[Leo Tolstoy]");
            obj.put("publishedDate", new Date().getTime());
            bookJsonArray.put(0, obj);

            obj = new JSONObject();
            obj.put("id", 2L);
            obj.put("title", "Hamlet");
            obj.put("authors", "[William Shakespeare]");
            obj.put("publishedDate", new Date().getTime());
            bookJsonArray.put(1, obj);

            obj = new JSONObject();
            obj.put("id", 3L);
            obj.put("title", "Pride and Prejudice");
            obj.put("authors", "[Jane Austen]");
            obj.put("publishedDate", new Date().getTime());
            bookJsonArray.put(2, obj);

            obj = new JSONObject();
            obj.put("id", 3L);
            obj.put("title", "Anna Karenina");
            obj.put("authors", "[Leo Tolstoy]");
            obj.put("publishedDate", new Date().getTime());
            bookJsonArray.put(3, obj);

            obj = new JSONObject();
            obj.put("id", 3L);
            obj.put("title", "Gulliver's Travels");
            obj.put("authors", "[Jonathan Swift]");
            obj.put("publishedDate", new Date().getTime());
            bookJsonArray.put(4, obj);
        } catch (JSONException e) {

        }
    }

    public static RealmBook getRealmBook(int bookId) {
        return realmBooks[bookId - 1];
    }

    public static JSONObject getJSONBook(int bookId) {
        return bookJsonArray.optJSONObject(bookId-1);
    }
}
