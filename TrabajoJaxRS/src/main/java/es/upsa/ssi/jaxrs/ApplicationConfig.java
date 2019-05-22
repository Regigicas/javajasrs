/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jaxrs;

import es.upsa.ssi.jaxrs.ejbs.CategoriaDao;
import es.upsa.ssi.jaxrs.ejbs.ItemDao;
import es.upsa.ssi.jaxrs.ejbs.ItemStatDao;
import es.upsa.ssi.jaxrs.ejbs.Logica;
import es.upsa.ssi.jaxrs.ejbs.StatDao;
import java.util.Set;
import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.sql.DataSource;
import javax.ws.rs.core.Application;

/**
 *
 * @author regigicas
 */
@Resources({
    @Resource(name = "jdbc/oracle", type = DataSource.class, lookup = "jdbc/ssiDataSource"),
    @Resource(name = "jms/cf", type = ConnectionFactory.class, lookup = "jms/upsaConnectionFactory"),
    @Resource(name = "jms/queue", type = Queue.class, lookup = "jms/upsaQueue"),
    @Resource(name = "jms/queue2", type = Queue.class, lookup = "jms/upsaQueue2")
})

@EJBs({
    @EJB(name = "ejb/daoCategoria", beanInterface = CategoriaDao.class, lookup = "java:module/OracleCategoriaDao!es.upsa.ssi.jaxrs.ejbs.CategoriaDao"),
    @EJB(name = "ejb/daoItem", beanInterface = ItemDao.class, lookup = "java:module/OracleItemDao!es.upsa.ssi.jaxrs.ejbs.ItemDao"),
    @EJB(name = "ejb/daoItemStat", beanInterface = ItemStatDao.class, lookup = "java:module/OracleItemStatDao!es.upsa.ssi.jaxrs.ejbs.ItemStatDao"),
    @EJB(name = "ejb/daoStat", beanInterface = StatDao.class, lookup = "java:module/OracleStatDao!es.upsa.ssi.jaxrs.ejbs.StatDao"),
    @EJB(name = "ejb/logica", beanInterface = Logica.class, lookup = "java:module/LogicaBean!es.upsa.ssi.jaxrs.ejbs.Logica")
})

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application
{

    @Override
    public Set<Class<?>> getClasses()
    {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources)
    {
        resources.add(es.upsa.ssi.jarxrs.resources.CategoriaResource.class);
        resources.add(es.upsa.ssi.jarxrs.resources.ItemsResource.class);
        resources.add(es.upsa.ssi.jarxrs.resources.StatsResource.class);
    }
    
}
