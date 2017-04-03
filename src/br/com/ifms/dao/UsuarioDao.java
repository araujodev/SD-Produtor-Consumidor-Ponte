package br.com.ifms.dao;

import br.com.ifms.model.Usuario;
import br.com.ifms.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {

    Connection connection;

    public UsuarioDao() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void testeConexao() {
        try {
                System.out.println("Conexão aberta!");
                connection.close();
        } catch (SQLException e) {
                // TODO: handle exception
        }
    }
    
    public Usuario getUserByUsername(String email) throws SQLException{
        PreparedStatement stmt = this.connection.prepareStatement("select * from usuarios where email=?");
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        Usuario usuario = null;
        while (rs.next()) {
            usuario = new Usuario(rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
        }
        rs.close();
        stmt.close();
        return usuario;
    }
}