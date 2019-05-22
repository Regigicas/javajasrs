/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrs.resources;

import es.upsa.ssi.jarxrs.cdi.qualifiers.LocallyProduced;
import es.upsa.ssi.jarxrs.exceptions.TrabajoJaxRSException;
import es.upsa.ssi.jarxrs.json.ItemStatTypeJson;
import es.upsa.ssi.jarxrs.json.forms.ItemStatPost;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import es.upsa.ssi.jaxrs.ejbs.Logica;

/**
 * REST Web Service
 *
 * @author regigicas
 */
@Path("stats")
public class StatsResource
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
                .entity(dao.getListadoItemStats())
                .build();
    }
    
    @GET
    @Path("{codStat}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatJson(@PathParam("codStat") int codStat) throws TrabajoJaxRSException
    {
        return Response.ok()
                .entity(dao.getItemStat(codStat))
                .build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertStat(ItemStatPost stat) throws TrabajoJaxRSException
    {
        ItemStatTypeJson.Builder builder = ItemStatTypeJson.Builder.newBuilder((p) -> uriInfo.getBaseUriBuilder().path("stats/{codStat}")
                .build(p.getCodStat()));
        ItemStatTypeJson itemStatJson = builder.build(dao.insertItemStat(stat.getNombre()));
        return Response.created(itemStatJson.getLink().getUri())
                .entity(itemStatJson)
                .build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{codStat}")
    public Response updateStat(@PathParam("codStat") int codStat, ItemStatPost stat) throws TrabajoJaxRSException
    {
        ItemStatTypeJson.Builder builder = ItemStatTypeJson.Builder.newBuilder((p) -> uriInfo.getBaseUriBuilder().path("stats/{codStat}")
                .build(p.getCodStat()));
        ItemStatTypeJson itemStatJson = builder.build(dao.updateItemStat(codStat, stat.getNombre()));
        return Response.ok()
                .entity(itemStatJson)
                .build();
    }
    
    @DELETE
    @Path("{codStat}")
    public Response deleteStat(@PathParam("codStat") int codStat) throws TrabajoJaxRSException
    {
        dao.deleteItemStat(codStat);
        return Response.status(Response.Status.NO_CONTENT)
                       .build();
    }
}
