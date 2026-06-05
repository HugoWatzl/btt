package com.braziliantopteam.btt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/btt";
    private static final String USUARIO = "root";
    private static final String SENHA = "senha";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao conectar com o banco de dados.", erro);
        }
    }
}