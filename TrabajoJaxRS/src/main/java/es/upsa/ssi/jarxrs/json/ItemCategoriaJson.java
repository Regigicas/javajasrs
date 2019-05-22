/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrs.json;

import es.upsa.ssi.jaxrs.pojos.ItemCategoria;
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
public class ItemCategoriaJson
{
    private ItemCategoria itemCategoria;
    private Link link;

    private ItemCategoriaJson(ItemCategoria itemCategoria, URI uri)
    {
        this.itemCategoria = itemCategoria;
        this.link = Link.fromUri(uri)
                .rel("self")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
    @JsonbProperty("codCategoria")
    public int getCodCategoria()
    {
        return this.itemCategoria.getCodCategoria();
    }
    
    @JsonbProperty("descripcion")
    public String getDescripcion()
    {
        return this.itemCategoria.getDescripcion();
    }
    
    @JsonbProperty("link")
    @JsonbTypeAdapter(LinkJsonAdapter.class)
    public Link getLink()
    {
        return this.link;
    }
    
    public static class Builder
    {
        public static Builder newBuilder(Function<ItemCategoria, URI> funcion)
        {
            return new Builder(funcion);
        }
        
        private Function<ItemCategoria, URI> funcion;

        private Builder(Function<ItemCategoria, URI> funcion)
        {
            this.funcion = funcion;
        }
        
        public ItemCategoriaJson build(ItemCategoria categoria)
        {
            return new ItemCategoriaJson(categoria, funcion.apply(categoria));
        }
        
        public List<ItemCategoriaJson> build(List<ItemCategoria> categorias)
        {
            return categorias.stream()
                    .map((p) -> new ItemCategoriaJson(p, funcion.apply(p)))
                    .collect(Collectors.toList());
        }
    }
}
