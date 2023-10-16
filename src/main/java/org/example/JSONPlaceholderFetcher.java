package org.example;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class JSONPlaceholderFetcher {
    private final HttpClient client = HttpClient.newBuilder().build();
    ObjectMapper objectMapper = new ObjectMapper();

    public Post getSinglePost(int id){
        String result = "";

        try {
            URI uri = URI.create("https://jsonplaceholder.typicode.com/posts/" + id);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            result = response.body();
            Post post = objectMapper.readValue(result, Post.class);

            return post;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Post> getAllPosts(){
        String result = "";

        URI uri = URI.create("https://jsonplaceholder.typicode.com/posts/" );
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            result = response.body();
            int i = 1;
            List<Post> posts = new ArrayList<>(objectMapper.readValue(result, new TypeReference<List<Post>>(){}));
//            System.out.println("klasa POST metoda toString()");
//            for(Post post : posts){
//                System.out.println(i + ". " + post.toString());
//                i++;
//            }
            return posts;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String mapToJSON(Post post) throws JsonProcessingException {
        return objectMapper.writeValueAsString(post).toString();
    }
}
