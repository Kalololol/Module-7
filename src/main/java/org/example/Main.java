package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Post post = new Post();
        post.setBody("Ciało posta");
        post.setTitle("Tytuł");
        post.setId(1);
        post.setUserID(2);

        JSONPlaceholderFetcher json = new JSONPlaceholderFetcher();
        try {
            System.out.println(json.mapToJSON(post));
            System.out.println(json.mapToJSON(post).length());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
