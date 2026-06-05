package com.braziliantopteam.btt.dao;

import com.braziliantopteam.btt.entity.Modalidade;
import com.braziliantopteam.btt.enums.TipoArteMarcial;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ModalidadeDao {

    public Modalidade salvar(Modalidade modalidade) {
        String sql = "INSERT INTO modalidades (tipo, descricao, horario_aula, valor_mensalidade) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, modalidade.getTipo().name());
            stmt.setString(2, modalidade.getDescricao());
            stmt.setString(3, modalidade.getHorarioAula());
            stmt.setDouble(4, modalidade.getValorMensalidade());

            stmt.executeUpdate();

            ResultSet chaves = stmt.getGeneratedKeys();
            if (chaves.next()) {
                modalidade.setId(chaves.getLong(1));
            }

            return modalidade;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao salvar modalidade.", erro);
        }
    }

    public List<Modalidade> listarTodos() {
        String sql = "SELECT * FROM modalidades";
        List<Modalidade> modalidades = new ArrayList<>();

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                modalidades.add(mapearModalidade(resultado));
            }

            return modalidades;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao listar modalidades.", erro);
        }
    }

    public Modalidade buscarPorId(Long id) {
        String sql = "SELECT * FROM modalidades WHERE id = ?";

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                return mapearModalidade(resultado);
            }

            return null;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao buscar modalidade.", erro);
        }
    }

    public Modalidade atualizar(Long id, Modalidade modalidade) {
        String sql = "UPDATE modalidades SET tipo = ?, descricao = ?, horario_aula = ?, valor_mensalidade = ? WHERE id = ?";

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, modalidade.getTipo().name());
            stmt.setString(2, modalidade.getDescricao());
            stmt.setString(3, modalidade.getHorarioAula());
            stmt.setDouble(4, modalidade.getValorMensalidade());
            stmt.setLong(5, id);

            stmt.executeUpdate();

            modalidade.setId(id);
            return modalidade;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao atualizar modalidade.", erro);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM modalidades WHERE id = ?";

        try (Connection conexao = ConexaoFactory.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao deletar modalidade.", erro);
        }
    }

    private Modalidade mapearModalidade(ResultSet resultado) throws SQLException {
        Modalidade modalidade = new Modalidade();

        modalidade.setId(resultado.getLong("id"));
        modalidade.setTipo(TipoArteMarcial.valueOf(resultado.getString("tipo")));
        modalidade.setDescricao(resultado.getString("descricao"));
        modalidade.setHorarioAula(resultado.getString("horario_aula"));
        modalidade.setValorMensalidade(resultado.getDouble("valor_mensalidade"));

        return modalidade;
    }
}