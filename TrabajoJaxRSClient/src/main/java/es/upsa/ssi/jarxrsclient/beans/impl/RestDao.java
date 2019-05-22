/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrsclient.beans.impl;

import es.upsa.ssi.jarxrsclient.beans.Dao;
import es.upsa.ssi.jarxrsclient.exceptions.TrabajoJaxRSClientException;
import es.upsa.ssi.jarxrsclient.json.forms.ItemCategoriaPost;
import es.upsa.ssi.jarxrsclient.json.forms.ItemManageStatPost;
import es.upsa.ssi.jarxrsclient.json.forms.ItemPost;
import es.upsa.ssi.jarxrsclient.json.forms.ItemStatPost;
import es.upsa.ssi.jarxrsclient.pojos.Item;
import es.upsa.ssi.jarxrsclient.pojos.ItemCategoria;
import es.upsa.ssi.jarxrsclient.pojos.ItemStatType;
import es.upsa.ssi.jarxrsclient.pojos.StatWrapper;
import java.net.URI;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author regigicas
 */
public class RestDao implements Dao
{
    private static final String URL_GET_CATEGORIAS_ENDPOINT = "http://localhost:11292/trabajojaxrs/api/categorias";
    private static final String URL_GET_CATEGORIA_ENDPOINT = "http://localhost:11292/trabajojaxrs/api/categorias/{codCategoria}";
    
    private static final String URL_GET_STATS_ENDPOINT = "http://localhost:11292/trabajojaxrs/api/stats";
    private static final String URL_GET_STAT_ENDPOINT = "http://localhost:11292/trabajojaxrs/api/stats/{codStat}";
    
    private static final String URL_GET_ITEMS_ENDPOINT = "http://localhost:11292/trabajojaxrs/api/items";
    private static final String URL_GET_ITEM_ENDPOINT = "http://localhost:11292/trabajojaxrs/api/items/{codItem}";
    
    private static final String URL_GET_ITEM_STATS_ENDPOINT = "http://localhost:11292/trabajojaxrs/api/items/{codItem}/stats";
    private static final String URL_GET_ITEM_STAT_ENDPOINT = "http://localhost:11292/trabajojaxrs/api/items/{codItem}/stats/{codStat}";
    
    private Client client = ClientBuilder.newClient();
    
    @Override
    public List<ItemCategoria> getListadoCategoriasItem() throws TrabajoJaxRSClientException
    {
        Response response = client.target(URL_GET_CATEGORIAS_ENDPOINT)
                                    .request()
                                    .accept(MediaType.APPLICATION_JSON)
                                    .get();
        
        List<ItemCategoria> categorias = response.readEntity(new GenericType<List<ItemCategoria>>() {});
        return categorias;
    }

    @Override
    public ItemCategoria getCategoriaItem(int codCategoria) throws TrabajoJaxRSClientException
    {
        Response response = client.target(UriBuilder.fromUri(URL_GET_CATEGORIA_ENDPOINT)
                                    .build(codCategoria))
                                    .request()
                                    .accept(MediaType.APPLICATION_JSON)
                                    .get();
        
        ItemCategoria categoria = response.readEntity(ItemCategoria.class);
        return categoria;
    }

    @Override
    public ItemCategoria insertCategoriaItem(String descripcion) throws TrabajoJaxRSClientException
    {
        ItemCategoriaPost post = new ItemCategoriaPost();
        post.setDescripcion(descripcion);
        
        Response response = client.target(URL_GET_CATEGORIAS_ENDPOINT)
                                        .request()
                                        .post(Entity.json(post));
        
        switch (response.getStatus())
        {
            case 201:
                URI uriLocation = response.getLocation();
                return queryCategoria(uriLocation);
            default:
                throw new TrabajoJaxRSClientException();
        }
    }

