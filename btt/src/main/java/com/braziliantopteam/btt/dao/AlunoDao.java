package com.braziliantopteam.btt.dao;

import com.braziliantopteam.btt.entity.Aluno;
import com.braziliantopteam.btt.enums.Sexo;
import com.braziliantopteam.btt.enums.TipoArteMarcial;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AlunoDao {

    public Aluno salvar(Aluno aluno) {
<<<<<<< HEAD
        String sqlAluno = """
            INSERT INTO alunos (nome, email, telefone, sexo)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sqlAluno, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getTelefone());
            stmt.setString(4, aluno.getSexo().name());
=======
        String sql = "INSERT INTO alunos (nome, matricula, email, telefone, sexo, modalidade) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getTelefone());
            stmt.setString(5, aluno.getSexo().name());
            stmt.setString(6, aluno.getModalidade().name());
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583

            stmt.executeUpdate();

            ResultSet chaves = stmt.getGeneratedKeys();
            if (chaves.next()) {
                aluno.setId(chaves.getLong(1));
            }

<<<<<<< HEAD
            salvarModalidadesDoAluno(conexao, aluno);

=======
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583
            return aluno;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao salvar aluno.", erro);
        }
    }

    public List<Aluno> listarTodos() {
        String sql = "SELECT * FROM alunos";
        List<Aluno> alunos = new ArrayList<>();

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
<<<<<<< HEAD
                Aluno aluno = mapearAluno(resultado);
                aluno.setModalidades(buscarModalidadesDoAluno(conexao, aluno.getId()));
                alunos.add(aluno);
=======
                alunos.add(mapearAluno(resultado));
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583
            }

            return alunos;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao listar alunos.", erro);
        }
    }

    public Aluno buscarPorId(Long id) {
        String sql = "SELECT * FROM alunos WHERE id = ?";

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
<<<<<<< HEAD
                Aluno aluno = mapearAluno(resultado);
                aluno.setModalidades(buscarModalidadesDoAluno(conexao, aluno.getId()));
                return aluno;
=======
                return mapearAluno(resultado);
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583
            }

            return null;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao buscar aluno.", erro);
        }
    }

    public Aluno atualizar(Long id, Aluno aluno) {
<<<<<<< HEAD
        String sql = """
            UPDATE alunos
            SET nome = ?, email = ?, telefone = ?, sexo = ?
            WHERE id = ?
            """;
=======
        String sql = "UPDATE alunos SET nome = ?, matricula = ?, email = ?, telefone = ?, sexo = ?, modalidade = ? WHERE id = ?";
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
<<<<<<< HEAD
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getTelefone());
            stmt.setString(4, aluno.getSexo().name());
            stmt.setLong(5, id);
=======
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getTelefone());
            stmt.setString(5, aluno.getSexo().name());
            stmt.setString(6, aluno.getModalidade().name());
            stmt.setLong(7, id);
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583

            stmt.executeUpdate();

            aluno.setId(id);
<<<<<<< HEAD

            deletarModalidadesDoAluno(conexao, id);
            salvarModalidadesDoAluno(conexao, aluno);

=======
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583
            return aluno;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao atualizar aluno.", erro);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM alunos WHERE id = ?";

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

<<<<<<< HEAD
            deletarModalidadesDoAluno(conexao, id);

=======
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao deletar aluno.", erro);
        }
    }

    private Aluno mapearAluno(ResultSet resultado) throws SQLException {
        Aluno aluno = new Aluno();

        aluno.setId(resultado.getLong("id"));
        aluno.setNome(resultado.getString("nome"));
<<<<<<< HEAD
        aluno.setEmail(resultado.getString("email"));
        aluno.setTelefone(resultado.getString("telefone"));
        aluno.setSexo(Sexo.valueOf(resultado.getString("sexo")));

        return aluno;
    }

    private void salvarModalidadesDoAluno(Connection conexao, Aluno aluno) throws SQLException {
        String sql = """
                INSERT INTO aluno_modalidades (aluno_id, modalidade)
                VALUES (?, ?)
                """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            for (TipoArteMarcial modalidade : aluno.getModalidades()) {
                stmt.setLong(1, aluno.getId());
                stmt.setString(2, modalidade.name());
                stmt.addBatch();
            }

            stmt.executeBatch();
        }
    }

    private List<TipoArteMarcial> buscarModalidadesDoAluno(Connection conexao, Long alunoId) throws SQLException {
        String sql = "SELECT modalidade FROM aluno_modalidades WHERE aluno_id = ?";
        List<TipoArteMarcial> modalidades = new ArrayList<>();

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, alunoId);

            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                modalidades.add(
                        TipoArteMarcial.valueOf(resultado.getString("modalidade"))
                );
            }
        }

        return modalidades;
    }

    private void deletarModalidadesDoAluno(Connection conexao, Long alunoId) throws SQLException {
        String sql = "DELETE FROM aluno_modalidades WHERE aluno_id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, alunoId);
            stmt.executeUpdate();
        }
    }
=======
        aluno.setMatricula(resultado.getString("matricula"));
        aluno.setEmail(resultado.getString("email"));
        aluno.setTelefone(resultado.getString("telefone"));
        aluno.setSexo(Sexo.valueOf(resultado.getString("sexo")));
        aluno.setModalidade(TipoArteMarcial.valueOf(resultado.getString("modalidade")));

        return aluno;
    }
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583
}