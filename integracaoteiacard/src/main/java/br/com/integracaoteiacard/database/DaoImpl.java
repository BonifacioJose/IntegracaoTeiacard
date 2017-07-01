package br.com.integracaoteiacard.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author José
 */
public class DaoImpl <T extends DatabaseEntity> implements Dao<T>  {
    
    private final Connection conexao;
    private PreparedStatement ps;
    private ResultSet rs;
    private final String tabela;
    private final String insertSql;
    private final String updateSql;

    public DaoImpl(Connection conexao, String tabela, String insertSql, String updateSql) {
        this.conexao = conexao;
        this.tabela = tabela;
        this.insertSql = insertSql;
        this.updateSql = updateSql;
    }

    @Override
    public <T extends DatabaseEntity> void salvar(T t) throws SQLException {
        if (t.isNovo()) {
            ps = conexao.prepareStatement(insertSql);
            prepararInsert(t);
        } else {
            ps = conexao.prepareStatement(updateSql);
            prepararUpdate(t);
        }
        ps.execute();
    }

    @Override
    public <T extends DatabaseEntity> T salvarComRetorno(T t) throws SQLException { 
        if (t.isNovo()) {
            ps = conexao.prepareStatement(insertSql);
            prepararInsert(t);
        } else {
            ps = conexao.prepareStatement(updateSql);
            prepararUpdate(t);
            return t;
        }
        String id = String.valueOf(ps.executeUpdate());
        return buscarPorId(Long.valueOf(id));
    }

    @Override
    public <T extends DatabaseEntity> void deletar(T t) throws SQLException {
        String sql = "delete from " + tabela + " where id = " + t.getId();
        ps.executeUpdate(sql);
    }

    @Override
    public <T extends DatabaseEntity> T buscarPorId(Long id) throws SQLException {
        String sql = "select * from " + tabela + " where id = " + id;
        buscar(sql);
        if (rs.next()) {
            return (T) extrair(rs);
        }
        return null;
    }

    @Override
    public List<T> buscarTodos() throws SQLException {
        String sql = "select * from " + tabela;
        buscar(sql);
        List<T> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add((T) extrair(rs));
        }
        return lista;
    }
    
    
    @Override
    public <T extends DatabaseEntity> ResultSet buscar(String sql) throws SQLException {
        ps = conexao.prepareStatement(sql);
        rs = ps.executeQuery();
        return rs;  
    }  

    @Override
    public void fecharPonteiros() { 
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException ex) {            
        }
    }

    @Override
    public void fecharConexoes() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {            
        }
    }
    
    public PreparedStatement getPs() {
        return this.ps;
    }
    
    public ResultSet getRs() {
        return this.rs;
    }

    @Override
    public <T extends DatabaseEntity> T extrair(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T extends DatabaseEntity> void prepararInsert(T t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T extends DatabaseEntity> void prepararUpdate(T t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
