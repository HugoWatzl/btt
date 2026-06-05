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
        String sql = "INSERT INTO alunos (nome, matricula, email, telefone, sexo, modalidade) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getTelefone());
            stmt.setString(5, aluno.getSexo().name());
            stmt.setString(6, aluno.getModalidade().name());

            stmt.executeUpdate();

            ResultSet chaves = stmt.getGeneratedKeys();
            if (chaves.next()) {
                aluno.setId(chaves.getLong(1));
            }

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
                alunos.add(mapearAluno(resultado));
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
                return mapearAluno(resultado);
            }

            return null;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao buscar aluno.", erro);
        }
    }

    public Aluno atualizar(Long id, Aluno aluno) {
        String sql = "UPDATE alunos SET nome = ?, matricula = ?, email = ?, telefone = ?, sexo = ?, modalidade = ? WHERE id = ?";

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getTelefone());
            stmt.setString(5, aluno.getSexo().name());
            stmt.setString(6, aluno.getModalidade().name());
            stmt.setLong(7, id);

            stmt.executeUpdate();

            aluno.setId(id);
            return aluno;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao atualizar aluno.", erro);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM alunos WHERE id = ?";

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

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
        aluno.setMatricula(resultado.getString("matricula"));
        aluno.setEmail(resultado.getString("email"));
        aluno.setTelefone(resultado.getString("telefone"));
        aluno.setSexo(Sexo.valueOf(resultado.getString("sexo")));
        aluno.setModalidade(TipoArteMarcial.valueOf(resultado.getString("modalidade")));

        return aluno;
    }
}