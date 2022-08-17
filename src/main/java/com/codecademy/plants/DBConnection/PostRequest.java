package com.codecademy.plants.DBConnection;

import java.io.IOException;

import okhttp3.*;

public class PostRequest {

    private final OkHttpClient httpClient = new OkHttpClient();

    public static void main(String[] args) throws Exception {

        PostRequest obj = new PostRequest();

        System.out.println("Testing 1 - Send Http GET request");
        obj.sendGet();

        System.out.println("Testing 2 - Send Http POST request");
        obj.sendPost();

    }

    private void sendGet() throws Exception {

        Request request = new Request.Builder()
                .url("http://localhost:4001/traveladventures")
                .addHeader("custom-key", "mkyong")  // add request headers
                .addHeader("User-Agent", "OkHttp Bot")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            System.out.println(response.body().string());
        }

    }

    private void sendPost() throws Exception {
        String json = "{\"date\": \"17/38/2042\", \"country\": \"Mexico\",  \"city\": \"Secret\", \"state\": \"Colorado\", \"numPhotos\": 1738, \"blogCompleted\": false}";
        // form parameters
        final RequestBody body = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url("http://localhost:4001/traveladventures")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Content-Type", "application")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            System.out.println(response.body().string());
        }
    }
}