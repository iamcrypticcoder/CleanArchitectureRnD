package com.crypticcoder.cleanarchitecture.data.mappers.impl;

import com.crypticcoder.cleanarchitecture.data.mappers.JSONObjectMapper;
import com.crypticcoder.cleanarchitecture.data.models.RealmBook;
import com.crypticcoder.cleanarchitecture.domain.model.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public class JSONObjectBookMapper implements JSONObjectMapper<JSONObject, Book> {

    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    @Override
    public JSONObject toJSONObject(Book obj) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Gson gson = gsonBuilder.create();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", obj.getId());
            jsonObject.put("title", obj.getTitle());

            Type listType = new TypeToken<List<String>>() {}.getType();
            jsonObject.put("authors", gson.toJson(obj.getAuthors(), listType));

            jsonObject.put("publishedDate", gson.toJson(obj.getPublishedDate()));
        } catch (JSONException e) {

        }
        return jsonObject;
    }

    @Override
    public Book toDomainObject(JSONObject obj) {
        Book book = new Book();
        try {
            book.setId(obj.optLong("id"));
            book.setTitle(obj.optString("title"));
            JSONArray jsonArray = obj.optJSONArray("authors");
            for (int i = 0; i < jsonArray.length(); i++)
                book.getAuthors().add(jsonArray.optString(i));
            book.setPublishedDate(obj.isNull("publishedDate") ? null : dateFormat.parse(obj.optString("publishedDate")));
        } catch (ParseException e) {

        }
        return book;
    }
}
