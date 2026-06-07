package DAO;

import config.ConexaoBD;
import java.sql.*;

public class ComandaDAO {

    public int criarComanda() {
        String sql = "INSERT INTO comandas (status) VALUES ('Aberta')";

        //RETURN_GENERATED_KEYS diz o ‘ID’ que o banco criou
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.executeUpdate();

            //Puxa o ID que acabou de ser criado
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar comanda: " + e.getMessage());
        }
        return -1; // Retorna -1 se falhar
    }

    public boolean fecharComanda(int comandaId) {
        String sql = "UPDATE comandas SET status = 'Fechada' WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, comandaId);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0; // Se alterou alguma linha, retorna true

        } catch (Exception e) {
            System.out.println("Erro ao fechar comanda: " + e.getMessage());
            return false;
        }
    }

}
