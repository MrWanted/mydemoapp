package com.example.mydemoapp.serviceImpl;


import lombok.Data;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import java.io.IOException;
import com.example.mydemoapp.service.ClientService;
import org.springframework.stereotype.Service;

@Data
@Service
public class OkHttpImpl implements ClientService {
    private final OkHttpClient client;

    @Value("${external.api.personUrl}")
    private String personUrl;

    @Cacheable(cacheNames = "persons", key = "#id")
    public String fetchDataFromApi(String id) throws IOException {
        String urlWithId = personUrl + id;

        Request request = new Request.Builder()
                .url(urlWithId)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            return response.body() != null ? response.body().string() : null;
        }
    }

    @Override
    public String postDataToApi(String endpoint, String jsonPayload) throws IOException, InterruptedException {
        return "";
    }

    public void fetchDataFromApiAsync(String id) {
        OkHttpClient client = new OkHttpClient();
        String urlWithId = personUrl + id;

        // Build the request
        Request request = new Request.Builder()
                .url(urlWithId)
                .build();

        // Execute the request asynchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace(); // Handle failure
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    System.out.println("Response: " + responseBody); // Example of processing data
                } else {
                    System.err.println("Error: " + response);
                    System.err.println("Error: " + response.code()); // Handle error response
                }
            }
        });
    }

}
