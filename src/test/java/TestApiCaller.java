import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Fetches JSONPLACEHOLDER API then test the number of posts against a fixed number.
 * 
 */
public class TestApiCaller {
    public static String baseUrl = "https://jsonplaceholder.typicode.com/posts";

    @Test(description = "Test json-place-holder api")
    public void testAPI() throws URISyntaxException, IOException, InterruptedException {
        //Jackson Mapper to later convert the string response to Java Post type.
        ObjectMapper mapper = new ObjectMapper();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                            .uri(new URI(baseUrl))
                            .GET()
                            .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String resposeBody = response.body();
        List<Post> posts = mapper.readValue(resposeBody, new TypeReference<List<Post>>(){});
        
        Assert.assertEquals(posts.size(), 100);
    }
}
