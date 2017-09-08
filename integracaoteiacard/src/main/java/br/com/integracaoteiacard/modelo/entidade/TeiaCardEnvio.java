package br.com.integracaoteiacard.modelo.entidade;

import br.com.integracaoteiacard.database.DatabaseEntity;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Bonifácio
 */
public class TeiaCardEnvio implements DatabaseEntity {
    
    private Long id;
    private Date dataEnvio;

    public TeiaCardEnvio() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
    
    @Override
    public boolean isNovo() {
        return this.id == null;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TeiaCardEnvio other = (TeiaCardEnvio) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
