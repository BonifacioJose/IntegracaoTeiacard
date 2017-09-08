package br.com.integracaoteiacard.modelo.service;

import br.com.integracaoteiacard.database.DaoImpl;
import br.com.integracaoteiacard.modelo.entidade.Campo;
import br.com.integracaoteiacard.modelo.entidade.FormatoCampo;
import br.com.integracaoteiacard.modelo.entidade.Linha;
import br.com.integracaoteiacard.modelo.entidade.TeiaCardEnvio;
import br.com.integracaoteiacard.modelo.entidade.TipoCampoDate;
import br.com.integracaoteiacard.modelo.entidade.TipoLinha;
import br.com.integracaoteicard.modelo.dao.TeiacardEnvioDao;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Bonifácio
 */
public class LynxService {

    private final DaoImpl dao;
    private final TeiacardEnvioDao teiacardEnvioDao;

    public LynxService(DaoImpl dao, TeiacardEnvioDao teiacardEnvioDao) {
        this.dao = dao;
        this.teiacardEnvioDao = teiacardEnvioDao;
    }

    public Linha[] getHeaderArquivoEHeaderLote(TeiaCardEnvio teiaCardEnvio) throws SQLException {
        Linha[] linhas = new Linha[2];
        String sql = "SELECT "
                + "  empresa.codigo 	as codigo_da_empresa, "
                + "  '0001' 		as Lote_Servico, "
                + "  1 		as Tipo_Registro, "
                + "  01 		as Tipo_Servico, "
                + "  '' 		as uso_netunna, "
                + "  2 		as Tipo_Insc_Empresa, "
                + "  empresa.cpf 	as Numero_Insc_Empresa, "
                + "  empresa.nome 	as nome_empresa, "
                + "  1 		as Codigo_Loja, "
                + "  '' 		as numero_estab_adquirente, "
                + "  '' 		as uso_reservado_empresa, "
                + "  '' 		as uso_exclusivo_netunna "
                + "  FROM movto"
                + "  INNER JOIN empresa ON empresa.grid = movto. empresa "
                + "  WHERE "
                + "  data BETWEEN '2017-07-12' AND '2017-07-12'"
                + "  AND conta_debitar LIKE ('1.3.01.%')"
                + "  AND conta_debitar NOT LIKE ('1.3.01.4%') "
                + "  lIMIT 1";
        ResultSet rs = dao.buscar(sql);
        if (rs.next()) {
            linhas[0] = new Linha(TipoLinha.HEADER_ARQUIVO,
                    new Campo("01.0", "Código da Empresa", 3, 0, FormatoCampo.NUMERICO, null, "1", "GNR.001"),
                    new Campo("02.0", "Lote de Serviço", 4, 0, FormatoCampo.NUMERICO, "0000", "1", "GNR.002"),
                    new Campo("03.0", "Tipo do Registro", 1, 0, FormatoCampo.NUMERICO, "0", "1", "GNR.003"),
                    new Campo("04.0", "Uso Exclusivo Netunna", 9, 0, FormatoCampo.ALFANUMERICO, null, "", "GNR.004"),
                    new Campo("05.0", "Tipo de Inscrição da Empresa", 1, 0, FormatoCampo.NUMERICO, null, "2", "GNR.005"),
                    new Campo("06.0", "Número de Inscrição da Empresa", 14, 0, FormatoCampo.NUMERICO, null, "", "GNR.006"),
                    new Campo("07.0", "Nome da Empresa", 40, 0, FormatoCampo.ALFANUMERICO, null, "", "GNR.007"),
                    new Campo("08.0", "Nome da Adquirente", 40, 0, FormatoCampo.ALFANUMERICO, null, "GETNET", "GNR.008"),
                    new Campo("09.0", "Código de Remessa ou de Retorno ", 1, 0, FormatoCampo.NUMERICO, null, "1", "GNR.009"),
                    new Campo("10.0", "Data de Geração do Arquivo ", 8, null, new Date(), TipoCampoDate.DATE, "GNR.010"),
                    new Campo("11.0", "Hora de Geração do Arquivo", 6, null, new Date(), TipoCampoDate.TIMESTAMP, "GNR.011"),
                    new Campo("12.0", "Número Sequencial do Arquivo (NSA)", 7, 0, FormatoCampo.NUMERICO, null, String.valueOf(teiaCardEnvio.getId().intValue()), "GNR.012"),
                    new Campo("13.0", "Número da Versão do Leiaute do Arquivo", 5, 0, FormatoCampo.NUMERICO, null, "01.08", "GNR.013"),
                    new Campo("14.0", "Uso Reservado para a Empresa", 40, 0, FormatoCampo.ALFANUMERICO, null, null, "GNR.014"),
                    new Campo("15.0", "Uso Exclusivo Netunna", 121, 0, FormatoCampo.ALFANUMERICO, null, null, "GNR.004"));

            linhas[1] = new Linha(TipoLinha.HEADER_LOTE,
                    new Campo("01.1", "Código da Empresa", 3, 0, FormatoCampo.NUMERICO, null, "1", "GNR.001"),
                    new Campo("02.1", "Lote de Serviço", 4, 0, FormatoCampo.NUMERICO, "0001", "1", "GNR.002"),
                    new Campo("03.1", "Tipo do Registro", 1, 0, FormatoCampo.NUMERICO, "1", "1", "GNR.003"),
                    new Campo("04.1", "Tipo de Serviço", 2, 0, FormatoCampo.NUMERICO, "01", "01", "GNR.017"),
                    new Campo("05.1", "Uso Exclusivo Netunna", 7, 0, FormatoCampo.ALFANUMERICO, null, "0", "GNR.004"),
                    new Campo("06.1", "Tipo de Inscrição Empresa", 1, 0, FormatoCampo.NUMERICO, null, rs.getString("Tipo_Insc_Empresa"), "GNR.005"),
                    new Campo("07.1", "Número de Inscrição da Empresa", 14, 0, FormatoCampo.NUMERICO, null, rs.getString("numero_insc_empresa").replaceAll("\\.", "").replaceAll("/", "").replaceAll("-",""), "GNR.006"),
                    new Campo("08.1", "Código da Loja", 5, 0, FormatoCampo.ALFANUMERICO, null, rs.getString("codigo_loja"), "GNR.018"),
                    new Campo("09.1", "Número do Estabelecimento na Adquirente", 18, 0, FormatoCampo.ALFANUMERICO, null, rs.getString("numero_estab_adquirente"), "GNR.019"),
                    new Campo("10.1", "Uso Reservado para a Empresa", 40, 0, FormatoCampo.ALFANUMERICO, null, rs.getString("uso_reservado_empresa"), "GNR.014"),
                    new Campo("11.1", "Uso Exclusivo Netunna", 205, 0, FormatoCampo.ALFANUMERICO, null, "", "GNR.004")
            );
            return linhas;
        }
        return null;
    }

