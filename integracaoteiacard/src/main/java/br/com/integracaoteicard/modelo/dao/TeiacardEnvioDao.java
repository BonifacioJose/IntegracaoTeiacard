package br.com.integracaoteicard.modelo.dao;

import br.com.integracaoteiacard.database.Dao;
import br.com.integracaoteiacard.database.DaoImpl;
import br.com.integracaoteiacard.database.DatabaseEntity;
import br.com.integracaoteiacard.modelo.entidade.TeiaCardEnvio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author bonif
 */
public class TeiacardEnvioDao extends DaoImpl<TeiaCardEnvio> implements Dao<TeiaCardEnvio> {
    
    public TeiacardEnvioDao(Connection conexao) {
        super(conexao,
                "teiacard_envio",
                "insert into teiacard_envio (data_envio) values (?) returning id",
                "");
    }
    
     
    @Override
    public <T extends DatabaseEntity> T extrair(ResultSet rs) throws SQLException {
        TeiaCardEnvio teiacardEnvio = new TeiaCardEnvio();
        teiacardEnvio.setId(rs.getLong("id"));
        teiacardEnvio.setDataEnvio(rs.getTimestamp("data_envio"));
        return (T) teiacardEnvio;
    }

    @Override
    public <T extends DatabaseEntity> void prepararInsert(T t) throws SQLException {
        getPs().setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
    }
   
}
