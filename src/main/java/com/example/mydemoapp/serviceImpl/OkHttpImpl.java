package com.example.mydemoapp.serviceImpl;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import java.io.IOException;
import com.example.mydemoapp.service.ClientService;
import org.springframework.stereotype.Service;

@Data
@Service
@Slf4j
public class OkHttpImpl implements ClientService {
    private final OkHttpClient client;

    @Value("${external.api.personUrl}")
    private String personUrl;

    //@Cacheable(cacheNames = "persons", key = "#id")
    public String fetchDataFromApi(String id) throws IOException {
        // Construct the URL with the provided ID
        String urlWithId = personUrl + id;

        // Create the HTTP GET request
        Request request = new Request.Builder()
                .url(urlWithId)
                .build();

        try (Response response = client.newCall(request).execute()) {
            // Check if the response is successful based on HTTP status code
            if (!response.isSuccessful()) {
                // Handle different HTTP status codes explicitly
                int statusCode = response.code();
                String errorMessage = "Unexpected code " + response;

                switch (statusCode) {
                    case 404:
                        throw new OpenApiResourceNotFoundException("Resource not found: " + urlWithId);
                    case 500:
                        throw new IOException("Server error occurred. Please try again later.");
                    case 403:
                        throw new IOException("Access forbidden. You do not have permission.");
                    default:
                        throw new IOException(errorMessage);
                }
            }

            // Read the response body
            String responseBody = response.body() != null ? response.body().string() : null;

            // Check the body for specific error messages even if status code is 200
            if (responseBody != null && responseBody.contains("Resource not found")) {
                throw new OpenApiResourceNotFoundException("Resource not found: " + urlWithId);
            }

            // Return the response body if no error found
            return responseBody;
        } catch (IOException e) {
            // Return the error message or log it
            throw new IOException("Error fetching data: " + e.getMessage(), e);
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
