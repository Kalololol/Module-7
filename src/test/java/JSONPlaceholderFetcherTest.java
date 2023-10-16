import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.JSONPlaceholderFetcher;
import org.example.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JSONPlaceholderFetcherTest {
    private JSONPlaceholderFetcher json = new JSONPlaceholderFetcher();
    ObjectMapper objectMapper = new ObjectMapper();

    //Sprawdź, czy przy wywołaniu metody getSinglePost z argumentem 1 otrzymany obiekt ma takie same wartości jak w JSONie
    @Test
    public void getSinglePostTest(){

        String input = "{\"userId\" : 1,\"id\" : 1,\"title\" : \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\"body\" : \"quia et " +
                "suscipit\\nsuscipit recusandae consequuntur expedita " +
                "et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"}";
        try {
            Post readJson = objectMapper.readValue(input, Post.class);
            Post result = json.getSinglePost(1);
            Assertions.assertEquals(readJson.getId(), result.getId());
            Assertions.assertEquals(readJson.getUserID(), result.getUserID());
            Assertions.assertEquals(readJson.getBody(), result.getBody());
            Assertions.assertEquals(readJson.getTitle(), result.getTitle());

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    //Sprawdź czy przy wywołaniu metody getAllPosts rozmiar tablicy jest równy 100;
    @Test
    public void getAllPostsTest(){
        int result = 100;
        int sizeTable = json.getAllPosts().size();
        Assertions.assertEquals(result, sizeTable);
    }
    //Sprawdź, czy jeśli stworzysz obiekt klasy Post i go zmapujesz na JSON, czy String będzie równy temu co zakładasz.
    @Test public void mapToJSONTest(){
        Post post = new Post();
        post.setBody("Ciało posta");
        post.setTitle("Tytuł");
        post.setId(1);
        post.setUserID(2);
        String mapJsonToString = "";
        try {
            mapJsonToString = json.mapToJSON(post);

            mapJsonToString.length();
            Assertions.assertEquals(58, mapJsonToString.length() );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
