package com.gnoemes.booklistingapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnoemes.booklistingapp.models.Book;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testObj() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readValue(MainActivity.mockJSON,JsonNode.class);

        JsonNode items = node.get("items");
        Book book = null;

        for (int i = 0; i < items.size(); i++) {
            JsonNode obj = items.get(i);
            JsonNode volumeInfo = obj.get("volumeInfo");
            JsonNode title = volumeInfo.get("title");
            JsonNode author = volumeInfo.get("authors");
            String[] authors = new String[author.size()];
            for (int j = 0; j < author.size(); j++) {
                authors[i] = author.get(i).asText();
            }
            JsonNode description = volumeInfo.get("description");
            JsonNode imageLinks = volumeInfo.get("imageLinks");
            JsonNode smallThumbnail = imageLinks.get("smallThumbnail");
            JsonNode infoLink = volumeInfo.get("infoLink");
            book = new Book(title.asText(),authors,description.asText(),smallThumbnail.asText(),infoLink.asText());
        }


    }


}