package br.com.integracaoteiacard.modelo.entidade;

/**
 *
 * @author Bonifácio
 */
public enum TipoLinha {
    
    HEADER_ARQUIVO(0),
    HEADER_LOTE(1),
    REGISTRO_DETALHE_VENDA(2, "V"),
    REGISTRO_DETALHE_PARCELA(2, "P"),
    REGISTRO_DETALHE_BAIXA_PARCELA(2, "B"),
    REGISTRO_DETALHE_ESTORNO(2, "X"),
    REGISTRO_DETALHE_OCORRENCIA(2, "O"),
    FOOTER_LOTE(3),
    FOOTER_ARQUIVO(9);
    
    private final int tipo;
    private final String letra;

    private TipoLinha(int tipo) {
        this.tipo = tipo;
        this.letra = "";
    }

    private TipoLinha(int tipo, String letra) {
        this.tipo = tipo;
        this.letra = letra;
    }

    public int getTipo() {
        return tipo;
    }

    public String getLetra() {
        return letra;
    }

}
