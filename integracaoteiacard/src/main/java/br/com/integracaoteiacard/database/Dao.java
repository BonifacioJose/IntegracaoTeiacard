package br.com.integracaoteiacard.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Bonifácio
 * @param <T>
 */
public interface Dao<T extends DatabaseEntity> {
    
    public <T extends DatabaseEntity> void salvar(T t) throws SQLException;
    public <T extends DatabaseEntity> T salvarComRetorno(T t) throws SQLException;
    public <T extends DatabaseEntity> void deletar(T t) throws SQLException;
    public <T extends DatabaseEntity> T buscarPorId(Long id) throws SQLException;
    public List<T> buscarTodos() throws SQLException;
    public <T extends DatabaseEntity> ResultSet buscar(String sql) throws SQLException;   
    public <T extends DatabaseEntity> T extrair(ResultSet rs) throws SQLException;
    public <T extends DatabaseEntity> void prepararInsert(T t) throws SQLException;
    public <T extends DatabaseEntity> void prepararUpdate(T t) throws SQLException;
    public void fecharPonteiros();
    public void fecharConexoes();
    
}
