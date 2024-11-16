package com.example.mydemoapp.service;

import java.io.IOException;

public interface ClientService {

    /**
     * Makes a GET request to the specified API endpoint.
     *
     * @return The response body as a String if the request is successful.
     * @throws IOException If an I/O error occurs when sending or receiving.
     * @throws InterruptedException If the operation is interrupted.
     */
    String fetchDataFromApi(String id) throws IOException, InterruptedException;

    /**
     * Makes a POST request to the specified API endpoint with a JSON payload.
     *
     * @param endpoint The specific endpoint to send data to.
     * @param jsonPayload The JSON payload to send with the request.
     * @return The response body as a String if the request is successful.
     * @throws IOException If an I/O error occurs when sending or receiving.
     * @throws InterruptedException If the operation is interrupted.
     */
    String postDataToApi(String endpoint, String jsonPayload) throws IOException, InterruptedException;
}

