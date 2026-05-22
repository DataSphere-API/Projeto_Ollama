package tools;

import io.github.ollama4j.tools.Tools;

public class VendaBebidasToolSpec {
    public static Tools.Tool getSpecification() {
        return Tools.Tool.builder()
                .name("venda_bebidas")
                .description("Registra a venda de uma bebida")
                .inputSchema("{\"type\":\"object\",\"properties\":{\"nomeBebida\":{\"type\":\"string\"},\"quantidade\":{\"type\":\"integer\"}}}")
                .function((input) -> {
                    String nomeBebida = input.get("nomeBebida").asText();
                    int quantidade = input.get("quantidade").asInt();
                    // Lógica de venda
                    return "{\"status\":\"Venda registrada com sucesso\"}";
                })
                .build();
    }
}
