# 🍷 Projeto Ollama Tools Adega

Este projeto foi desenvolvido como parte da disciplina de **Engenharia de Software da FATEC**.  
Ele integra **Java + Ollama4j + SQLite** para criar ferramentas (*tools*) que simulam o gerenciamento de uma adega/ponto de venda.

---

## 🚀 Funcionalidades
- 📋 **Cadastro de Comandas** — abrir, fechar e cancelar comandas.
- 🏭 **Cadastro de Fornecedores** — manter dados de fornecedores e contatos.
- 🍾 **Gestão de Produtos** — alimentos e bebidas com preço, custo e categoria.
- 📦 **Controle de Estoque** — movimentações, alertas de estoque mínimo e histórico.
- 💰 **Registro de Vendas** — itens vinculados a comandas e formas de pagamento.
- 🤖 **Integração com Ollama** — exposição de *tools* conversacionais via `ollama4j`.

---

## 🛠️ Tecnologias
- **Java 25**  
- **Maven**  
- **SQLite** (via `sqlite-jdbc`)  
- **Ollama** (servidor local)  
- **ollama4j** (cliente Java para Ollama)

---

## 📂 Estrutura do Projeto
Projeto_Ollama/
├── ollama-tools/
│    ├── src/main/java/tools/   # ToolSpecs (Cadastro, Venda, Estoque, etc.)
│    ├── src/test/java/         # Testes unitários
│    ├── pom.xml                # Configuração Maven
│    └── README.md              # Este arquivo
└── sql/
└── schema.sql             # Esquema inicial do banco SQLite

---

## ⚙️ Instalação e Uso

### 1. Clonar o repositório
```bash
git clone https://github.com/DataSphere-API/Projeto_Ollama.git
cd Projeto_Ollama/ollama-tools
