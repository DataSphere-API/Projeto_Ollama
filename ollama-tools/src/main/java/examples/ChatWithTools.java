package examples;

import io.github.ollama4j.Ollama;
import io.github.ollama4j.models.chat.OllamaChatMessageRole;
import io.github.ollama4j.models.chat.OllamaChatRequest;
import io.github.ollama4j.models.chat.OllamaChatResult;
import io.github.ollama4j.tools.Tools;
import service.OllamaService;
import tools.ConsultaEstoqueToolSpec;
import tools.VendaBebidasToolSpec;
import tools.VendaAlimentosToolSpec;
import tools.CadastroFornecedorToolSpec;
import tools.CadastroComandaToolSpec;

public class ChatWithTools {
    public static void main(String[] args) throws Exception {
        OllamaService service = new OllamaService();
        service.pullModel();

        Ollama ollama = new Ollama("http://localhost:11434/");
        String model = "qwen3.5:2b";

        // Registra suas 5 tools
        ollama.registerTool(ConsultaEstoqueToolSpec.getSpecification());
        ollama.registerTool(VendaBebidasToolSpec.getSpecification());
        ollama.registerTool(VendaAlimentosToolSpec.getSpecification());
        ollama.registerTool(CadastroFornecedorToolSpec.getSpecification());
        ollama.registerTool(CadastroComandaToolSpec.getSpecification());

        // Exemplo de uso
        OllamaChatRequest request = OllamaChatRequest.builder()
                .withModel(model)
                .withMessage(OllamaChatMessageRole.USER, "Qual o estoque da bebida X?")
                .build();

        OllamaChatResult result = ollama.chat(request, null);
        System.out.println("Resposta: " + result.getResponseModel().getMessage().getResponse());
    }
}
