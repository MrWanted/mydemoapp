package com.example.mydemoapp.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class OkHttpConfig {

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    Response response = null;
                    try {
                        response = chain.proceed(request);
                        if (!response.isSuccessful()) {
                            log.warn("Request failed with code {}: {}",
                                    response.code(), request.url());
                            response.close();
                            // Retry the request
                            response = chain.proceed(request);
                        }
                        return response;
                    } catch (IOException e) {
                        log.warn("Request failed, retrying: {}", request.url(), e);
                        if (response != null) {
                            response.close();
                        }
                        return chain.proceed(request);
                    }
                })
                .build();
    }
}

