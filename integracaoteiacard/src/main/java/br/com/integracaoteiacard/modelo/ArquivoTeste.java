package br.com.integracaoteiacard.modelo;

/**
 *
 * @author José
 */
public class ArquivoTeste {
    
    private String nome;
    private Arquivo arquivo;

    public ArquivoTeste(String nome, Arquivo arquivo) {
        this.nome = nome;
        this.arquivo = arquivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }
    
    
}
