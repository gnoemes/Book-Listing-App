package com.gnoemes.booklistingapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnoemes.booklistingapp.models.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Utils {
    public static final String ERR_MESSAGE = "No description";

    public static List<Book> createJSONFromURL(String url) {
        List<Book> books;

        String jsonString = getJsonData(url);
        if (jsonString == null || jsonString.length() == 0) {
            return null;
        }
            books = getBooksFromJson(jsonString);

        return books;
    }

    private static String getJsonData(String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

@JsonIgnoreProperties(ignoreUnknown = true)
    public static List<Book> getBooksFromJson(String json){

        List<Book> books = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = objectMapper.readValue(json,JsonNode.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonNode items = node.get("items");

        for (int i = 0; i < items.size(); i++) {
            JsonNode obj = items.get(i);
            JsonNode volumeInfo = obj.get("volumeInfo");
            JsonNode titleNode = volumeInfo.get("title");
            JsonNode authorNode = volumeInfo.get("authors");
            String[] authors = null;
            if (authorNode != null) {
                authors = new String[authorNode.size()];
                for (int j = 0; j < authorNode.size(); j++) {
                    authors[j] = authorNode.get(j).asText();
                }
            }

            JsonNode descriptionNode = volumeInfo.get("description");
            JsonNode imageLinksNode = volumeInfo.get("imageLinks");
            JsonNode imageNode = null;
            if (imageLinksNode != null) {
                 imageNode = imageLinksNode.get("smallThumbnail");
            }
            JsonNode infoLink = volumeInfo.get("infoLink");



            String title;
            String description;
            String imageURL;
            String url;

            if (titleNode == null) title = ERR_MESSAGE;
            else title = titleNode.asText();
            if (descriptionNode == null) description = ERR_MESSAGE;
            else description = descriptionNode.asText();
            if (authors == null || authors.length == 0) {
                authors = new String[1];
                authors[0] = ERR_MESSAGE;
            }
            if (imageNode == null) imageURL = ERR_MESSAGE;
            else imageURL = imageNode.asText();
            if (infoLink == null) url = ERR_MESSAGE;
            else url = infoLink.asText();


            books.add( new Book(title,authors,description, imageURL,url));
        }

        return books;
    }
}
