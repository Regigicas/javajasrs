/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrsclient;

import java.util.Set;
import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.ws.rs.core.Application;

/**
 *
 * @author regigicas
 */
@Resources({
    @Resource(name = "jms/cf", type = ConnectionFactory.class, lookup = "jms/upsaConnectionFactory"),
    @Resource(name = "jms/queue", type = Queue.class, lookup = "jms/upsaQueue"),
    @Resource(name = "jms/queue2", type = Queue.class, lookup = "jms/upsaQueue2")
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
        resources.add(es.upsa.ssi.jarxrsclient.resources.CategoriasResource.class);
        resources.add(es.upsa.ssi.jarxrsclient.resources.ItemsResource.class);
        resources.add(es.upsa.ssi.jarxrsclient.resources.StatsResource.class);
    }
    
}
