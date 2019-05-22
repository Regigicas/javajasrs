/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrs.cdi.producer;

import es.upsa.ssi.jarxrs.cdi.qualifiers.LocallyProduced;
import es.upsa.ssi.jaxrs.ejbs.CategoriaDao;
import es.upsa.ssi.jaxrs.ejbs.ItemDao;
import es.upsa.ssi.jaxrs.ejbs.ItemStatDao;
import es.upsa.ssi.jaxrs.ejbs.Logica;
import es.upsa.ssi.jaxrs.ejbs.StatDao;
import javax.ejb.EJB;
import javax.enterprise.inject.Produces;

/**
 *
 * @author regigicas
 */
public class EJBProducer
{
    @Produces
    @LocallyProduced
    @EJB(name = "ejb/daoCategoria")
    private CategoriaDao categoriaDao;
    
    @Produces
    @LocallyProduced
    @EJB(name = "ejb/daoItem")
    private ItemDao itemDao;
    
    @Produces
    @LocallyProduced
    @EJB(name = "ejb/daoItemStat")
    private ItemStatDao itemStatDao;
    
    @Produces
    @LocallyProduced
    @EJB(name = "ejb/daoStat")
    private StatDao statDao;
    
    @Produces
    @LocallyProduced
    @EJB(name = "ejb/logica")
    private Logica logica;
}
