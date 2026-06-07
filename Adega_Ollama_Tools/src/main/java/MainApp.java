import client.OllamaClient;
import service.AdegaService;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        OllamaClient cliente = new OllamaClient();
        AdegaService adegaService = new AdegaService();
        Scanner scanner = new Scanner(System.in);

        // O manual agora ensina a IA a separar o que é comando do que é conversa
        String manualDaIA = "Você é um Gerente de Adega. " +
                "Analise o pedido do usuário. " +
                "Se for um comando, retorne APENAS o token: [TOOL_ESTOQUE], [TOOL_FORNECEDORES], [TOOL_ABRIR_COMANDA], [TOOL_ADICIONAR_ITEM] ID_COMANDA ID_BEBIDA QTD, ou [TOOL_FECHAR_COMANDA] ID_COMANDA. " +
                "Se for uma conversa (saudação, dúvida, etc), retorne APENAS: [CHAT]. " +
                "NÃO escreva explicações.";

        System.out.println("Sistema de Adega Ativo. Digite 'sair' para encerrar.");

        while (true) {
            System.out.print("\nVocê: ");
            String entrada = scanner.nextLine();

            if (entrada.equalsIgnoreCase("sair")) break;

            // 1. IA decide se é TOOL ou CHAT
            String decisaoIA = cliente.enviarMensagem(entrada, manualDaIA).toUpperCase();

            // 2. Processa
            if (decisaoIA.contains("[CHAT]")) {
                // Se for chat, pedimos para ela gerar uma resposta simpática
                String resposta = cliente.enviarMensagem(entrada, "Você é um atendente prestativo e simpático de uma Adega. Responda o usuário.");
                System.out.println("IA: " + resposta);
            } else {
                // Se for TOOL, executamos as funções do Java
                processarComando(decisaoIA, cliente, adegaService);
            }
        }
        scanner.close();
    }

    private static void processarComando(String respostaIA, OllamaClient cliente, AdegaService service) {
        if (respostaIA.contains("[TOOL_ESTOQUE]")) {
            System.out.println("IA: Consultando estoque...");
            System.out.println(service.consultarEstoqueIA());

        } else if (respostaIA.contains("[TOOL_FORNECEDORES]")) {
            System.out.println("IA: Consultando fornecedores...");
            System.out.println(service.consultarFornecedoresIA());

        } else if (respostaIA.contains("[TOOL_ABRIR_COMANDA]")) {
            System.out.println("IA: " + service.abrirNovaComandaIA());

        } else if (respostaIA.contains("[TOOL_ADICIONAR_ITEM]")) {
            String[] partes = respostaIA.replaceAll("[^0-9 ]", "").trim().split("\\s+");
            if (partes.length >= 3) {
                System.out.println("IA: " + service.adicionarItemNaComandaIA(Integer.parseInt(partes[0]), Integer.parseInt(partes[1]), Integer.parseInt(partes[2])));
            } else {
                System.out.println("IA: Erro. Formato correto: [TOOL_ADICIONAR_ITEM] ID_COMANDA ID_BEBIDA QTD");
            }

        } else if (respostaIA.contains("[TOOL_FECHAR_COMANDA]")) {
            String[] partes = respostaIA.replaceAll("[^0-9 ]", "").trim().split("\\s+");
            if (partes.length >= 1) {
                System.out.println("IA: " + service.fecharComandaIA(Integer.parseInt(partes[0])));
            } else {
                System.out.println("IA: Erro. Informe o ID da comanda.");
            }

        } else {
            System.out.println("IA: Não entendi o comando. Tente algo como 'listar estoque' ou 'abrir comanda'.");
        }
    }
}