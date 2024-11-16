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
                /*.addInterceptor(chain -> {
                    Request request = chain.request();
                    try (Response response = chain.proceed(request)) {
                        if (!response.isSuccessful()) {
                            log.warn("Request failed, retrying: {}", request.url());
                            return chain.proceed(request);
                        }
                        // Clone the response before returning since we're in a try-with-resources block
                        return response.newBuilder().build();
                    } catch (IOException e) {
                        log.warn("Request failed, retrying: {}", request.url(), e);
                        return chain.proceed(request);
                    }
                })*/
                .build();
    }
}