    @Override
    public void updateCategoriaItem(int codCategoria, String descripcion) throws TrabajoJaxRSClientException
    {
        ItemCategoriaPost post = new ItemCategoriaPost();
        post.setDescripcion(descripcion);
        
        Response response = client.target(UriBuilder.fromUri(URL_GET_CATEGORIA_ENDPOINT)
                                        .build(codCategoria))
                                        .request()
                                        .put(Entity.json(post));
        
        switch (response.getStatus())
        {
            case 200:
                break;
            default:
                throw new TrabajoJaxRSClientException();
        }
    }

    @Override
    public void deleteCategoriaItem(int codCategoria) throws TrabajoJaxRSClientException
    {
        Response response = client.target(UriBuilder.fromUri(URL_GET_CATEGORIA_ENDPOINT)
                                    .build(codCategoria))
                                    .request()
                                    .delete();
        
        switch (response.getStatus())
        {
            case 204:
                break;
            default:
                throw new TrabajoJaxRSClientException();
        }
    }

    private ItemCategoria queryCategoria(URI uriLocation)
    {
        return client.target(uriLocation)
                        .request()
                        .accept(MediaType.APPLICATION_JSON)
                        .get()
                        .readEntity(ItemCategoria.class);
    }

    @Override
    public List<ItemStatType> getListadoItemStats() throws TrabajoJaxRSClientException
    {
        Response response = client.target(URL_GET_STATS_ENDPOINT)
                                .request()
                                .accept(MediaType.APPLICATION_JSON)
                                .get();
        
        List<ItemStatType> stats = response.readEntity(new GenericType<List<ItemStatType>> () {});
        return stats;
    }

    @Override
    public ItemStatType getItemStat(int codStat) throws TrabajoJaxRSClientException
    {
        Response response = client.target(UriBuilder.fromUri(URL_GET_STAT_ENDPOINT)
                                    .build(codStat))
                                    .request()
                                    .accept(MediaType.APPLICATION_JSON)
                                    .get();
        
        ItemStatType stat = response.readEntity(ItemStatType.class);
        return stat;
    }

    @Override
    public ItemStatType insertItemStat(String nombre) throws TrabajoJaxRSClientException
    {
        ItemStatPost post = new ItemStatPost();
        post.setNombre(nombre);
        
        Response response = client.target(URL_GET_STATS_ENDPOINT)
                                        .request()
                                        .post(Entity.json(post));
        
        switch (response.getStatus())
        {
            case 201:
                URI uriLocation = response.getLocation();
                return queryStat(uriLocation);
            default:
                throw new TrabajoJaxRSClientException();
        }
    }

    @Override
    public void updateItemStat(int codStat, String nombre) throws TrabajoJaxRSClientException
    {
        ItemStatPost post = new ItemStatPost();
        post.setNombre(nombre);
        
        Response response = client.target(UriBuilder.fromUri(URL_GET_STAT_ENDPOINT)
                                        .build(codStat))
                                        .request()
                                        .put(Entity.json(post));
        
        switch (response.getStatus())
        {
            case 200:
                break;
            default:
                throw new TrabajoJaxRSClientException();
        }
    }

    @Override
    public void deleteItemStat(int codStat) throws TrabajoJaxRSClientException
    {
        Response response = client.target(UriBuilder.fromUri(URL_GET_STAT_ENDPOINT)
                                    .build(codStat))
                                    .request()
                                    .delete();
        
        switch (response.getStatus())
        {
            case 204:
                break;
            default:
                throw new TrabajoJaxRSClientException();
        }
    }
    
    private ItemStatType queryStat(URI uriLocation)
    {
        return client.target(uriLocation)
                        .request()
                        .accept(MediaType.APPLICATION_JSON)
                        .get()
                        .readEntity(ItemStatType.class);
    }

    @Override
    public List<Item> getListadoItem() throws TrabajoJaxRSClientException
    {
        Response response = client.target(URL_GET_ITEMS_ENDPOINT)
                                    .request()
                                    .accept(MediaType.APPLICATION_JSON)
                                    .get();
        
        List<Item> item = response.readEntity(new GenericType<List<Item>>() {});
        return item;
    }

