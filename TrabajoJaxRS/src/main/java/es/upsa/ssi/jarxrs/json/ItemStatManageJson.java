/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrs.json;

import es.upsa.ssi.jaxrs.pojos.Item;
import es.upsa.ssi.jaxrs.pojos.ItemStatType;
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
public class ItemStatManageJson
{
    private StatWrapper stat;
    private Link link;

    private ItemStatManageJson(StatWrapper stat, URI uri)
    {
        this.stat = stat;
        this.link = Link.fromUri(uri)
                .rel("self")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
    @JsonbProperty("codStat")
    public int getCodStat()
    {
        return this.stat.getCodStat();
    }
    
    @JsonbProperty("nombre")
    public String getNombre()
    {
        return this.stat.getNombre();
    }
    
    @JsonbProperty("value")
    public int getValue()
    {
        return this.stat.getValue();
    }
    
    @JsonbProperty("link")
    @JsonbTypeAdapter(LinkJsonAdapter.class)
    public Link getLink()
    {
        return this.link;
    }
    
    public static class Builder
    {
        public static Builder newBuilder(Function<StatWrapper, URI> funcion)
        {
            return new Builder(funcion);
        }
        
        private Function<StatWrapper, URI> funcion;

        private Builder(Function<StatWrapper, URI> funcion)
        {
            this.funcion = funcion;
        }
        
        public ItemStatManageJson build(StatWrapper stat)
        {
            return new ItemStatManageJson(stat, funcion.apply(stat));
        }
        
        public List<ItemStatManageJson> build(List<StatWrapper> items)
        {
            return items.stream()
                    .map((i) -> new ItemStatManageJson(i, funcion.apply(i)))
                    .collect(Collectors.toList());
        }
    }
}
