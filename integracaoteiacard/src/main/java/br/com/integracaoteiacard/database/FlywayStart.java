package br.com.integracaoteiacard.database;

import br.com.integracaoteiacard.util.SystemUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.flywaydb.core.Flyway;

/**
 *
 * @author Bonifácio
 */
@ApplicationScoped
@ManagedBean(eager = true)
public class FlywayStart implements Serializable {

    @PostConstruct
    public void init() {
        System.out.println("teste1");
        // Create the Flyway instance
        Flyway flyway = new Flyway();
        // Point it to the database
        System.out.println("jdbc:postgresql://"
                + SystemUtil.getConfigurationProperty("ip") + ":"
                + SystemUtil.getConfigurationProperty("porta")
                + "/" + SystemUtil.getConfigurationProperty("banco"));
        flyway.setDataSource("jdbc:postgresql://"
                + SystemUtil.getConfigurationProperty("ip") + ":"
                + SystemUtil.getConfigurationProperty("porta")
                + "/" + SystemUtil.getConfigurationProperty("banco"),
                SystemUtil.getConfigurationProperty("usuario"), SystemUtil.getConfigurationProperty("senha"));

        // Start the migration
        flyway.migrate();
    }
}
