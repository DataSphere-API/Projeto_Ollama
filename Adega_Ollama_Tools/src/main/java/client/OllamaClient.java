package client; // ou o nome do pacote que você estiver usando

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OllamaClient {

    private static final String URL_OLLAMA = "http://localhost:11434/api/generate";
    //endereco host ollama

    public String enviarMensagem(String prompt, String systemPrompt) {
        try {
            // 1. Criamos um objeto JSON e deixamos o Gson cuidar das aspas e caracteres especiais
            JsonObject requestJson = new JsonObject();
            requestJson.addProperty("model", "qwen2.5-coder:7b");
            requestJson.addProperty("system", systemPrompt);
            requestJson.addProperty("prompt", prompt);
            requestJson.addProperty("stream", false);

            JsonObject options = new JsonObject();
            options.addProperty("temperature", 0.0);
            requestJson.add("options", options);

            // 2. O Gson converte o objeto para uma String perfeita (sem erro de sintaxe)
            String jsonBody = requestJson.toString();

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(java.net.URI.create("http://localhost:11434/api/generate"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // --- MODO DETETIVE ---
            String corpoResposta = response.body();
            System.out.println("DEBUG OLLAMA: " + corpoResposta);
            // ---------------------

            JsonObject respostaJson = JsonParser.parseString(corpoResposta).getAsJsonObject();

            // Verificação muito mais segura
            if (respostaJson.has("response") && !respostaJson.get("response").isJsonNull()) {
                return respostaJson.get("response").getAsString();
            } else {
                return "Erro: O campo 'response' veio nulo ou não existe. JSON recebido: " + corpoResposta;
            }

        } catch (Exception e) {
            return "Erro ao processar: " + e.getMessage();
        }
    }
}