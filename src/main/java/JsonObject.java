import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.util.List;


public class JsonObject {
    public static List<Fact> convertingJson (CloseableHttpClient httpClient, HttpGet request) throws IOException {
        CloseableHttpResponse response = httpClient.execute(request);

        ObjectMapper objectMapper = new ObjectMapper();
        List<Fact> facts = objectMapper.readValue(
                response.getEntity().getContent(),
                new TypeReference<>() {}
        );
        return facts;

    }
}
