package br.com.integracaoteiacard.database;

/**
 *
 * @author Bonifácio
 */
public interface DatabaseEntity {
    
    public void setId(Long id);
    public Long getId();
    public boolean isNovo();
}
