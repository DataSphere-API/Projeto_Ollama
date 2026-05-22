package service;

import io.github.ollama4j.Ollama;
import io.github.ollama4j.exceptions.OllamaException;
import io.github.ollama4j.models.chat.OllamaChatRequest;
import io.github.ollama4j.models.generate.OllamaGenerateRequest;
import io.github.ollama4j.models.response.OllamaResult;

public class OllamaService {

    private final Ollama OLLAMA = new Ollama("http://localhost:11434");
    private final String MODEL = "qwen3.5:2b";

    public void pullModel(){
        try {
            OLLAMA.pullModel(MODEL);
            OllamaChatRequest builder = OllamaChatRequest.builder().withModel(MODEL);
        } catch (OllamaException e) {
            throw new RuntimeException(e);
        }
    }

}