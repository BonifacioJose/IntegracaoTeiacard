package br.com.integracaoteiacard.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bonifácio
 */
public class Arquivo {
    
    private List<Campo> campos;

    public Arquivo(Campo... campos) {
        this.campos = new ArrayList<>();
        for (Campo campo : campos) {
            this.campos.add(campo);
        }
    }    
    
    @Override
    public String toString() {
        String retorno = "";
        for (Campo campo : this.campos) {
            retorno += campo.toString();
        }
        return retorno;
    }
    
}
