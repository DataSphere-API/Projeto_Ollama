import client.OllamaClient;
import service.AdegaService;

public class MainTeste {
    public static void main(String[] args) {
        OllamaClient cliente = new OllamaClient();
        AdegaService adegaService = new AdegaService();

        // 1. O Manual de Instruções (System Prompt)
        String manualDaIA = "Você é um agente de roteamento lógico. " +
                "Analise a solicitação do usuário e retorne APENAS um dos três tokens: " +
                "1. [TOOL_ESTOQUE] (para consultas sobre bebidas/estoque) " +
                "2. [TOOL_FORNECEDORES] (para consultas sobre fornecedores) " +
                "3. [NENHUM] (para qualquer outra conversa) " +
                "NÃO inclua explicações, NÃO inclua pontuação extra, NÃO escreva frases. " +
                "Seja estritamente um classificador de comandos.";

        // A pergunta do usuário
        String perguntaUsuario = "Pode me dizer o que temos no nosso estoque hoje?";
        System.out.println("Usuário: " + perguntaUsuario);
        System.out.println("Pensando...");

        // 2. A IA decide o que fazer
        String acaoDaIA = cliente.enviarMensagem(perguntaUsuario, manualDaIA);
        String respostaIA = acaoDaIA.toUpperCase(); // Padronizamos para maiúsculas

        // 3. O Java intercepta a ação e aciona as Tools
        if (respostaIA.contains("[TOOL_ESTOQUE]")) {
            System.out.println("-> IA acionou a Ferramenta de Estoque. Buscando no Supabase...");
            String dadosDoBanco = adegaService.consultarEstoqueIA();

            // Devolvemos os dados para a IA criar a resposta final
            String promptFinal = "O usuário perguntou sobre o estoque. Aqui estão os dados reais do banco: \n"
                    + dadosDoBanco + "\n\nResponda ao usuário com base nesses dados de forma simpática.";

            String respostaFinal = cliente.enviarMensagem(promptFinal, "Você é um atendente prestativo.");
            System.out.println("\n--- Resposta Final da IA ---");
            System.out.println(respostaFinal);

        } else if (respostaIA.contains("[TOOL_FORNECEDORES]")) {
            System.out.println("-> IA acionou a Ferramenta de Fornecedores. Buscando no Supabase...");
            String dadosDoBanco = adegaService.consultarFornecedoresIA();

            String promptFinal = "O usuário perguntou sobre fornecedores. Aqui estão os dados: \n"
                    + dadosDoBanco + "\n\nResponda ao usuário.";

            String respostaFinal = cliente.enviarMensagem(promptFinal, "Você é um atendente prestativo.");
            System.out.println("\n--- Resposta Final da IA ---");
            System.out.println(respostaFinal);

        } else {
            // Se ela não usou ferramenta, apenas respondeu normalmente
            System.out.println("\n--- Resposta Direta da IA ---");
            System.out.println(acaoDaIA);
        }
    }
}