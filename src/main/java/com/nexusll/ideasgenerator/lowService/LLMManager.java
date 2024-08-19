package com.nexusll.ideasgenerator.lowService;

import com.nexusll.ideasgenerator.model.ApiData;
import com.nexusll.ideasgenerator.model.Prompt;
import io.netty.util.internal.SocketUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Component
public class LLMManager {

    public String ask(Prompt prompt){
        try {
            // The API endpoint URL
            // The URL to connect to
            URL url = new URL("https://api.groq.com/openai/v1/chat/completions");

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer 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");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // The JSON data to send in the request body
            String jsonInputString = "{\"messages\": [{\"role\": \"user\", \"content\": \"" + prompt.getSequence() + "\"}], \"model\": \"llama3-8b-8192\"}";
            //System.out.println(jsonInputString); //here is what I send to LLM, my prompt

            //+ prompt.getSequence() +

            // Send the JSON data
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input2 = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input2, 0, input2.length);
            }

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response (if needed)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (var reader = new java.io.BufferedReader(
                        new java.io.InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line.trim());
                    }
                    return response.toString();
                }
            } else {
                System.out.println("Request failed. HTTP Code: " + responseCode);
            }
            // Disconnect the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
