package tools;

import io.github.ollama4j.tools.Tools;

public class CadastroComandaToolSpec {
    public static Tools.Tool getSpecification() {
        return Tools.Tool.builder()
                .name("cadastro_comanda")
                .description("Cadastra uma nova comanda")
                .inputSchema("{\"type\":\"object\",\"properties\":{\"numeroComanda\":{\"type\":\"string\"},\"cliente\":{\"type\":\"string\"}}}")
                .function((input) -> {
                    String numero = input.get("numeroComanda").asText();
                    String cliente = input.get("cliente").asText();
                    // Lógica de cadastro
                    return "{\"status\":\"Comanda cadastrada com sucesso\"}";
                })
                .build();
    }
}