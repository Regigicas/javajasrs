/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrs.json;

import es.upsa.ssi.jaxrs.pojos.Item;
import es.upsa.ssi.jaxrs.pojos.ItemCategoria;
import es.upsa.ssi.jaxrs.pojos.StatWrapper;
import java.net.URI;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author regigicas
 */
public class ItemJson
{
    private Item item; 
    private Link link;

    public ItemJson(Item item, URI uri)
    {
        this.item = item;
        this.link = Link.fromUri(uri)
                .rel("self")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
    @JsonbProperty("codItem")
    public int getCodItem()
    {
        return this.item.getCodItem();
    }
    
    @JsonbProperty("nombre")
    public String getNombre()
    {
        return this.item.getNombre();
    }
    
    @JsonbProperty("descripcion")
    public String getDescripcion()
    {
        return this.item.getDescripcion();
    }
    
    @JsonbProperty("categoria")
    public ItemCategoria getCategoria()
    {
        return this.item.getCategoria();
    }
    
    @JsonbProperty("stats")
    public List<StatWrapper> getStats()
    {
        return this.item.getStats();
    }
    
    @JsonbProperty("link")
    @JsonbTypeAdapter(LinkJsonAdapter.class)
    public Link getLink()
    {
        return this.link;
    }
    
    public static class Builder
    {
        public static Builder newBuilder(Function<Item, URI> function)
        {
            return new Builder(function);
        }
        
        private Function<Item, URI> funcion;
        
        private Builder(Function<Item, URI> funcion)
        {
            this.funcion = funcion;
        }
        
        public ItemJson build(Item item)
        {
            return new ItemJson(item, funcion.apply(item));
        }
        
        public List<ItemJson> build(List<Item> items)
        {
            return items.stream()
                    .map((i) -> new ItemJson(i, funcion.apply(i)))
                    .collect(Collectors.toList());
        }
    }
}
