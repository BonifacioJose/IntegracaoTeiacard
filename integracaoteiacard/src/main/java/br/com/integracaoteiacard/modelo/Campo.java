package br.com.integracaoteiacard.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Bonifácio
 */
public class Campo {

    private String id;
    private String campo;
    private int tamanho;
    private int numeroDecimais;
    private FormatoCampo formato;
    private String valorDefault;
    private String valor;
    private String descricao;
    private TipoCampoDate tipoCampoDate;

    public Campo() {
    }

    public Campo(String id, String campo, int tamanho, int numeroDecimais, FormatoCampo formatoCampo, String valorDefault, String valor, String descricao) {
        this.id = id;
        this.campo = campo;
        this.tamanho = tamanho;
        this.numeroDecimais = numeroDecimais;
        this.formato = formatoCampo;
        this.valorDefault = valorDefault;
        this.valor = valor;
        if (FormatoCampo.ALFANUMERICO.equals(this.formato)) {
            if (this.valorDefault != null && this.valorDefault.length() > 40) {
                this.valorDefault = this.valorDefault.substring(0, 40);
            }
            if (this.valor != null && this.valor.length() > 40) {
                this.valor = this.valor.substring(0, 40);                
            }
        }
        this.descricao = descricao;
    }

    
    public Campo(String id, String campo, int tamanho, Date valorDefault, Date valor, TipoCampoDate tipoCampoDate, String descricao) {
        this.id = id;
        this.campo = campo;
        this.formato = FormatoCampo.ALFANUMERICO;
        this.tamanho = tamanho;
        this.tipoCampoDate = tipoCampoDate;
        this.valorDefault = getFormatoData(valorDefault);
        this.valor = getFormatoData(valor);
        this.descricao = descricao;
    }
    
    
    private String getFormatoData(Date data) {
        if (data != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);
            switch (tipoCampoDate) {
                case DATE:
                    int dia = calendar.get(Calendar.DAY_OF_MONTH);
                    int mes = calendar.get(Calendar.MONTH) + 1;
            return String.valueOf(dia < 10 ? "0".concat(String.valueOf(dia)) : dia)
                    + String.valueOf(mes < 10 ? "0".concat(String.valueOf(mes)) : mes)
                    + String.valueOf(calendar.get(Calendar.YEAR));                
                case TIMESTAMP:    
                    int hora = calendar.get(Calendar.HOUR_OF_DAY);
                    int minuto = calendar.get(Calendar.MINUTE);                
            return String.valueOf(hora < 10 ? "0".concat(String.valueOf(hora)) : hora)
                    + String.valueOf(minuto < 10 ? "0".concat(String.valueOf(minuto)) : minuto)
                    + String.valueOf(calendar.get(Calendar.SECOND));
            }
        }
        return "";
    }
    
    private String getValorNormalizado(String valor) {
        String novoValor = "";
        if (valor == null) {
            valor = "";
        }
        switch (formato) {
            case ALFANUMERICO:
                for (int i = 0; i < (tamanho - valor.length()); i++) {
                    novoValor += " ";
                }
                valor = valor + novoValor;
                break;
            case NUMERICO:
                for (int i = 0; i < (tamanho - valor.length()); i++) {
                    novoValor += "0";
                }
                valor = novoValor + valor;
                break;
        }
        return valor;
    }

    public void setValorDefault(String valorDefault) {
        this.valorDefault = valorDefault;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getValorDefault() {
        return getValorNormalizado(valorDefault);
    }

    public String getValor() {
        return valor == null ? getValorDefault() : getValorNormalizado(valor);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getNumeroDecimais() {
        return numeroDecimais;
    }

    public void setNumeroDecimais(int numeroDecimais) {
        this.numeroDecimais = numeroDecimais;
    }

    public FormatoCampo getFormato() {
        return formato;
    }

    public void setFormato(FormatoCampo formato) {
        this.formato = formato;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return getValor();
    }

}
