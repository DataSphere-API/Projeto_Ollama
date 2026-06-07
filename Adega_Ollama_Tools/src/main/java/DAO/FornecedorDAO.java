package DAO;

import config.ConexaoBD;
import model.FornecedorModel;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    public List<FornecedorModel> listarTodosFornecedores() {
        List<FornecedorModel> listaDeFornecedores = new ArrayList<>();

        String sql = "SELECT * FROM fornecedores";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);

             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FornecedorModel f = new FornecedorModel(
                        rs.getInt("id"),
                        rs.getString("nome_fornecedor"),
                        rs.getString("telefone"),
                        rs.getString("produto_fornecido")
                );
                listaDeFornecedores.add(f);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar fornecedores: " + e.getMessage());
        }
        return listaDeFornecedores;
    }

}
