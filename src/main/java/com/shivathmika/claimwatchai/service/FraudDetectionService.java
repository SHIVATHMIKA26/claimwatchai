package com.shivathmika.claimwatchai.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class FraudDetectionService {

    public String checkFraud(String json) throws Exception {

        URL url = new URL("http://127.0.0.1:5000/predict");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type","application/json");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        os.write(json.getBytes());
        os.flush();

        BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
        );

        return br.readLine();
    }
}