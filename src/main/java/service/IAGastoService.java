package com.gastos.service;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class IAGastoService {

    public String classificarGasto(String descricao) {
        try {
            // URL da sua API Python
            URL url = new URL("http://127.0.0.1:5000/classificar");

            // Conexão
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Corpo da requisição
            String jsonInput = "{\"descricao\": \"" + descricao + "\"}";
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Leitura da resposta
            Scanner scanner = new Scanner(connection.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            scanner.close();

            // DEBUG: Veja o que a IA respondeu
            System.out.println("Resposta da IA: " + response);

            // Aqui está o erro: se não for JSON, isso quebra
            JSONObject jsonResponse = new JSONObject(response);
            return jsonResponse.getString("categoria");

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro na classificação";
        }
    }
}