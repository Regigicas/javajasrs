/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrsclient.beans;

import es.upsa.ssi.jarxrsclient.exceptions.TrabajoJaxRSClientException;
import es.upsa.ssi.jarxrsclient.json.forms.ItemManageStatPost;
import es.upsa.ssi.jarxrsclient.json.forms.ItemPost;
import es.upsa.ssi.jarxrsclient.pojos.Item;
import es.upsa.ssi.jarxrsclient.pojos.ItemCategoria;
import es.upsa.ssi.jarxrsclient.pojos.ItemStatType;
import es.upsa.ssi.jarxrsclient.pojos.StatWrapper;
import java.util.List;

/**
 *
 * @author regigicas
 */
public interface Dao
{
    List<ItemCategoria> getListadoCategoriasItem() throws TrabajoJaxRSClientException;
    ItemCategoria getCategoriaItem(int codCategoria) throws TrabajoJaxRSClientException;
    ItemCategoria insertCategoriaItem(String descripcion) throws TrabajoJaxRSClientException;
    void updateCategoriaItem(int codCategoria, String descripcion) throws TrabajoJaxRSClientException;
    void deleteCategoriaItem(int codCategoria) throws TrabajoJaxRSClientException;
    
    List<ItemStatType> getListadoItemStats() throws TrabajoJaxRSClientException;
    ItemStatType getItemStat(int codStat) throws TrabajoJaxRSClientException;
    ItemStatType insertItemStat(String nombre) throws TrabajoJaxRSClientException;
    void updateItemStat(int codStat, String nombre) throws TrabajoJaxRSClientException;
    void deleteItemStat(int codStat) throws TrabajoJaxRSClientException;
    
    List<Item> getListadoItem() throws TrabajoJaxRSClientException;
    Item getItem(int codItem) throws TrabajoJaxRSClientException;
    Item insertItem(ItemPost itemPost) throws TrabajoJaxRSClientException;
    void updateItem(int codItem, ItemPost itemPost) throws TrabajoJaxRSClientException;
    void deleteItem(int codItem) throws TrabajoJaxRSClientException;
    
    StatWrapper getStatDeItem(int codItem, int codStat) throws TrabajoJaxRSClientException;
    void insertStatDeItem(int codItem, ItemManageStatPost post) throws TrabajoJaxRSClientException;
    void updateStatDeItem(int codItem, ItemManageStatPost post) throws TrabajoJaxRSClientException;
    void deleteStatDeItem(int codItem, int codStat) throws TrabajoJaxRSClientException;
}
