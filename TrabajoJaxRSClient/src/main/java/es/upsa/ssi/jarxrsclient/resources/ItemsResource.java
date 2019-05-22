/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrsclient.resources;

import es.upsa.ssi.jarxrsclient.beans.Dao;
import es.upsa.ssi.jarxrsclient.exceptions.TrabajoJaxRSClientException;
import es.upsa.ssi.jarxrsclient.json.forms.ItemManageStatPost;
import es.upsa.ssi.jarxrsclient.json.forms.ItemPost;
import es.upsa.ssi.jarxrsclient.json.forms.StatWrapperPost;
import es.upsa.ssi.jarxrsclient.pojos.Item;
import es.upsa.ssi.jarxrsclient.pojos.ItemCategoria;
import es.upsa.ssi.jarxrsclient.pojos.ItemStatType;
import es.upsa.ssi.jarxrsclient.pojos.StatWrapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.json.bind.JsonbBuilder;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author regigicas
 */
@Path("items")
@RequestScoped
public class ItemsResource
{
    @Inject
    private Dao dao;
    
    @Inject
    private Models models;
    
    @Resource(name = "jms/queue")
    private Queue queue;
    
    @Resource(name = "jms/queue2")
    private Queue queue2;
    
    @Resource(name = "jms/cf")
    private ConnectionFactory cf;
    
    @GET
    @Controller
    public String getItems() throws TrabajoJaxRSClientException
    {
        List<Item> items = dao.getListadoItem();
        models.put("items", items);
        return "/jsps/items.jsp";
    }
    
    @GET
    @Controller
    @Path("{codItem}")
    public String getItem(@PathParam("codItem") int codItem) throws TrabajoJaxRSClientException
    {
        Item item = dao.getItem(codItem);
        models.put("item", item);
        return "/jsps/item.jsp";
    }
    
    @GET
    @Controller
    @Path("delete/{codItem}")
    public String deleteItem(@PathParam("codItem") int codItem) throws TrabajoJaxRSClientException
    {
        dao.deleteItem(codItem);
        return "redirect:items";
    }
    
    @GET
    @Controller
    @Path("insert")
    public String getInsertarItem() throws TrabajoJaxRSClientException
    {
        List<ItemStatType> stats = dao.getListadoItemStats();
        List<ItemCategoria> categorias = dao.getListadoCategoriasItem();
        models.put("stats", stats);
        models.put("categorias", categorias);
        return "/jsps/forms/item.jsp";
    }
    
    @GET
    @Controller
    @Path("editar/{codItem}")
    public String getEditarItem(@PathParam("codItem") int codItem) throws TrabajoJaxRSClientException
    {
        Item item = dao.getItem(codItem);
        List<ItemCategoria> categorias = dao.getListadoCategoriasItem();
        models.put("item", item);
        models.put("categorias", categorias);
        return "/jsps/forms/item_edit.jsp";
    }
    
    @POST
    @Controller
    public String insertarItem(MultivaluedMap<String, String> formParams) throws TrabajoJaxRSClientException
    {
        ItemPost itemPost = new ItemPost();
        itemPost.setStats(new LinkedList<>());
        for (Map.Entry<String, List<String>> entry : formParams.entrySet())
        {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            
            switch (key)
            {
                case "nombre":
                    itemPost.setNombre(value.get(0));
                    break;
                case "descripcion":
                    itemPost.setDescripcion(value.get(0));
                    break;
                case "codCategoria":
                    itemPost.setCodCategoria(Integer.parseInt(value.get(0)));
                    break;
                default:
                    break;
            }
            
            if (key.contains("stat_"))
            {
                String[] split = key.split("_");
                String idStr = split[1];
                String valueStr = formParams.getFirst("value_" + idStr);
                
                StatWrapperPost sw = new StatWrapperPost();
                sw.setCodStat(Integer.parseInt(idStr));
                sw.setValue(Integer.parseInt(valueStr));
                itemPost.insertStat(sw);
            }
        }
        
        Item item = dao.insertItem(itemPost);
        return String.format("redirect:items/%d", item.getCodItem());
    }
    