    @Override
    public Item getItem(int codItem) throws TrabajoJaxRSClientException
    {
        Response response = client.target(UriBuilder.fromUri(URL_GET_ITEM_ENDPOINT)
                                    .build(codItem))
                                    .request()
                                    .accept(MediaType.APPLICATION_JSON)
                                    .get();
        
        Item item = response.readEntity(Item.class);
        return item;
    }

    @Override
    public Item insertItem(ItemPost itemPost) throws TrabajoJaxRSClientException
    {
        Response response = client.target(URL_GET_ITEMS_ENDPOINT)
                                        .request()
                                        .post(Entity.json(itemPost));
        
        switch (response.getStatus())
        {
            case 201:
                URI uriLocation = response.getLocation();
                return queryItem(uriLocation);
            default:
                throw new TrabajoJaxRSClientException();
        }
    }

    @Override
    public void updateItem(int codItem, ItemPost itemPost) throws TrabajoJaxRSClientException
    {
        Response response = client.target(UriBuilder.fromUri(URL_GET_ITEM_ENDPOINT)
                                        .build(codItem))
                                        .request()
                                        .put(Entity.json(itemPost));
        
        switch (response.getStatus())
        {
            case 200:
                break;
            default:
                throw new TrabajoJaxRSClientException();
        }
    }

    @Override
    public void deleteItem(int codItem) throws TrabajoJaxRSClientException
    {
        Response response = client.target(UriBuilder.fromUri(URL_GET_ITEM_ENDPOINT)
                                    .build(codItem))
                                    .request()
                                    .delete();
        
        switch (response.getStatus())
        {
            case 204:
                break;
            default:
                throw new TrabajoJaxRSClientException();
        }
    }
    
    private Item queryItem(URI uriLocation)
    {
        return client.target(uriLocation)
                        .request()
                        .accept(MediaType.APPLICATION_JSON)
                        .get()
                        .readEntity(Item.class);
    }

    @Override
    public void insertStatDeItem(int codItem, ItemManageStatPost post) throws TrabajoJaxRSClientException
    {
        Response response = client.target(UriBuilder.fromUri(URL_GET_ITEM_STATS_ENDPOINT)
                                        .build(codItem))
                                        .request()
                                        .post(Entity.json(post));
        
        switch (response.getStatus())
        {
            case 201:
                break;
            default:
                throw new TrabajoJaxRSClientException();
        }
    }

    @Override
    public void updateStatDeItem(int codItem, ItemManageStatPost post) throws TrabajoJaxRSClientException
    {
        Response response = client.target(UriBuilder.fromUri(URL_GET_ITEM_STATS_ENDPOINT)
                                        .build(codItem))
                                        .request()
                                        .put(Entity.json(post));
        
        switch (response.getStatus())
        {
            case 200:
                break;
            default:
                throw new TrabajoJaxRSClientException();
        }
    }

    @Override
    public void deleteStatDeItem(int codItem, int codStat) throws TrabajoJaxRSClientException
    {
        Response response = client.target(UriBuilder.fromUri(URL_GET_ITEM_STAT_ENDPOINT)
                                    .build(codItem, codStat))
                                    .request()
                                    .delete();
        
        switch (response.getStatus())
        {
            case 204:
                break;
            default:
                throw new TrabajoJaxRSClientException();
        }
    }

    @Override
    public StatWrapper getStatDeItem(int codItem, int codStat) throws TrabajoJaxRSClientException
    {
        Response response = client.target(UriBuilder.fromUri(URL_GET_ITEM_STAT_ENDPOINT)
                                    .build(codItem, codStat))
                                    .request()
                                    .accept(MediaType.APPLICATION_JSON)
                                    .get();
        
        StatWrapper stat = response.readEntity(StatWrapper.class);
        return stat;
    }
}
