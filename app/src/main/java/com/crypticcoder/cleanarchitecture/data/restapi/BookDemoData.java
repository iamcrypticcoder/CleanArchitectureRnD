package com.crypticcoder.cleanarchitecture.data.restapi;

import com.crypticcoder.cleanarchitecture.data.models.RealmBook;
import com.crypticcoder.cleanarchitecture.domain.model.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public class BookDemoData {
    public static Map<Long, JSONObject> bookDb;
    public static Long bookIncrementalId;

    public static JSONArray bookJsonArray;
    public static RealmBook[] realmBooks;


    static {
        bookDb = new HashMap<>();
        bookJsonArray = new JSONArray();

        /*
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
        */

        try {
            JSONObject obj = new JSONObject();
            obj.put("id", 1L);
            obj.put("title", "War and Peace");
            obj.put("authors", "[Leo Tolstoy]");
            obj.put("publishedDate", new Date().getTime());
            bookDb.put(0L, obj);

            obj = new JSONObject();
            obj.put("id", 2L);
            obj.put("title", "Hamlet");
            obj.put("authors", "[William Shakespeare]");
            obj.put("publishedDate", new Date().getTime());
            bookDb.put(1L, obj);

            obj = new JSONObject();
            obj.put("id", 3L);
            obj.put("title", "Pride and Prejudice");
            obj.put("authors", "[Jane Austen]");
            obj.put("publishedDate", new Date().getTime());
            bookDb.put(2L, obj);

            obj = new JSONObject();
            obj.put("id", 3L);
            obj.put("title", "Anna Karenina");
            obj.put("authors", "[Leo Tolstoy]");
            obj.put("publishedDate", new Date().getTime());
            bookDb.put(3L, obj);

            obj = new JSONObject();
            obj.put("id", 3L);
            obj.put("title", "Gulliver's Travels");
            obj.put("authors", "[Jonathan Swift]");
            obj.put("publishedDate", new Date().getTime());
            bookDb.put(4L, obj);


            bookIncrementalId = 5L;

        } catch (JSONException e) {

        }
    }

    public static RealmBook getRealmBook(Long bookId) {
        return realmBooks[(int)(bookId - 1)];
    }

    public static JSONObject getJSONBook(Long bookId) {
        return bookJsonArray.optJSONObject((int)(bookId - 1));
    }

    public static boolean remoteApi_createBook(JSONObject jsonObject) {
        bookDb.put(bookIncrementalId, jsonObject);
        bookIncrementalId++;
        return true;
    }

    public static boolean remoteApi_updateBook(JSONObject jsonObject) {
        Long id = jsonObject.optLong("id", 0L);
        if(0L == id) return false;
        if(!bookDb.containsKey(id)) return false;

        bookDb.put(id, jsonObject);
        return true;
    }

    public static boolean remoteApi_deleteBook(Long bookId) {
        if(0L == bookId) return false;
        if(!bookDb.containsKey(bookId)) return false;

        bookDb.remove(bookId);
        return true;
    }

    public static JSONObject remoteApi_getBook(Long bookId) {
        if(false == bookDb.containsKey(bookId)) return null;
        return bookDb.get(bookId);
    }

    public static JSONObject remoteApi_getBookList() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Gson gson = gsonBuilder.create();

        int count = bookDb.values().size();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("count", count);

            JSONArray list = new JSONArray();
            for(JSONObject book : bookDb.values()) list.put(book);
            jsonObject.put("data", list);
        } catch (JSONException e) {}

        return jsonObject;
    }


}
