package com.georgejrdev.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.georgejrdev.utils.Interfaces.InterfaceRequest;

public class Request implements InterfaceRequest {

    @Override
    public String makeRequest(String url, String method) {
        return sendRequest(url, method, null);
    }


    private String sendRequest(String url, String method, String body) {
        
        StringBuilder response = new StringBuilder();
        try {
            String inputLine;

            URL requestUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod(method);

            if (body != null) {
                connection.setDoOutput(true);

                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = body.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.append(e.getMessage());
        }

        return response.toString();
    }
}