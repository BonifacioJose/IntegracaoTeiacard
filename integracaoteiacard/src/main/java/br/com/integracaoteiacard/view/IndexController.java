package br.com.integracaoteiacard.view;

import br.com.integracaoteiacard.database.Dao;
import br.com.integracaoteiacard.database.DaoImpl;
import br.com.integracaoteiacard.modelo.entidade.Arquivo;
import br.com.integracaoteiacard.modelo.entidade.ArquivoFisico;
import br.com.integracaoteiacard.modelo.entidade.Campo;
import br.com.integracaoteiacard.modelo.entidade.FormatoCampo;
import br.com.integracaoteiacard.modelo.entidade.Linha;
import br.com.integracaoteiacard.modelo.entidade.TeiaCardEnvio;
import br.com.integracaoteiacard.modelo.entidade.TipoCampoDate;
import br.com.integracaoteiacard.modelo.entidade.TipoLinha;
import br.com.integracaoteiacard.modelo.service.LynxService;
import br.com.integracaoteiacard.util.ArquivoUtil;
import br.com.integracaoteiacard.util.DatabaseUtil;
import br.com.integracaoteiacard.util.DateUtil;
import br.com.integracaoteiacard.util.SystemUtil;
import br.com.integracaoteicard.modelo.dao.TeiacardEnvioDao;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Bonifácio
 */
@Named
@ViewScoped
public class IndexController implements Serializable {

    public ArquivoFisico getArquivoTeste() {
        Connection conexao = DatabaseUtil.getConnection();
        DaoImpl dao = new DaoImpl(conexao, "", "", "");
        TeiacardEnvioDao teiacardEnvioDao = new TeiacardEnvioDao(conexao);
        try {
            TeiaCardEnvio envio = new TeiaCardEnvio();
            envio = teiacardEnvioDao.salvarComRetorno(envio);
            String retorno = "";
            for (int i = 0; i < (5 - String.valueOf(envio.getId()).length()); i++) {
                retorno += "0";
            }
            retorno += String.valueOf(envio.getId());

            LynxService lynxService = new LynxService(dao, teiacardEnvioDao);
            List<Linha> linhas = new ArrayList<>();
            linhas.addAll(Arrays.asList(lynxService.getHeaderArquivoEHeaderLote(envio)));
            List<Linha> detalhes = lynxService.getRegistrosDetalhesSegmentoV();
            linhas.addAll(detalhes);
            linhas.add(lynxService.getTrailerArquivo(linhas.size() - detalhes.size()));
            Arquivo arquivo = new Arquivo(linhas.toArray(new Linha[linhas.size()]));
            ArquivoFisico arquivoFisico = new ArquivoFisico("teste", arquivo);

            ArquivoUtil.salvarArquivo("", "\\" + retorno + DateUtil.getFormatoData(new Date()) + ".REM", new ByteArrayInputStream(arquivo.toString().getBytes()));
            
            return arquivoFisico;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dao.fecharConexoes();
            teiacardEnvioDao.fecharConexoes();
        }

        int quantidadeRegistros = 20;
        int quantidadeLotes = 5;

        //ArquivoUtil.salvarArquivo("", "\\" + DateUtil.getFormatoData(new Date()) + ".REM", new ByteArrayInputStream(arquivoTeste.getArquivo().toString().getBytes()));
        return null;
    }

}
