/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrs.json;

import es.upsa.ssi.jaxrs.pojos.ItemStatType;
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
public class ItemStatTypeJson
{
    private ItemStatType itemStat;
    private Link link;

    private ItemStatTypeJson(ItemStatType itemStat, URI uri)
    {
        this.itemStat = itemStat;
        this.link = Link.fromUri(uri)
                .rel("self")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
    @JsonbProperty("codStat")
    public int getCodStat()
    {
        return this.itemStat.getCodStat();
    }
    
    @JsonbProperty("nombre")
    public String getNombre()
    {
        return this.itemStat.getNombre();
    }
    
    @JsonbProperty("link")
    @JsonbTypeAdapter(LinkJsonAdapter.class)
    public Link getLink()
    {
        return this.link;
    }
    
    public static class Builder
    {
        public static Builder newBuilder(Function<ItemStatType, URI> funcion)
        {
            return new Builder(funcion);
        }
        
        private Function<ItemStatType, URI> funcion;

        private Builder(Function<ItemStatType, URI> funcion)
        {
            this.funcion = funcion;
        }
        
        public ItemStatTypeJson build(ItemStatType stat)
        {
            return new ItemStatTypeJson(stat, funcion.apply(stat));
        }
        
        public List<ItemStatTypeJson> build(List<ItemStatType> stats)
        {
            return stats.stream()
                    .map((p) -> new ItemStatTypeJson(p, funcion.apply(p)))
                    .collect(Collectors.toList());
        }
    }
}
