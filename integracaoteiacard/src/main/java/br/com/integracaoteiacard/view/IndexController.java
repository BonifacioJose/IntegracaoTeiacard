package br.com.integracaoteiacard.view;

import br.com.integracaoteiacard.database.Dao;
import br.com.integracaoteiacard.database.DaoImpl;
import br.com.integracaoteiacard.modelo.Arquivo;
import br.com.integracaoteiacard.modelo.ArquivoTeste;
import br.com.integracaoteiacard.modelo.Campo;
import br.com.integracaoteiacard.modelo.FormatoCampo;
import br.com.integracaoteiacard.modelo.TipoCampoDate;
import br.com.integracaoteiacard.util.ArquivoUtil;
import br.com.integracaoteiacard.util.DatabaseUtil;
import br.com.integracaoteiacard.util.SystemUtil;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Bonifácio
 */
@Named
@ViewScoped
public class IndexController implements Serializable {

    public ArquivoTeste getArquivoTeste() {
        Connection conexao = DatabaseUtil.getConnection();
        DaoImpl dao = new DaoImpl(conexao, "", "", "");
        try {        
        String sql = "select lc.* from lista_compra as lc";
        ResultSet result = dao.buscar(sql);
        while (result.next()) {
            System.out.println("teste1: " + result.getInt("id"));
        }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dao.fecharConexoes();
        }
        
        
        ArquivoTeste arquivoTeste = new ArquivoTeste("Arquivo Teste1",
                new Arquivo(
                        new Campo("01.1", "Código da Empresa", 8, 0, FormatoCampo.NUMERICO, "1111", null, "GRN.001"),
                        new Campo("01.1", "Código da Empresa", 3, 0, FormatoCampo.ALFANUMERICO, "1134", "2445", "GRN.001"),
                        new Campo("01.1", "Código da Empresa", 6, new Date(), new Date(), TipoCampoDate.DATE, "GRN.001"),
                        new Campo("01.1", "Código da Empresa", 8, new Date(), new Date(), TipoCampoDate.TIMESTAMP, "GRN.001"),
                        new Campo("01.1", "Código da Empresa", 3, 0, FormatoCampo.ALFANUMERICO, null, "abcdefghijabcdefghijabcdefghijabcdefghijabc", "GRN.001"),
                        new Campo("01.1", "Código da Empresa", 3, 0, FormatoCampo.ALFANUMERICO, null, "5", "GRN.001")
                        ));
        ArquivoUtil.salvarArquivo("","\\arquivo.txt",new ByteArrayInputStream(arquivoTeste.getArquivo().toString().getBytes()));
        return arquivoTeste;        
    }
        
}
