/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jaxrs.ejbs.beans;

import es.upsa.ssi.jarxrs.cdi.qualifiers.LocallyProduced;
import es.upsa.ssi.jarxrs.exceptions.TrabajoJaxRSException;
import es.upsa.ssi.jarxrs.json.forms.ItemManageStatPost;
import es.upsa.ssi.jarxrs.json.forms.ItemPost;
import es.upsa.ssi.jaxrs.ejbs.CategoriaDao;
import es.upsa.ssi.jaxrs.ejbs.ItemDao;
import es.upsa.ssi.jaxrs.ejbs.ItemStatDao;
import es.upsa.ssi.jaxrs.ejbs.Logica;
import es.upsa.ssi.jaxrs.ejbs.StatDao;
import es.upsa.ssi.jaxrs.pojos.Item;
import es.upsa.ssi.jaxrs.pojos.ItemCategoria;
import es.upsa.ssi.jaxrs.pojos.ItemStatType;
import es.upsa.ssi.jaxrs.pojos.StatWrapper;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author regigicas
 */
@Stateless
public class LogicaBean implements Logica
{
    @Inject
    @LocallyProduced
    private CategoriaDao categoriaDao;
    
    @Inject
    @LocallyProduced
    private ItemDao itemDao;
    
    @Inject
    @LocallyProduced
    private ItemStatDao itemStatDao;
    
    @Inject
    @LocallyProduced
    private StatDao statDao;

    @Override
    public List<ItemCategoria> getListadoCategoriasItem() throws TrabajoJaxRSException
    {
        return categoriaDao.getListadoCategoriasItem();
    }

    @Override
    public ItemCategoria getCategoriaItem(int codCategoria) throws TrabajoJaxRSException
    {
        return categoriaDao.getCategoriaItem(codCategoria);
    }

    @Override
    public ItemCategoria insertCategoriaItem(String descripcion) throws TrabajoJaxRSException
    {
        return categoriaDao.insertCategoriaItem(descripcion);
    }

    @Override
    public ItemCategoria updateCategoriaItem(int codCategoria, String descripcion) throws TrabajoJaxRSException
    {
        return categoriaDao.updateCategoriaItem(codCategoria, descripcion);
    }

    @Override
    public void deleteCategoriaItem(int codCategoria) throws TrabajoJaxRSException
    {
        categoriaDao.deleteCategoriaItem(codCategoria);
    }

    @Override
    public List<ItemStatType> getListadoItemStats() throws TrabajoJaxRSException
    {
        return statDao.getListadoItemStats();
    }

    @Override
    public ItemStatType getItemStat(int codStat) throws TrabajoJaxRSException
    {
        return statDao.getItemStat(codStat);
    }

    @Override
    public ItemStatType insertItemStat(String nombre) throws TrabajoJaxRSException
    {
        return statDao.insertItemStat(nombre);
    }

    @Override
    public ItemStatType updateItemStat(int codStat, String nombre) throws TrabajoJaxRSException
    {
        return statDao.updateItemStat(codStat, nombre);
    }

    @Override
    public void deleteItemStat(int codStat) throws TrabajoJaxRSException
    {
        statDao.deleteItemStat(codStat);
    }

    @Override
    public List<Item> getListadoItem() throws TrabajoJaxRSException
    {
        return itemDao.getListadoItem();
    }

    @Override
    public Item getItem(int codItem) throws TrabajoJaxRSException
    {
        return itemDao.getItem(codItem);
    }

    @Override
    public Item insertItem(ItemPost itemPost) throws TrabajoJaxRSException
    {
        return itemDao.insertItem(itemPost);
    }

    @Override
    public Item updateItem(int codItem, ItemPost itemPost) throws TrabajoJaxRSException
    {
        return itemDao.updateItem(codItem, itemPost);
    }

    @Override
    public void deleteItem(int codItem) throws TrabajoJaxRSException
    {
        itemDao.deleteItem(codItem);
    }

    @Override
    public List<StatWrapper> getStatsDeItem(int codItem) throws TrabajoJaxRSException
    {
        return itemStatDao.getStatsDeItem(codItem);
    }

    @Override
    public StatWrapper getStatDeItem(int codItem, int codStat) throws TrabajoJaxRSException
    {
        return itemStatDao.getStatDeItem(codItem, codStat);
    }

    @Override
    public StatWrapper insertStatDeItem(int codItem, ItemManageStatPost post) throws TrabajoJaxRSException
    {
        return itemStatDao.insertStatDeItem(codItem, post);
    }

    @Override
    public StatWrapper updateStatDeItem(int codItem, ItemManageStatPost post) throws TrabajoJaxRSException
    {
        return itemStatDao.updateStatDeItem(codItem, post);
    }

    @Override
    public void deleteStatDeItem(int codItem, int codStat) throws TrabajoJaxRSException
    {
        itemStatDao.deleteStatDeItem(codItem, codStat);
    }
}
