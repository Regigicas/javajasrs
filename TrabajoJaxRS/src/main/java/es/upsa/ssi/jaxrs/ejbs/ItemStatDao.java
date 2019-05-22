/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jaxrs.ejbs;

import es.upsa.ssi.jarxrs.exceptions.TrabajoJaxRSException;
import es.upsa.ssi.jarxrs.json.forms.ItemManageStatPost;
import es.upsa.ssi.jaxrs.pojos.StatWrapper;
import java.util.List;

/**
 *
 * @author regigicas
 */
public interface ItemStatDao
{
    List<StatWrapper> getStatsDeItem(int codItem) throws TrabajoJaxRSException;
    StatWrapper getStatDeItem(int codItem, int codStat) throws TrabajoJaxRSException;
    StatWrapper insertStatDeItem(int codItem, ItemManageStatPost post) throws TrabajoJaxRSException;
    StatWrapper updateStatDeItem(int codItem, ItemManageStatPost post) throws TrabajoJaxRSException;
    void deleteStatDeItem(int codItem, int codStat) throws TrabajoJaxRSException;
}
