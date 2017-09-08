package br.com.integracaoteiacard.modelo.entidade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Bonifácio
 */
public class Linha {
    
    private TipoLinha tipo;
    private List<Campo> campos;

    public TipoLinha getTipo() {
        return tipo;
    }

    public Linha(TipoLinha tipo, Campo... campos) {
        this.tipo = tipo;
        this.campos = new ArrayList<>();
        this.campos.addAll(Arrays.asList(campos));
    }

    public void setTipo(TipoLinha tipo) {
        this.tipo = tipo;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }

    @Override
    public String toString() {
        String retorno = "";
        for (Campo campo : this.campos) {
            retorno += campo;
        }
        return retorno + "\n";
    }    
    
}
