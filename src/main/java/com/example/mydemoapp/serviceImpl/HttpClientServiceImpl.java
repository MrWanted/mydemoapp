package com.example.mydemoapp.serviceImpl;

import com.example.mydemoapp.service.ClientService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Data
@Primary
public class HttpClientServiceImpl implements ClientService {

    @Value("${external.api.todosUrl}")
    private String todosUrl;

    @Override
    @Cacheable(value = "todos", key = "#id")
    public String fetchDataFromApi(String id) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String urlWithId = todosUrl + "?id=" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlWithId))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    @Override
    public String postDataToApi(String endpoint, String jsonPayload) throws IOException, InterruptedException {
        return "";
    }
}
