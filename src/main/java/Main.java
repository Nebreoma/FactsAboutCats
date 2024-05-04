import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = creatiСonfiguringСlient();

// создание объекта запроса с произвольными заголовками
        HttpGet request = new HttpGet("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
// отправка запроса
        CloseableHttpResponse response = httpClient.execute(request);
// вывод полученных заголовков
        List<Fact> factsCats = JsonObject.convertingJson (httpClient, request);
        factsCats.stream()
                .filter(value -> value.getUpvotes() != null && value.getUpvotes() > 0)
                .forEach(System.out::println);
    }

    public static CloseableHttpClient creatiСonfiguringСlient() {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(
                        RequestConfig.custom()
                            .setConnectTimeout(5000)
                            // максимальное время ожидание подключения к серверу
                            .setSocketTimeout(30000)
                            // максимальное время ожидания получения данных
                            .setRedirectsEnabled(false)
                            // возможность следовать редиректу в ответе
                            .build()
                )
                .build();
        return httpClient;
    }
}
