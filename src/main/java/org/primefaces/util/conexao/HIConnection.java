package org.primefaces.util.conexao;

import java.util.HashMap;
import java.util.Map;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.spi.PersistenceUnitTransactionType;
import jakarta.servlet.http.HttpServletRequest;

@ApplicationScoped
public class HIConnection {


    private Map<Object, Object> listFactory;

    public HIConnection() {
        listFactory = new HashMap<>();
    }

    @Produces
    @RequestScoped
    @Default
    public EntityManager createEntityManager() {
        try {

            EntityManager session;

            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();

            String dataBase = (String) request.getSession().getAttribute("PUDATABASE");

            EntityManagerFactory factory = (EntityManagerFactory) listFactory.get(dataBase);

            if (factory == null || !factory.isOpen()) {

                String dataSource = (String) request.getSession().getAttribute("PUDATASOURCE");
                String persistencia = (String) request.getSession().getAttribute("PUPERSISTENCIA");
                String url = (String) request.getSession().getAttribute("PUURL");
                String usuario = (String) request.getSession().getAttribute("PUUSUARIO");
                String senha = (String) request.getSession().getAttribute("PUSENHA");

                Map<String, String> configOverrides = new HashMap<>();

                configOverrides.put("jakarta.persistence.transactionType", PersistenceUnitTransactionType.RESOURCE_LOCAL.name());        
                configOverrides.put("jakarta.persistence.jdbc.url", url.trim());
                configOverrides.put("jakarta.persistence.jdbc.user", usuario.trim());
                configOverrides.put("jakarta.persistence.jdbc.password", senha.trim());
                configOverrides.put("hibernate.hbm2ddl.auto", "update");

                factory = Persistence.createEntityManagerFactory(persistencia, configOverrides);
                EntityManager ent = factory.createEntityManager();
                ent.close();
                factory.close();

                configOverrides = new HashMap<>();
                configOverrides.put("jakarta.persistence.transactionType", PersistenceUnitTransactionType.JTA.name());
                configOverrides.put("jakarta.persistence.jtaDataSource", dataSource);
                factory = Persistence.createEntityManagerFactory(persistencia, configOverrides);

                listFactory.put(dataBase, factory);

            }

            session = factory.createEntityManager();

            return session;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeEntityManager(@Disposes @Default EntityManager session) {
        try {
            if (session.isOpen()) {
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
