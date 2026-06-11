package com.braziliantopteam.btt.dao;

import com.braziliantopteam.btt.entity.Aula;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AulaDao {

    public Aula salvar(Aula aula) {
        String sql = """
                INSERT INTO aulas (modalidade_id, dia_semana, horario)
                VALUES (?, ?, ?)
                """;

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, aula.getModalidadeId());
            stmt.setString(2, aula.getDiaSemana());
            stmt.setString(3, aula.getHorario());

            stmt.executeUpdate();

            ResultSet chaves = stmt.getGeneratedKeys();
            if (chaves.next()) {
                aula.setId(chaves.getLong(1));
            }

            return aula;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao salvar aula.", erro);
        }
    }

    public List<Aula> listarTodos() {
        String sql = "SELECT * FROM aulas";
        List<Aula> aulas = new ArrayList<>();

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                aulas.add(mapearAula(resultado));
            }

            return aulas;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao listar aulas.", erro);
        }
    }

    public Aula buscarPorId(Long id) {
        String sql = "SELECT * FROM aulas WHERE id = ?";

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                return mapearAula(resultado);
            }

            return null;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao buscar aula.", erro);
        }
    }

    public Aula atualizar(Long id, Aula aula) {
        String sql = """
                UPDATE aulas
                SET modalidade_id = ?, dia_semana = ?, horario = ?
                WHERE id = ?
                """;

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, aula.getModalidadeId());
            stmt.setString(2, aula.getDiaSemana());
            stmt.setString(3, aula.getHorario());
            stmt.setLong(4, id);

            stmt.executeUpdate();

            aula.setId(id);
            return aula;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao atualizar aula.", erro);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM aulas WHERE id = ?";

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao deletar aula.", erro);
        }
    }

    private Aula mapearAula(ResultSet resultado) throws SQLException {
        Aula aula = new Aula();

        aula.setId(resultado.getLong("id"));
        aula.setModalidadeId(resultado.getLong("modalidade_id"));
        aula.setDiaSemana(resultado.getString("dia_semana"));
        aula.setHorario(resultado.getString("horario"));

        return aula;
    }
}