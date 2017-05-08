#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${parentArtifactId}module.spring;

import org.opensingular.server.commons.spring.SingularDefaultPersistenceConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(
        proxyTargetClass = true
)
public class PersistenceConfiguration extends SingularDefaultPersistenceConfiguration {

    @Value("classpath:db/dml/insert-${parentArtifactId}-module.sql")
    private Resource ${parentArtifactId}Module;


    @Override
    protected String getUrlConnection() {
        return "jdbc:h2:file:./singularserverdb;AUTO_SERVER=TRUE;mode=ORACLE;CACHE_SIZE=4096;EARLY_FILTER=1;LOCK_TIMEOUT=15000;";
    }

    @Override
    protected ResourceDatabasePopulator databasePopulator() {
        ResourceDatabasePopulator populator = super.databasePopulator();
        populator.addScript(${parentArtifactId}Module);
        return populator;
    }

}