    public List<Linha> getRegistrosDetalhesSegmentoV() throws SQLException {
        List<Linha> linhas = new ArrayList<>();
        String sql = "SELECT "
                + " empresa.codigo 		as codigo_empresa, "
                + " '0001'			as lote_servico, "
                + " '2' 			as tipo_registro, "
                + " '01'	as tipo_servico,"
                + " '123' 			as Numero_seq_registro_lote, "
                + " 'V' 			as codigo_seq_registro_detalhe, "
                + " '' 			as uso_netunna, "
                + " '2' 			as tipo_insc_empresa, "
                + " empresa.cpf 		as numero_insc_empresa, "
                + " empresa.codigo		as codigo_loja, "
                + " ''	 		as numero_esta_adquirente, "
                + " movto.conta_creditar	as numero_caixa, "
                + " ''	 		as numero_maquineta_adquirente, "
                + " '' 			as numero_cupom_fiscal, "
                + " '' 			as uso_exclusivo_netunna, "
                + " ''	 		as codigo_autorizacao, "
                + "  '' 		as numero_estab_adquirente, "
                + " data 			as data_venda, "
                + " hora 			as hora_venda,"
                + " valor 			as valor_bruto_venda,"
                + " '1' 			as plano, "
                + " conta.nome		as bandeira, "
                + " '' 			as numero_cartao, "
                + " '' 			as nome_proprietario_cartao, "
                + " movto.usuario		as nome_operador_caixa, "
                + " '' 			as indicador_programa_promocional, "
                + " 002 			as meio_captura, "
                + " '' 			as numero_pos_adquirente, "
                + " 0 			as taxa_comissao, "
                + " '' 			as NSU_CV_DOC, "
                + " '' 			as uso_exclusivo_netunna"
                + " FROM MOVTO "
                + " INNER JOIN empresa 		ON empresa.grid 		= movto.empresa"
                + " INNER JOIN conta 		ON movto.conta_debitar = conta.codigo"
                + " LEFT JOIN  motivo_movto 	ON motivo_movto.grid 		= movto.motivo"
                + " LEFT JOIN  classificacao_pgto 	ON classificacao_pgto.grid	= motivo_movto.classificacao_pgto"
                + " WHERE "
                + " data = '2017-07-12'"
                + " AND movto.conta_debitar 	LIKE 		('1.3.01.%')"
                + " AND movto.conta_debitar 	NOT LIKE 	('1.3.01.4%')";
        ResultSet rs = dao.buscar(sql);

        int totalRegistros = 0;
        BigDecimal valorBrutoTotal = new BigDecimal(BigInteger.ZERO);
        while (rs.next()) {
            totalRegistros += 1;
            valorBrutoTotal.add(rs.getBigDecimal("valor_bruto_venda").setScale(2, RoundingMode.HALF_UP));
            linhas.add(new Linha(TipoLinha.REGISTRO_DETALHE_VENDA,
                    new Campo("01.2V", "Código da Empresa", 3, 0, FormatoCampo.NUMERICO, null, "1", "GNR.001"),
                    new Campo("02.2V", "Lote de Serviço", 4, 0, FormatoCampo.NUMERICO, null, "1", "GNR.002"),
                    new Campo("03.2V", "Tipo do Registro", 1, 0, FormatoCampo.NUMERICO, "2", "1", "GNR.003"),
                    new Campo("04.2V", "Tipo de Serviço", 2, 0, FormatoCampo.NUMERICO, null, "01", "GNR.017"),
                    new Campo("05.2V", "Número Sequencial do Registro no Lote", 5, 0, FormatoCampo.NUMERICO, null, String.valueOf(totalRegistros), "GNR.020"),
                    new Campo("06.2V", "Código do Segmento do Registro Detalhe", 1, 0, FormatoCampo.ALFANUMERICO, TipoLinha.REGISTRO_DETALHE_VENDA.getLetra(), rs.getString("codigo_seq_registro_detalhe"), "GNR.021"),
                    new Campo("07.2V", "Uso Exclusivo Netunna", 1, 0, FormatoCampo.ALFANUMERICO, null, "", "GNR.004"),
                    new Campo("08.2V", "Tipo de Inscrição Empresa", 1, 0, FormatoCampo.NUMERICO, null, rs.getString("tipo_insc_empresa"), "GNR.005"),
                    new Campo("09.2V", "Número de Inscrição da Empresa", 14, 0, FormatoCampo.NUMERICO, null, rs.getString("numero_insc_empresa").replaceAll("\\.", "").replaceAll("/", "").replaceAll("-",""), "GNR.006"),
                    new Campo("10.2V", "Código da Loja", 5, 0, FormatoCampo.ALFANUMERICO, null, rs.getString("codigo_loja"), "GNR.018"),
                    new Campo("11.2V", "Número do Estabelecimento na Adquirente", 18, 0, FormatoCampo.ALFANUMERICO, null, rs.getString("numero_esta_adquirente"), "GNR.019"),
                    new Campo("12.2V", "Número do Caixa/Checkout", 6, 0, FormatoCampo.ALFANUMERICO, null, "", "GNR.022"),
                    new Campo("13.2V", "Número do PDV (Maquineta) na Adquirente", 15, 0, FormatoCampo.NUMERICO, null, rs.getString("numero_maquineta_adquirente"), "GNR.023"),
                    new Campo("14.2V", "Número do Cupom Fiscal / Número do Pedido", 10, 0, FormatoCampo.NUMERICO, null, rs.getString("numero_cupom_fiscal"), "GNR.024"),
                    new Campo("15.2V", "Uso Exclusivo Netunna", 10, 0, FormatoCampo.ALFANUMERICO, null, "", "GNR.004"),
                    new Campo("16.2V", "Código da Autorização ", 10, 0, FormatoCampo.ALFANUMERICO, null, rs.getString("codigo_autorizacao"), "GNR.026"),
                    new Campo("17.2V", "Data da Venda", 8, null, new Date(), TipoCampoDate.DATE, "GNR.027"),
                    new Campo("18.2V", "Hora da Venda", 6, null, new Date(), TipoCampoDate.TIMESTAMP, "GNR.028"),
                    new Campo("19.2V", "Valor Bruto da Venda", 10, 0, FormatoCampo.NUMERICO, null, rs.getBigDecimal("valor_bruto_venda").setScale(2, RoundingMode.HALF_UP).toString(), "GNR.029"),
                    new Campo("20.2V", "Plano (Quantidade de Parcelas)", 2, 0, FormatoCampo.NUMERICO, null, rs.getString("plano"), "GNR.030"),
                    new Campo("21.2V", "Código da Bandeira", 3, 0, FormatoCampo.NUMERICO, null, rs.getString("bandeira").substring(0,3), "GNR.031"),
                    new Campo("22.2V", "Número do Cartão (Truncado) ", 19, 0, FormatoCampo.ALFANUMERICO, null, rs.getString("numero_cartao"), "GNR.032"),
                    new Campo("23.2V", "Nome do Proprietário do Cartão", 25, 0, FormatoCampo.ALFANUMERICO, null, rs.getString("nome_proprietario_cartao"), "GNR.033"),
                    new Campo("24.2V", "Nome do Operador de Caixa", 25, 0, FormatoCampo.ALFANUMERICO, null, "", "GNR.034"),
                    new Campo("25.2V", "Indicador Programa Promocional", 1, 0, FormatoCampo.ALFANUMERICO, null, rs.getString("indicador_programa_promocional"), "GNR.035"),
                    new Campo("26.2V", "Meio de Captura", 2, 0, FormatoCampo.NUMERICO, null, "0", "GNR.036"),
                    new Campo("27.2V", "Número POS (Maquineta) Adquirente ", 15, 0, FormatoCampo.NUMERICO, null, rs.getString("numero_pos_adquirente"), "GNR.037"),
                    new Campo("28.2V", "Taxa de Comissão", 4, 2, FormatoCampo.NUMERICO, null, "0", "GNR.038"),
                    new Campo("29.2V", "Número NSU / CV ou DOC", 20, 0, FormatoCampo.ALFANUMERICO, null, rs.getString("NSU_CV_DOC"), "GNR.025"),
                    new Campo("30.2V", "Valor Comissão (Adquirente Adyen)", 10, 2, FormatoCampo.NUMERICO, null, "0", "GNR.025"),
                    new Campo("31.2V", "Uso Exclusivo Netunna", 44, 0, FormatoCampo.ALFANUMERICO, null, "", "GNR.004")
            ));
            if (rs.isLast()) {
                linhas.add(new Linha(TipoLinha.FOOTER_LOTE,
                        new Campo("01.3", "Código da Empresa", 3, 0, FormatoCampo.NUMERICO, null, "1", "GNR.001"),
                        new Campo("02.3", "Lote de Serviço", 4, 0, FormatoCampo.NUMERICO, "0001", "1", "GNR.002"),
                        new Campo("03.3", "Tipo do Registro", 1, 0, FormatoCampo.NUMERICO, "1", "1", "GNR.003"),
                        new Campo("04.3", "Tipo de Serviço", 2, 0, FormatoCampo.NUMERICO, "01", "01", "GNR.017"),
                        new Campo("05.3", "Uso Exclusivo Netunna", 7, 0, FormatoCampo.ALFANUMERICO, null, "0", "GNR.004"),
                        new Campo("06.3", "Tipo de Inscrição Empresa", 1, 0, FormatoCampo.NUMERICO, null, rs.getString("Tipo_Insc_Empresa"), "GNR.005"),
                        new Campo("07.3", "Número de Inscrição da Empresa", 14, 0, FormatoCampo.NUMERICO, null, rs.getString("numero_insc_empresa").replaceAll("\\.", "").replaceAll("/", "").replaceAll("-",""), "GNR.006"),
                        new Campo("08.3", "Código da Loja", 5, 0, FormatoCampo.ALFANUMERICO, null, rs.getString("codigo_loja"), "GNR.018"),
                        new Campo("09.3", "Número do Estabelecimento na Adquirente", 18, 0, FormatoCampo.ALFANUMERICO, null, rs.getString("numero_estab_adquirente"), "GNR.019"),
                        new Campo("10.3", "Quantidade de Vendas (Cupom Fiscal ou Pedidos)", 6, 0, FormatoCampo.NUMERICO, null, String.valueOf(totalRegistros), "GNR.039"),
                        new Campo("11.3", "Valor Bruto de Vendas no Lote", 14, 2, FormatoCampo.NUMERICO, null, valorBrutoTotal.setScale(2, RoundingMode.HALF_UP).toString(), "GNR.040"),
                        new Campo("12.3", "Valor Liquido de Vendas no Lote", 14, 2, FormatoCampo.NUMERICO, null, valorBrutoTotal.setScale(2, RoundingMode.HALF_UP).toString(), "GNR.041"),
                        new Campo("13.3", "Uso Exclusivo Netunna", 211, 0, FormatoCampo.ALFANUMERICO, null, null, "GNR.004")
                ));
            }            
        }
        return linhas;
    }

    public Linha getTrailerArquivo(int totalRegistros) throws SQLException {
        return new Linha(TipoLinha.FOOTER_ARQUIVO,
                        new Campo("01.9", "Código da Empresa", 3, 0, FormatoCampo.NUMERICO, null, "1", "GNR.001"),
                        new Campo("02.9", "Lote de Serviço", 4, 0, FormatoCampo.NUMERICO, "9999", "9999", "GNR.002"),
                        new Campo("03.9", "Tipo do Registro", 1, 0, FormatoCampo.NUMERICO, "9", "9", "GNR.003"),
                        new Campo("04.9", "Uso Exclusivo Netunna", 9, 0, FormatoCampo.ALFANUMERICO, null, null, "GNR.004"),
                        new Campo("06.9", "Quantidade de Lotes do Arquivo", 6, 0, FormatoCampo.NUMERICO, null, "1", "GNR.015"),
                        new Campo("07.9", "Quantidade de Registros do Arquivo", 6, 0, FormatoCampo.NUMERICO, null, String.valueOf(totalRegistros), "GNR.016"),
                        new Campo("08.9", "Uso Exclusivo Netunna", 271, 0, FormatoCampo.ALFANUMERICO, null, null, "GNR.004")
                );
    }

}
