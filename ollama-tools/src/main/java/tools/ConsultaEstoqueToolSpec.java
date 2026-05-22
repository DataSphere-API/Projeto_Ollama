package tools;

import io.github.ollama4j.tools.Tools;

public class ConsultaEstoqueToolSpec {
    public static Tools.Tool getSpecification() {
        return Tools.Tool.builder()
                .name("consulta_estoque")
                .description("Consulta a quantidade em estoque de uma bebida")
                .inputSchema("{\"type\":\"object\",\"properties\":{\"nomeBebida\":{\"type\":\"string\"}}}")
                .function((input) -> {
                    String nomeBebida = input.get("nomeBebida").asText();
                    // Lógica de consulta ao estoque
                    return "{\"estoque\":\"42\"}";
                })
                .build();
    }
}
