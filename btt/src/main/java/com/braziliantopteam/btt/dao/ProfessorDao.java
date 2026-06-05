package com.braziliantopteam.btt.dao;

import com.braziliantopteam.btt.entity.Professor;
import com.braziliantopteam.btt.enums.TipoArteMarcial;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfessorDao {

    public Professor salvar(Professor professor) {
        String sql = "INSERT INTO professores (nome, email, salario, tipo_de_arte_marcial) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setDouble(3, professor.getSalario());
            stmt.setString(4, professor.getTipoDeArteMarcial().name());

            stmt.executeUpdate();

            ResultSet chaves = stmt.getGeneratedKeys();
            if (chaves.next()) {
                professor.setId(chaves.getLong(1));
            }

            return professor;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao salvar professor.", erro);
        }
    }

    public List<Professor> listarTodos() {
        String sql = "SELECT * FROM professores";
        List<Professor> professores = new ArrayList<>();

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                professores.add(mapearProfessor(resultado));
            }

            return professores;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao listar professores.", erro);
        }
    }

    public Professor buscarPorId(Long id) {
        String sql = "SELECT * FROM professores WHERE id = ?";

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                return mapearProfessor(resultado);
            }

            return null;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao buscar professor.", erro);
        }
    }

    public Professor atualizar(Long id, Professor professor) {
        String sql = "UPDATE professores SET nome = ?, email = ?, salario = ?, tipo_de_arte_marcial = ? WHERE id = ?";

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setDouble(3, professor.getSalario());
            stmt.setString(4, professor.getTipoDeArteMarcial().name());
            stmt.setLong(5, id);

            stmt.executeUpdate();

            professor.setId(id);
            return professor;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao atualizar professor.", erro);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM professores WHERE id = ?";

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao deletar professor.", erro);
        }
    }

    private Professor mapearProfessor(ResultSet resultado) throws SQLException {
        Professor professor = new Professor();

        professor.setId(resultado.getLong("id"));
        professor.setNome(resultado.getString("nome"));
        professor.setEmail(resultado.getString("email"));
        professor.setSalario(resultado.getDouble("salario"));
        professor.setTipoDeArteMarcial(
                TipoArteMarcial.valueOf(resultado.getString("tipo_de_arte_marcial"))
        );

        return professor;
    }
}