    @POST
    @Controller
    @Path("editar/{codItem}")
    public String editarStat(@PathParam("codItem") int codItem, @FormParam("nombre") String nombre,
            @FormParam("descripcion") String descripcion, @FormParam("codCategoria") int codCategoria) throws TrabajoJaxRSClientException
    {
        ItemPost itemPost = new ItemPost();
        itemPost.setNombre(nombre);
        itemPost.setDescripcion(descripcion);
        itemPost.setCodCategoria(codCategoria);
        itemPost.setStats(new LinkedList<>());
        dao.updateItem(codItem, itemPost);
        return String.format("redirect:items/%d", codItem);
    }
    
    @GET
    @Controller
    @Path("{codItem}/stats/delete/{codStat}")
    public String borrarStatItem(@PathParam("codItem") int codItem, @PathParam("codStat") int codStat) throws TrabajoJaxRSClientException
    {
        dao.deleteStatDeItem(codItem, codStat);
        return String.format("redirect:items/%d", codItem);
    }
    
    @GET
    @Controller
    @Path("{codItem}/stats/editar/{codStat}")
    public String getEditarItemStat(@PathParam("codItem") int codItem, @PathParam("codStat") int codStat) throws TrabajoJaxRSClientException
    {
        Item item = dao.getItem(codItem);
        StatWrapper sw = dao.getStatDeItem(codItem, codStat);
        models.put("item", item);
        models.put("stat", sw);
        return "/jsps/forms/item_stat_edit.jsp";
    }
    
    @POST
    @Controller
    @Path("{codItem}/stats/editar/{codStat}")
    public String editarStat(@PathParam("codItem") int codItem, @PathParam("codStat") int codStat,
            @FormParam("value") int value) throws TrabajoJaxRSClientException
    {
        ItemManageStatPost post = new ItemManageStatPost();
        post.setCodStat(codStat);
        post.setValue(value);
        dao.updateStatDeItem(codItem, post);
        return String.format("redirect:items/%d", codItem);
    }
    
    @GET
    @Controller
    @Path("{codItem}/stats/insert")
    public String getInsertarStatItem(@PathParam("codItem") int codItem) throws TrabajoJaxRSClientException
    {
        Item item = dao.getItem(codItem);
        List<ItemStatType> stats = dao.getListadoItemStats();
        List<ItemStatType> toRemove = new LinkedList<>();
        
        for (StatWrapper sw : item.getStats())
        {
            for (ItemStatType stat : stats)
            {
                if (sw.getCodStat() == stat.getCodStat())
                {
                    toRemove.add(stat);
                    break;
                }
            }
        }
        
        stats.removeAll(toRemove);
        
        models.put("item", item);
        models.put("stats", stats);
        return "/jsps/forms/item_stat.jsp";
    }
    
    @POST
    @Controller
    @Path("{codItem}/stats/insert")
    public String insertarItemStat(@PathParam("codItem") int codItem, @FormParam("codStat") int codStat,
            @FormParam("value") int value) throws TrabajoJaxRSClientException
    {
        ItemManageStatPost post = new ItemManageStatPost();
        post.setCodStat(codStat);
        post.setValue(value);
        dao.insertStatDeItem(codItem, post);
        return String.format("redirect:items/%d", codItem);
    }
    
    @POST
    @Controller
    @Path("jms")
    public String postJMSMessage(@FormParam("codItem") int codItem) throws JMSException
    {
        Item item = null;
        try (JMSContext context  = cf.createContext())
        {
            context.createProducer().send(queue, codItem);
            try (JMSConsumer consumer = context.createConsumer(queue2))
            {
                Message msg = consumer.receive();
                String jsonString = msg.getBody(String.class);
                item = JsonbBuilder.create().fromJson(jsonString, Item.class);
            }
        }
        
        models.put("item", item);
        return "/jsps/item.jsp";
    }
    
    @GET
    @Controller
    @Path("jms")
    public String getJMSView() throws TrabajoJaxRSClientException
    {
        List<Item> items = dao.getListadoItem();
        models.put("items", items);
        return "/jsps/items_jms.jsp";
    }
}
