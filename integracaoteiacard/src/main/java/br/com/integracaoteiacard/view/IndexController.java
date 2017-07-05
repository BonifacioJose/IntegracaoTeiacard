package br.com.integracaoteiacard.view;

import br.com.integracaoteiacard.database.Dao;
import br.com.integracaoteiacard.database.DaoImpl;
import br.com.integracaoteiacard.modelo.Arquivo;
import br.com.integracaoteiacard.modelo.ArquivoTeste;
import br.com.integracaoteiacard.modelo.Campo;
import br.com.integracaoteiacard.modelo.FormatoCampo;
import br.com.integracaoteiacard.modelo.Linha;
import br.com.integracaoteiacard.modelo.TipoCampoDate;
import br.com.integracaoteiacard.modelo.TipoLinha;
import br.com.integracaoteiacard.util.ArquivoUtil;
import br.com.integracaoteiacard.util.DatabaseUtil;
import br.com.integracaoteiacard.util.DateUtil;
import br.com.integracaoteiacard.util.SystemUtil;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        int quantidadeRegistros = 20;
        int quantidadeLotes = 5;
        ArquivoTeste arquivoTeste = new ArquivoTeste("Arquivo Teste1",
                new Arquivo(
                        new Linha(TipoLinha.HEADER_ARQUIVO,
                                new Campo("01.0", "Código da Empresa", 3, 0, FormatoCampo.NUMERICO, null, "135", "GRN.001"),
                                new Campo("02.0", "Lote de Serviço", 4, 0, FormatoCampo.NUMERICO, "0000", "0000", "GRN.002"),
                                new Campo("03.0", "Tipo do Registro", 1, 0, FormatoCampo.NUMERICO, "0", "0", "GRN.003"),
                                new Campo("04.0", "Uso Exclusivo Netunna", 9, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.004"),
                                new Campo("05.0", "Tipo de Inscrição da Empresa", 1, 0, FormatoCampo.NUMERICO, null, "0", "GRN.005"),
                                new Campo("06.0", "Número de Inscrição da Empresa", 14, 0, FormatoCampo.NUMERICO, null, "01234567891234", "GRN.006"),
                                new Campo("07.0", "Nome da Empresa", 40, 0, FormatoCampo.ALFANUMERICO, null, "W2 COMERCIO E INDUSTRIA LTDA", "GRN.007"),
                                new Campo("08.0", "Nome da Adquirente", 40, 0, FormatoCampo.ALFANUMERICO, null, "GETNET", "GRN.008"),
                                new Campo("09.0", "Código de Remessa ou de Retorno ", 1, 0, FormatoCampo.NUMERICO, null, "1", "GRN.009"),
                                new Campo("10.0", "Data de Geração do Arquivo ", 8, null, new Date(), TipoCampoDate.DATE, "GRN.010"),
                                new Campo("11.0", "Hora de Geração do Arquivo", 6, null, new Date(), TipoCampoDate.TIMESTAMP, "GRN.011"),
                                new Campo("12.0", "Número Sequencial do Arquivo (NSA)", 7, 0, FormatoCampo.NUMERICO, null, "0002192", "GRN.012"),
                                new Campo("13.0", "Número da Versão do Leiaute do Arquivo", 5, 0, FormatoCampo.NUMERICO, null, "01.06", "GRN.013"),
                                new Campo("14.0", "Uso Reservado para a Empresa", 40, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.014"),
                                new Campo("15.0", "Uso Exclusivo Netunna", 121, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.004")
                        ),
                        new Linha(TipoLinha.HEADER_LOTE,
                                new Campo("01.1", "Código da Empresa", 3, 0, FormatoCampo.NUMERICO, null, "135", "GRN.001"),
                                new Campo("02.1", "Lote de Serviço", 4, 0, FormatoCampo.NUMERICO, "0001", null, "GRN.002"),
                                new Campo("03.1", "Tipo do Registro", 1, 0, FormatoCampo.NUMERICO, "1", null, "GRN.003"),
                                new Campo("04.1", "Tipo de Serviço", 2, 0, FormatoCampo.NUMERICO, "01", null, "GRN.017"),
                                new Campo("05.1", "Uso Exclusivo Netunna", 7, 0, FormatoCampo.ALFANUMERICO, null, "0", "GRN.004"),
                                new Campo("06.1", "Tipo de Inscrição Empresa", 1, 0, FormatoCampo.NUMERICO, null, "0", "GRN.005"),
                                new Campo("07.1", "Número de Inscrição da Empresa", 14, 0, FormatoCampo.NUMERICO, null, "01234567891234", "GRN.006"),
                                new Campo("08.1", "Código da Loja", 5, 0, FormatoCampo.ALFANUMERICO, null, "21", "GRN.018"),
                                new Campo("09.1", "Número do Estabelecimento na Adquirente", 18, 0, FormatoCampo.ALFANUMERICO, null, "1002718", "GRN.019"),
                                new Campo("10.1", "Uso Reservado para a Empresa", 40, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.014"),
                                new Campo("11.1", "Uso Exclusivo Netunna", 205, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.004")
                        ),
                        new Linha(TipoLinha.REGISTRO_DETALHE_VENDA,
                                new Campo("01.2V", "Código da Empresa", 3, 0, FormatoCampo.NUMERICO, null, "135", "GRN.001"),
                                new Campo("02.2V", "Lote de Serviço", 4, 0, FormatoCampo.NUMERICO, null, null, "GRN.002"),
                                new Campo("03.2V", "Tipo do Registro", 1, 0, FormatoCampo.NUMERICO, "2", null, "GRN.003"),
                                new Campo("04.2V", "Tipo de Serviço", 2, 0, FormatoCampo.NUMERICO, null, null, "GRN.017"),
                                new Campo("05.2V", "Número Sequencial do Registro no Lote", 5, 0, FormatoCampo.NUMERICO, null, "01234", "GRN.020"),
                                new Campo("06.2V", "Código do Segmento do Registro Detalhe", 1, 0, FormatoCampo.ALFANUMERICO, TipoLinha.REGISTRO_DETALHE_VENDA.getLetra(), null, "GRN.021"),
                                new Campo("07.2V", "Uso Exclusivo Netunna", 1, 0, FormatoCampo.ALFANUMERICO, null, "", "GRN.004"),
                                new Campo("08.2V", "Tipo de Inscrição Empresa", 1, 0, FormatoCampo.NUMERICO, null, "1", "GRN.005"),
                                new Campo("09.2V", "Número de Inscrição da Empresa", 14, 0, FormatoCampo.NUMERICO, null, "01234567890123", "GRN.006"),
                                new Campo("10.2V", "Código da Loja", 5, 0, FormatoCampo.ALFANUMERICO, null, "012345", "GRN.018"),
                                new Campo("11.2V", "Número do Estabelecimento na Adquirente", 18, 0, FormatoCampo.ALFANUMERICO, null, "012345678901234567", "GRN.019"),
                                new Campo("12.2V", "Número do Caixa/Checkout", 6, 0, FormatoCampo.ALFANUMERICO, null, "012345", "GRN.022"),
                                new Campo("13.2V", "Número do PDV (Maquineta) na Adquirente", 15, 0, FormatoCampo.NUMERICO, null, "01234567891234", "GRN.023"),
                                new Campo("14.2V", "Número do Cupom Fiscal / Número do Pedido", 10, 0, FormatoCampo.NUMERICO, null, "0123456789", "GRN.024"),
                                new Campo("15.2V", "Uso Exclusivo Netunna", 10, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.004"),
                                new Campo("16.2V", "Código da Autorização ", 10, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.026"),
                                new Campo("17.2V", "Data da Venda", 8, null, new Date(), TipoCampoDate.DATE, "GRN.027"),
                                new Campo("18.2V", "Hora da Venda", 6, null, new Date(), TipoCampoDate.TIMESTAMP, "GRN.028"),
                                new Campo("19.2V", "Valor Bruto da Venda", 10, 2, FormatoCampo.NUMERICO, null, null, "GRN.029"),
                                new Campo("20.2V", "Plano (Quantidade de Parcelas)", 2, 0, FormatoCampo.NUMERICO, null, null, "GRN.030"),
                                new Campo("21.2V", "Código da Bandeira", 3, 0, FormatoCampo.NUMERICO, null, null, "GRN.031"),
                                new Campo("22.2V", "Número do Cartão (Truncado) ", 19, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.032"),
                                new Campo("23.2V", "Nome do Proprietário do Cartão", 25, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.033"),
                                new Campo("24.2V", "Nome do Operador de Caixa", 25, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.034"),
                                new Campo("25.2V", "Indicador Programa Promocional", 1, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.035"),
                                new Campo("26.2V", "Meio de Captura", 2, 0, FormatoCampo.NUMERICO, null, null, "GRN.036"),
                                new Campo("27.2V", "Número POS (Maquineta) Adquirente ", 15, 0, FormatoCampo.NUMERICO, null, null, "GRN.037"),
                                new Campo("28.2V", "Taxa de Comissão", 4, 0, FormatoCampo.NUMERICO, null, null, "GRN.038"),
                                new Campo("29.2V", "Número NSU / CV ou DOC", 20, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.025"),
                                new Campo("30.2V", "Uso Exclusivo Netunna", 54, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.004")
                        ),
                        new Linha(TipoLinha.REGISTRO_DETALHE_PARCELA,
                                new Campo("01.2P", "Código da Empresa", 3, 0, FormatoCampo.NUMERICO, null, "135", "GRN.001"),
                                new Campo("02.2P", "Lote de Serviço", 4, 0, FormatoCampo.NUMERICO, null, null, "GRN.002"),
                                new Campo("03.2P", "Tipo do Registro", 1, 0, FormatoCampo.NUMERICO, "2", null, "GRN.003"),
                                new Campo("04.2P", "Tipo de Serviço", 2, 0, FormatoCampo.NUMERICO, null, null, "GRN.017"),
                                new Campo("05.2P", "Número Sequencial do Registro no Lote", 5, 0, FormatoCampo.NUMERICO, null, "01234", "GRN.020"),
                                new Campo("06.2P", "Código do Segmento do Registro Detalhe", 1, 0, FormatoCampo.ALFANUMERICO, TipoLinha.REGISTRO_DETALHE_PARCELA.getLetra(), null, "GRN.021"),
                                new Campo("07.2P", "Uso Exclusivo Netunna", 1, 0, FormatoCampo.ALFANUMERICO, null, "", "GRN.004"),
                                new Campo("08.2P", "Tipo de Inscrição Empresa", 1, 0, FormatoCampo.NUMERICO, null, "1", "GRN.005"),
                                new Campo("09.2P", "Número de Inscrição da Empresa", 14, 0, FormatoCampo.NUMERICO, null, "01234567890123", "GRN.006"),
                                new Campo("10.2P", "Código da Loja", 5, 0, FormatoCampo.ALFANUMERICO, null, "012345", "GRN.018"),
                                new Campo("11.2P", "Número do Estabelecimento na Adquirente", 18, 0, FormatoCampo.ALFANUMERICO, null, "012345678901234567", "GRN.019"),
                                new Campo("12.2P", "Número do Caixa/Checkout", 6, 0, FormatoCampo.ALFANUMERICO, null, "012345", "GRN.022"),
                                new Campo("13.2P", "Número do PDV (Maquineta) na Adquirente", 15, 0, FormatoCampo.NUMERICO, null, "01234567891234", "GRN.023"),
                                new Campo("14.2P", "Número do Cupom Fiscal / Número do Pedido", 10, 0, FormatoCampo.NUMERICO, null, "0123456789", "GRN.024"),
                                new Campo("15.2P", "Uso Exclusivo Netunna", 10, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.004"),
                                new Campo("16.2P", "Data Prevista de Pagamento da Parcela", 8, null, new Date(), TipoCampoDate.DATE, "GRN.024"),
                                new Campo("17.2P", "Uso Exclusivo Netunna", 16, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.004"),
                                new Campo("18.2P", "Valor Bruto da Parcela ", 10, 2, FormatoCampo.ALFANUMERICO, null, null, "GRN.043"),
                                new Campo("19.2P", "Número da Parcela", 2, 0, FormatoCampo.NUMERICO, null, null, "GRN.044"),
                                new Campo("20.2P", "Código da Bandeira", 3, 0, FormatoCampo.NUMERICO, null, null, "GRN.031"),
                                new Campo("21.2P", "Uso Exclusivo NETUNNA", 70, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.004"),
                                new Campo("22.2P", "Meio de Captura", 2, 0, FormatoCampo.NUMERICO, null, null, "GRN.036"),
                                new Campo("23.2P", "Número POS (Maquineta) Adquirente ", 15, 0, FormatoCampo.NUMERICO, null, null, "GRN.037"),
                                new Campo("24.2P", "Taxa de Comissão", 4, 2, FormatoCampo.NUMERICO, null, null, "GRN.038"),
                                new Campo("25.2P", "Valor Líquido da Parcela", 10, 0, FormatoCampo.NUMERICO, null, null, "GRN.045"),
                                new Campo("26.2P", "Número NSU / CV ou DOC", 20, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.025"),
                                new Campo("27.2P", "Uso Exclusivo Netunna", 44, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.004")
                        ),
                        new Linha(TipoLinha.REGISTRO_DETALHE_ESTORNO,
                                new Campo("01.2X", "Código da Empresa", 3, 0, FormatoCampo.NUMERICO, null, "135", "GRN.001"),
                                new Campo("02.2X", "Lote de Serviço", 4, 0, FormatoCampo.NUMERICO, null, null, "GRN.002"),
                                new Campo("03.2X", "Tipo do Registro", 1, 0, FormatoCampo.NUMERICO, "2", null, "GRN.003"),
                                new Campo("04.2X", "Tipo de Serviço", 2, 0, FormatoCampo.NUMERICO, null, null, "GRN.017"),
                                new Campo("05.2X", "Número Sequencial do Registro no Lote", 5, 0, FormatoCampo.NUMERICO, null, "01234", "GRN.020"),
                                new Campo("06.2X", "Código do Segmento do Registro Detalhe", 1, 0, FormatoCampo.ALFANUMERICO, TipoLinha.REGISTRO_DETALHE_ESTORNO.getLetra(), null, "GRN.021"),
                                new Campo("07.2X", "Uso Exclusivo Netunna", 1, 0, FormatoCampo.ALFANUMERICO, null, "", "GRN.004"),
                                new Campo("08.2X", "Tipo de Inscrição Empresa", 1, 0, FormatoCampo.NUMERICO, null, "1", "GRN.005"),
                                new Campo("09.2X", "Número de Inscrição da Empresa", 14, 0, FormatoCampo.NUMERICO, null, "01234567890123", "GRN.006"),
                                new Campo("10.2X", "Código da Loja", 5, 0, FormatoCampo.ALFANUMERICO, null, "012345", "GRN.018"),
                                new Campo("11.2X", "Número do Estabelecimento na Adquirente", 18, 0, FormatoCampo.ALFANUMERICO, null, "012345678901234567", "GRN.019"),
                                new Campo("12.2X", "Número do Caixa/Checkout", 6, 0, FormatoCampo.ALFANUMERICO, null, "012345", "GRN.022"),
                                new Campo("13.2X", "Número do PDV (Maquineta) na Adquirente", 15, 0, FormatoCampo.NUMERICO, null, "01234567891234", "GRN.023"),
                                new Campo("14.2X", "Número do Cupom Fiscal / Número do Pedido", 10, 0, FormatoCampo.NUMERICO, null, "0123456789", "GRN.024"),
                                new Campo("15.2X", "Uso Exclusivo Netunna", 10, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.004"),
                                new Campo("16.2X", "Valor Bruto da Venda", 10, 2, FormatoCampo.ALFANUMERICO, null, null, "GRN.029"),
                                new Campo("17.2X", "Data Solicitação do Estorno", 8, null, new Date(), TipoCampoDate.DATE, "GRN.055"),
                                new Campo("18.2X", "Data Efetivação do Estorno", 8, null, new Date(), TipoCampoDate.DATE, "GRN.056"),
                                new Campo("19.2X", "Valor do Estorno", 8, 2, FormatoCampo.NUMERICO, null, null, "GRN.057"),
                                new Campo("20.2X", "Motivo Estorno", 2, 0, FormatoCampo.NUMERICO, null, null, "GRN.058"),
                                new Campo("21.2X", "Usuário Atendente", 18, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.059"),
                                new Campo("22.2X", "Coordenador", 18, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.060"),
                                new Campo("23.2X", "Usuário Financeiro", 18, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.061"),
                                new Campo("24.2X", "Data Contato", 8, null, new Date(), TipoCampoDate.DATE, "GRN.062"),
                                new Campo("25.2X", "Hora Contato", 6, null, new Date(), TipoCampoDate.TIMESTAMP, "GRN.063"),
                                new Campo("26.2X", "TID", 20, 0, FormatoCampo.NUMERICO, null, null, "GRN.064"),
                                new Campo("27.2X", "Data da Venda", 8, null, new Date(), TipoCampoDate.DATE, "GRN.027"),
                                new Campo("28.2X", "Código da Bandeira ", 3, 0, FormatoCampo.NUMERICO, null, null, "GRN.031"),
                                new Campo("29.2X", "Data prevista para o Débito", 8, null, new Date(), TipoCampoDate.DATE, "GRN.065"),
                                new Campo("30.2X", "Número NSU / CV ou DOC", 20, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.025"),
                                new Campo("31.2X", "Uso Exclusivo Netunna", 41, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.004")
                        ),
                        new Linha(TipoLinha.REGISTRO_DETALHE_BAIXA_PARCELA,
                                new Campo("01.2B", "Código da Empresa", 3, 0, FormatoCampo.NUMERICO, null, "135", "GRN.001"),
                                new Campo("02.2B", "Lote de Serviço", 4, 0, FormatoCampo.NUMERICO, null, null, "GRN.002"),
                                new Campo("03.2B", "Tipo do Registro", 1, 0, FormatoCampo.NUMERICO, "2", null, "GRN.003"),
                                new Campo("04.2B", "Tipo de Serviço", 2, 0, FormatoCampo.NUMERICO, null, null, "GRN.017"),
                                new Campo("05.2B", "Número Sequencial do Registro no Lote", 5, 0, FormatoCampo.NUMERICO, null, "01234", "GRN.020"),
                                new Campo("06.2B", "Código do Segmento do Registro Detalhe", 1, 0, FormatoCampo.ALFANUMERICO, TipoLinha.REGISTRO_DETALHE_BAIXA_PARCELA.getLetra(), null, "GRN.021"),
                                new Campo("07.2B", "Uso Exclusivo Netunna", 1, 0, FormatoCampo.ALFANUMERICO, null, "", "GRN.004"),
                                new Campo("08.2B", "Tipo de Inscrição Empresa", 1, 0, FormatoCampo.NUMERICO, null, "1", "GRN.005"),
                                new Campo("09.2B", "Número de Inscrição da Empresa", 14, 0, FormatoCampo.NUMERICO, null, "01234567890123", "GRN.006"),
                                new Campo("10.2B", "Código da Loja", 5, 0, FormatoCampo.ALFANUMERICO, null, "012345", "GRN.018"),
                                new Campo("11.2B", "Número do Estabelecimento na Adquirente", 18, 0, FormatoCampo.ALFANUMERICO, null, "012345678901234567", "GRN.019"),
                                new Campo("12.2B", "Número do Caixa/Checkout", 6, 0, FormatoCampo.ALFANUMERICO, null, "012345", "GRN.022"),
                                new Campo("13.2B", "Número do PDV (Maquineta) na Adquirente", 15, 0, FormatoCampo.NUMERICO, null, "01234567891234", "GRN.023"),
                                new Campo("14.2B", "Número do Cupom Fiscal / Número do Pedido", 10, 0, FormatoCampo.NUMERICO, null, "0123456789", "GRN.024"),
                                new Campo("15.2B", "Uso Exclusivo Netunna", 10, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.025"),
                                new Campo("16.2B", "Data de Pagamento da Parcela no Banco", 8, null, new Date(), TipoCampoDate.DATE, "GRN.047"),
                                new Campo("17.2B", "Valor Liquido da Parcela Paga no Banco", 10, 2, FormatoCampo.NUMERICO, null, null, "GRN.046"),
                                new Campo("18.2B", "Valor Bruto da Parcela", 10, 2, FormatoCampo.NUMERICO, null, null, "GRN.043"),
                                new Campo("19.2B", "Taxa de Comissão", 4, 2, FormatoCampo.NUMERICO, null, null, "GRN.038"),
                                new Campo("20.2B", "Valor Liquido da Parcela", 10, 2, FormatoCampo.NUMERICO, null, null, "GRN.045"),
                                new Campo("21.2B", "Data Prevista de Pagamento Parcela", 8, null, new Date(), TipoCampoDate.DATE, "GRN.042"),
                                new Campo("22.2B", "Número da Parcela", 2, 0, FormatoCampo.NUMERICO, null, null, "GRN.044"),
                                new Campo("23.2B", "Código da Bandeira", 3, 0, FormatoCampo.NUMERICO, null, null, "GRN.031"),
                                new Campo("24.2B", "Código do Banco", 3, 0, FormatoCampo.NUMERICO, null, null, "GRN.048"),
                                new Campo("25.2B", "Agência Bancária", 6, 0, FormatoCampo.NUMERICO, null, null, "GRN.049"),
                                new Campo("26.2B", "DV Agência Bancária", 2, 0, FormatoCampo.NUMERICO, null, null, "GRN.050"),
                                new Campo("27.2B", "Conta Corrente Bancária", 7, 0, FormatoCampo.NUMERICO, null, null, "GRN.051"),
                                new Campo("28.2B", "DV Conta Corrente Bancária", 2, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.052"),
                                new Campo("29.2B", "Número do Extrato Bancário", 8, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.053"),
                                new Campo("30.2B", "Tipo da Baixa", 1, 0, FormatoCampo.NUMERICO, null, null, "GRN.054"),
                                new Campo("31.2B", "Número NSU / CV / DOC", 20, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.025"),
                                new Campo("32.2B", "Código da Autorização", 10, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.026"),
                                new Campo("33.2B", "Uso Exclusivo Netunna", 90, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.004")
                        ),
                        new Linha(TipoLinha.REGISTRO_DETALHE_OCORRENCIA,
                                new Campo("01.2O", "Código da Empresa", 3, 0, FormatoCampo.NUMERICO, null, "135", "GRN.001"),
                                new Campo("02.2O", "Lote de Serviço", 4, 0, FormatoCampo.NUMERICO, null, null, "GRN.002"),
                                new Campo("03.2O", "Tipo do Registro", 1, 0, FormatoCampo.NUMERICO, "2", null, "GRN.003"),
                                new Campo("04.2O", "Tipo de Serviço", 2, 0, FormatoCampo.NUMERICO, null, null, "GRN.017"),
                                new Campo("05.2O", "Número Sequencial do Registro no Lote", 5, 0, FormatoCampo.NUMERICO, null, "01234", "GRN.020"),
                                new Campo("06.2O", "Código do Segmento do Registro Detalhe", 1, 0, FormatoCampo.ALFANUMERICO, TipoLinha.REGISTRO_DETALHE_OCORRENCIA.getLetra(), null, "GRN.021"),
                                new Campo("07.2O", "Uso Exclusivo Netunna", 1, 0, FormatoCampo.ALFANUMERICO, null, "", "GRN.004"),
                                new Campo("08.2O", "Tipo de Inscrição Empresa", 1, 0, FormatoCampo.NUMERICO, null, "1", "GRN.005"),
                                new Campo("09.2O", "Número de Inscrição da Empresa", 14, 0, FormatoCampo.NUMERICO, null, "01234567890123", "GRN.006"),
                                new Campo("10.2O", "Código da Loja", 5, 0, FormatoCampo.ALFANUMERICO, null, "012345", "GRN.018"),
                                new Campo("11.2O", "Número do Estabelecimento na Adquirente", 18, 0, FormatoCampo.ALFANUMERICO, null, "012345678901234567", "GRN.019"),
                                new Campo("12.2O", "Número do Caixa/Checkout", 6, 0, FormatoCampo.ALFANUMERICO, null, "012345", "GRN.022"),
                                new Campo("13.2O", "Número do PDV (Maquineta) na Adquirente", 15, 0, FormatoCampo.NUMERICO, null, "01234567891234", "GRN.023"),
                                new Campo("14.2O", "Número do Cupom Fiscal / Número do Pedido", 10, 0, FormatoCampo.NUMERICO, null, "0123456789", "GRN.024"),
                                new Campo("15.2O", "Uso Exclusivo Netunna", 10, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.004"),
                                new Campo("16.2O", "Valor Bruto da Ocorrência", 10, 2, FormatoCampo.NUMERICO, null, null, "GRN.066"),
                                new Campo("17.2O", "Valor Líquido da Ocorrência", 10, 2, FormatoCampo.NUMERICO, null, null, "GRN.067"),
                                new Campo("18.2O", "Data da Ocorrência", 8, null, new Date(), TipoCampoDate.DATE, "GRN.068"),
                                new Campo("19.2O", "Tipo Ocorrência (Origem)", 2, 0, FormatoCampo.NUMERICO, null, null, "GRN.069"),
                                new Campo("20.2O", "Código Autorização Ocorrência", 10, 0, FormatoCampo.NUMERICO, null, null, "GRN.070"),
                                new Campo("21.2O", "Uso Exclusivo NETUNNA", 86, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.004"),
                                new Campo("22.2O", "Taxa Comissão Ocorrência", 4, 2, FormatoCampo.NUMERICO, null, null, "GRN.071"),
                                new Campo("23.2O", "Número Parcela Ocorrência", 2, 0, FormatoCampo.NUMERICO, null, null, "GRN.072"),
                                new Campo("24.2O", "Data Reagendamento ", 8, null, new Date(), TipoCampoDate.DATE, "GRN.073"),
                                new Campo("25.2O", "Número NSU / CV ou DOC", 20, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.025"),
                                new Campo("26.2O", "Uso Exclusivo Netunna", 44, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.004")
                        ),
                        new Linha(TipoLinha.FOOTER_LOTE,
                                new Campo("01.3", "Código da Empresa", 3, 0, FormatoCampo.NUMERICO, null, "135", "GRN.001"),
                                new Campo("02.3", "Lote de Serviço", 4, 0, FormatoCampo.NUMERICO, "0001", null, "GRN.002"),
                                new Campo("03.3", "Tipo do Registro", 1, 0, FormatoCampo.NUMERICO, "1", null, "GRN.003"),
                                new Campo("04.3", "Tipo de Serviço", 2, 0, FormatoCampo.NUMERICO, "01", null, "GRN.017"),
                                new Campo("05.3", "Uso Exclusivo Netunna", 7, 0, FormatoCampo.ALFANUMERICO, null, "0", "GRN.004"),
                                new Campo("06.3", "Tipo de Inscrição Empresa", 1, 0, FormatoCampo.NUMERICO, null, "0", "GRN.005"),
                                new Campo("07.3", "Número de Inscrição da Empresa", 14, 0, FormatoCampo.NUMERICO, null, "01234567891234", "GRN.006"),
                                new Campo("08.3", "Código da Loja", 5, 0, FormatoCampo.ALFANUMERICO, null, "21", "GRN.018"),
                                new Campo("09.3", "Número do Estabelecimento na Adquirente", 18, 0, FormatoCampo.ALFANUMERICO, null, "1002718", "GRN.019"),
                                new Campo("10.3", "Quantidade de Vendas (Cupom Fiscal ou Pedidos)", 6, 0, FormatoCampo.NUMERICO, null, "1", "GRN.039"),
                                new Campo("11.3", "Valor Bruto de Vendas no Lote", 14, 2, FormatoCampo.NUMERICO, null, "82349", "GRN.040"),
                                new Campo("12.3", "Valor Liquido de Vendas no Lote", 14, 2, FormatoCampo.NUMERICO, null, "82349", "GRN.041"),
                                new Campo("13.3", "Uso Exclusivo Netunna", 211, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.004")
                        ),
                        new Linha(TipoLinha.FOOTER_ARQUIVO,
                                new Campo("01.9", "Código da Empresa", 3, 0, FormatoCampo.NUMERICO, null, "135", "GRN.001"),
                                new Campo("02.9", "Lote de Serviço", 4, 0, FormatoCampo.NUMERICO, "9999", "9999", "GRN.002"),
                                new Campo("03.9", "Tipo do Registro", 1, 0, FormatoCampo.NUMERICO, "9", "9", "GRN.003"),
                                new Campo("04.9", "Uso Exclusivo Netunna", 9, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.004"),
                                new Campo("06.9", "Quantidade de Lotes do Arquivo", 6, 0, FormatoCampo.NUMERICO, null, String.valueOf(quantidadeLotes), "GRN.015"),
                                new Campo("07.9", "Quantidade de Registros do Arquivo", 6, 0, FormatoCampo.NUMERICO, null, String.valueOf(quantidadeRegistros), "GRN.016"),
                                new Campo("08.9", "Uso Exclusivo Netunna", 271, 0, FormatoCampo.ALFANUMERICO, null, null, "GRN.004")
                        )
                ));

        ArquivoUtil.salvarArquivo("", "\\" + DateUtil.getFormatoData(new Date()) + ".REM", new ByteArrayInputStream(arquivoTeste.getArquivo().toString().getBytes()));
        return arquivoTeste;
    }

}
