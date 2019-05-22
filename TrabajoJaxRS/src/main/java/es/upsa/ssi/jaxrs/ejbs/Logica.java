/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jaxrs.ejbs;

import es.upsa.ssi.jarxrs.exceptions.TrabajoJaxRSException;
import es.upsa.ssi.jarxrs.json.forms.ItemManageStatPost;
import es.upsa.ssi.jarxrs.json.forms.ItemPost;
import es.upsa.ssi.jaxrs.pojos.Item;
import es.upsa.ssi.jaxrs.pojos.ItemCategoria;
import es.upsa.ssi.jaxrs.pojos.ItemStatType;
import es.upsa.ssi.jaxrs.pojos.StatWrapper;
import java.util.List;

/**
 *
 * @author regigicas
 */
public interface Logica
{
    List<ItemCategoria> getListadoCategoriasItem() throws TrabajoJaxRSException;
    ItemCategoria getCategoriaItem(int codCategoria) throws TrabajoJaxRSException;
    ItemCategoria insertCategoriaItem(String descripcion) throws TrabajoJaxRSException;
    ItemCategoria updateCategoriaItem(int codCategoria, String descripcion) throws TrabajoJaxRSException;
    void deleteCategoriaItem(int codCategoria) throws TrabajoJaxRSException;
    
    List<ItemStatType> getListadoItemStats() throws TrabajoJaxRSException;
    ItemStatType getItemStat(int codStat) throws TrabajoJaxRSException;
    ItemStatType insertItemStat(String nombre) throws TrabajoJaxRSException;
    ItemStatType updateItemStat(int codStat, String nombre) throws TrabajoJaxRSException;
    void deleteItemStat(int codStat) throws TrabajoJaxRSException;
    
    List<Item> getListadoItem() throws TrabajoJaxRSException;
    Item getItem(int codItem) throws TrabajoJaxRSException;
    Item insertItem(ItemPost itemPost) throws TrabajoJaxRSException;
    Item updateItem(int codItem, ItemPost itemPost) throws TrabajoJaxRSException;
    void deleteItem(int codItem) throws TrabajoJaxRSException;
    
    List<StatWrapper> getStatsDeItem(int codItem) throws TrabajoJaxRSException;
    StatWrapper getStatDeItem(int codItem, int codStat) throws TrabajoJaxRSException;
    StatWrapper insertStatDeItem(int codItem, ItemManageStatPost post) throws TrabajoJaxRSException;
    StatWrapper updateStatDeItem(int codItem, ItemManageStatPost post) throws TrabajoJaxRSException;
    void deleteStatDeItem(int codItem, int codStat) throws TrabajoJaxRSException;
}
