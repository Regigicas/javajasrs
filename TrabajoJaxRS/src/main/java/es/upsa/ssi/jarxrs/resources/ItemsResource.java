/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrs.resources;

import es.upsa.ssi.jarxrs.cdi.qualifiers.LocallyProduced;
import es.upsa.ssi.jarxrs.exceptions.TrabajoJaxRSException;
import es.upsa.ssi.jarxrs.json.ItemJson;
import es.upsa.ssi.jarxrs.json.ItemStatManageJson;
import es.upsa.ssi.jarxrs.json.forms.ItemManageStatPost;
import es.upsa.ssi.jarxrs.json.forms.ItemPost;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import es.upsa.ssi.jaxrs.ejbs.Logica;

/**
 * REST Web Service
 *
 * @author regigicas
 */
@Path("items")
public class ItemsResource
{
    @Inject
    @LocallyProduced
    private Logica dao;
    
    @Context
    private UriInfo uriInfo;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() throws TrabajoJaxRSException
    {
        return Response.ok()
                .entity(dao.getListadoItem())
                .build();
    }
    
    @GET
    @Path("{codItem}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemJson(@PathParam("codItem") int codItem) throws TrabajoJaxRSException
    {
        return Response.ok()
                .entity(dao.getItem(codItem))
                .build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertItem(ItemPost itemPost) throws TrabajoJaxRSException
    {
        ItemJson.Builder builder = ItemJson.Builder.newBuilder((i) -> uriInfo.getBaseUriBuilder().path("items/{codItem}")
                .build(i.getCodItem()));
        ItemJson itemJson = builder.build(dao.insertItem(itemPost));
        return Response.created(itemJson.getLink().getUri())
                .entity(itemJson)
                .build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{codItem}")
    public Response updateItem(@PathParam("codItem") int codItem, ItemPost itemPost) throws TrabajoJaxRSException
    {
        ItemJson.Builder builder = ItemJson.Builder.newBuilder((i) -> uriInfo.getBaseUriBuilder().path("items/{codItem}")
                .build(i.getCodItem()));
        ItemJson itemJson = builder.build(dao.updateItem(codItem, itemPost));
        return Response.ok()
                .entity(itemJson)
                .build();
    }
    
    @DELETE
    @Path("{codItem}")
    public Response deleteItem(@PathParam("codItem") int codItem) throws TrabajoJaxRSException
    {
        dao.deleteItem(codItem);
        return Response.status(Response.Status.NO_CONTENT)
                .build();
    }
    
    @GET
    @Path("{codItem}/stats")
    public Response getItemStats(@PathParam("codItem") int codItem) throws TrabajoJaxRSException
    {
        return Response.ok()
                .entity(dao.getStatsDeItem(codItem))
                .build();
    }
    
    @GET
    @Path("{codItem}/stats/{codStat}")
    public Response getItemStat(@PathParam("codItem") int codItem, @PathParam("codStat") int codStat) throws TrabajoJaxRSException
    {
        return Response.ok()
                .entity(dao.getStatDeItem(codItem, codStat))
                .build();
    }
    
    @POST
    @Path("{codItem}/stats")
    public Response insertItemStat(@PathParam("codItem") int codItem, ItemManageStatPost post) throws TrabajoJaxRSException
    {
        ItemStatManageJson.Builder builder = ItemStatManageJson.Builder.newBuilder((i) -> uriInfo.getBaseUriBuilder().path("items/{codItem}/stats/{codStat}")
                .build(codItem, i.getCodStat()));
        ItemStatManageJson itemJson = builder.build(dao.insertStatDeItem(codItem, post));
        return Response.created(itemJson.getLink().getUri())
                .entity(itemJson)
                .build();
    }
    
    @PUT
    @Path("{codItem}/stats")
    public Response updateItemStat(@PathParam("codItem") int codItem, ItemManageStatPost post) throws TrabajoJaxRSException
    {
        ItemStatManageJson.Builder builder = ItemStatManageJson.Builder.newBuilder((i) -> uriInfo.getBaseUriBuilder().path("items/{codItem}/stats/{codStat}")
                .build(codItem, i.getCodStat()));
        ItemStatManageJson itemJson = builder.build(dao.updateStatDeItem(codItem, post));
        return Response.ok()
                .entity(itemJson)
                .build();
    }
    
    @DELETE
    @Path("{codItem}/stats/{codStat}")
    public Response deleteItemStat(@PathParam("codItem") int codItem, @PathParam("codStat") int codStat) throws TrabajoJaxRSException
    {
        dao.deleteStatDeItem(codItem, codStat);
        return Response.status(Response.Status.NO_CONTENT)
                .build();
    }
}
