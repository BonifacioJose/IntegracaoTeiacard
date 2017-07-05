package br.com.integracaoteiacard.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bonifácio
 */
public class Arquivo {

    private List<Linha> linhas;

    public Arquivo(Linha... linhas) {
        this.linhas = new ArrayList<>();
        for (Linha linha : linhas) {
            this.linhas.add(linha);
        }
    }

    @Override
    public String toString() {
        String retorno = "";
        for (Linha linha : this.linhas) {
            retorno += linha.toString();
        }
        return retorno;
    }

}
