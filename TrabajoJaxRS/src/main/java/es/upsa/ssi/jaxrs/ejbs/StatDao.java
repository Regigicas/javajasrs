/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jaxrs.ejbs;

import es.upsa.ssi.jarxrs.exceptions.TrabajoJaxRSException;
import es.upsa.ssi.jaxrs.pojos.ItemStatType;
import java.util.List;

/**
 *
 * @author regigicas
 */
public interface StatDao
{
    List<ItemStatType> getListadoItemStats() throws TrabajoJaxRSException;
    ItemStatType getItemStat(int codStat) throws TrabajoJaxRSException;
    ItemStatType insertItemStat(String nombre) throws TrabajoJaxRSException;
    ItemStatType updateItemStat(int codStat, String nombre) throws TrabajoJaxRSException;
    void deleteItemStat(int codStat) throws TrabajoJaxRSException;
}
