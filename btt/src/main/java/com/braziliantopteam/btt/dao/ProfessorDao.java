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
        String sqlProfessor = """
                INSERT INTO professores (nome, email, salario)
                VALUES (?, ?, ?)
                """;

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sqlProfessor, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setDouble(3, professor.getSalario());

            stmt.executeUpdate();

            ResultSet chaves = stmt.getGeneratedKeys();
            if (chaves.next()) {
                professor.setId(chaves.getLong(1));
            }

            salvarModalidadesDoProfessor(conexao, professor);

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
                Professor professor = mapearProfessor(resultado);
                professor.setTiposDeArteMarcial(
                        buscarModalidadesDoProfessor(conexao, professor.getId())
                );
                professores.add(professor);
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
                Professor professor = mapearProfessor(resultado);
                professor.setTiposDeArteMarcial(
                        buscarModalidadesDoProfessor(conexao, professor.getId())
                );
                return professor;
            }

            return null;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao buscar professor.", erro);
        }
    }

    public Professor atualizar(Long id, Professor professor) {
        String sql = """
                UPDATE professores
                SET nome = ?, email = ?, salario = ?
                WHERE id = ?
                """;

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setDouble(3, professor.getSalario());
            stmt.setLong(4, id);

            stmt.executeUpdate();

            professor.setId(id);

            deletarModalidadesDoProfessor(conexao, id);
            salvarModalidadesDoProfessor(conexao, professor);

            return professor;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao atualizar professor.", erro);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM professores WHERE id = ?";

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            deletarModalidadesDoProfessor(conexao, id);

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

        return professor;
    }

    private void salvarModalidadesDoProfessor(Connection conexao, Professor professor) throws SQLException {
        String sql = """
                INSERT INTO professor_modalidades (professor_id, modalidade)
                VALUES (?, ?)
                """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            for (TipoArteMarcial modalidade : professor.getTiposDeArteMarcial()) {
                stmt.setLong(1, professor.getId());
                stmt.setString(2, modalidade.name());
                stmt.addBatch();
            }

            stmt.executeBatch();
        }
    }

    private List<TipoArteMarcial> buscarModalidadesDoProfessor(Connection conexao, Long professorId) throws SQLException {
        String sql = "SELECT modalidade FROM professor_modalidades WHERE professor_id = ?";
        List<TipoArteMarcial> modalidades = new ArrayList<>();

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, professorId);

            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                modalidades.add(
                        TipoArteMarcial.valueOf(resultado.getString("modalidade"))
                );
            }
        }

        return modalidades;
    }

    private void deletarModalidadesDoProfessor(Connection conexao, Long professorId) throws SQLException {
        String sql = "DELETE FROM professor_modalidades WHERE professor_id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, professorId);
            stmt.executeUpdate();
        }
    }
}