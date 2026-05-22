package tools;
import io.github.ollama4j.tools.Tools;

public class CadastroFornecedorToolSpec {
    public static Tools.Tool getSpecification() {
        return Tools.Tool.builder()
                .name("cadastro_fornecedor")
                .description("Cadastra um novo fornecedor")
                .inputSchema("{\"type\":\"object\",\"properties\":{\"nomeFornecedor\":{\"type\":\"string\"},\"telefone\":{\"type\":\"string\"},\"endereco\":{\"type\":\"string\"}}}")
                .function((input) -> {
                    String nome = input.get("nomeFornecedor").asText();
                    String telefone = input.get("telefone").asText();
                    String endereco = input.get("endereco").asText();
                    // Lógica de cadastro
                    return "{\"status\":\"Fornecedor cadastrado com sucesso\"}";
                })
                .build();
    }
}
