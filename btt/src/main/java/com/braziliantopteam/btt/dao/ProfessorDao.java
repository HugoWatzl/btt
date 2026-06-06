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
<<<<<<< HEAD
        String sqlProfessor = """
                INSERT INTO professores (nome, email, salario)
                VALUES (?, ?, ?)
                """;

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sqlProfessor, Statement.RETURN_GENERATED_KEYS)) {
=======
        String sql = "INSERT INTO professores (nome, email, salario, tipo_de_arte_marcial) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setDouble(3, professor.getSalario());
<<<<<<< HEAD
=======
            stmt.setString(4, professor.getTipoDeArteMarcial().name());
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583

            stmt.executeUpdate();

            ResultSet chaves = stmt.getGeneratedKeys();
            if (chaves.next()) {
                professor.setId(chaves.getLong(1));
            }

<<<<<<< HEAD
            salvarModalidadesDoProfessor(conexao, professor);

=======
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583
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
<<<<<<< HEAD
                Professor professor = mapearProfessor(resultado);
                professor.setTiposDeArteMarcial(
                        buscarModalidadesDoProfessor(conexao, professor.getId())
                );
                professores.add(professor);
=======
                professores.add(mapearProfessor(resultado));
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583
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
<<<<<<< HEAD
                Professor professor = mapearProfessor(resultado);
                professor.setTiposDeArteMarcial(
                        buscarModalidadesDoProfessor(conexao, professor.getId())
                );
                return professor;
=======
                return mapearProfessor(resultado);
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583
            }

            return null;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao buscar professor.", erro);
        }
    }

    public Professor atualizar(Long id, Professor professor) {
<<<<<<< HEAD
        String sql = """
                UPDATE professores
                SET nome = ?, email = ?, salario = ?
                WHERE id = ?
                """;
=======
        String sql = "UPDATE professores SET nome = ?, email = ?, salario = ?, tipo_de_arte_marcial = ? WHERE id = ?";
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setDouble(3, professor.getSalario());
<<<<<<< HEAD
            stmt.setLong(4, id);
=======
            stmt.setString(4, professor.getTipoDeArteMarcial().name());
            stmt.setLong(5, id);
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583

            stmt.executeUpdate();

            professor.setId(id);
<<<<<<< HEAD

            deletarModalidadesDoProfessor(conexao, id);
            salvarModalidadesDoProfessor(conexao, professor);

=======
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583
            return professor;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao atualizar professor.", erro);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM professores WHERE id = ?";

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

<<<<<<< HEAD
            deletarModalidadesDoProfessor(conexao, id);

=======
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583
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
<<<<<<< HEAD

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
=======
        professor.setTipoDeArteMarcial(
                TipoArteMarcial.valueOf(resultado.getString("tipo_de_arte_marcial"))
        );

        return professor;
    }
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583
}