package tools;

import io.github.ollama4j.tools.Tools;

public class VendaAlimentosToolSpec {
    public static Tools.Tool getSpecification() {
        return Tools.Tool.builder()
                .name("venda_alimentos")
                .description("Registra a venda de um alimento")
                .inputSchema("{\"type\":\"object\",\"properties\":{\"nomeAlimento\":{\"type\":\"string\"},\"quantidade\":{\"type\":\"integer\"}}}")
                .function((input) -> {
                    String nomeAlimento = input.get("nomeAlimento").asText();
                    int quantidade = input.get("quantidade").asInt();
                    // Lógica de venda
                    return "{\"status\":\"Venda registrada com sucesso\"}";
                })
                .build();
    }
}