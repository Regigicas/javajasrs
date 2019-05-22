/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrs.resources;

import es.upsa.ssi.jarxrs.cdi.qualifiers.LocallyProduced;
import es.upsa.ssi.jarxrs.exceptions.TrabajoJaxRSException;
import es.upsa.ssi.jarxrs.json.ItemCategoriaJson;
import es.upsa.ssi.jarxrs.json.forms.ItemCategoriaPost;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import es.upsa.ssi.jaxrs.ejbs.Logica;

/**
 * REST Web Service
 *
 * @author regigicas
 */
@Path("categorias")
public class CategoriaResource
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
                .entity(dao.getListadoCategoriasItem())
                .build();
    }
    
    @GET
    @Path("{codCategoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoriaJson(@PathParam("codCategoria") int codCategoria) throws TrabajoJaxRSException
    {
        return Response.ok()
                .entity(dao.getCategoriaItem(codCategoria))
                .build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertarCategoria(ItemCategoriaPost descripcion) throws TrabajoJaxRSException
    {
        ItemCategoriaJson.Builder builder = ItemCategoriaJson.Builder.newBuilder((p) -> uriInfo.getBaseUriBuilder().path("categorias/{codCategoria}")
                .build(p.getCodCategoria()));
        ItemCategoriaJson categoriaJson = builder.build(dao.insertCategoriaItem(descripcion.getDescripcion()));
        return Response.created(categoriaJson.getLink().getUri())
                .entity(categoriaJson)
                .build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{codCategoria}")
    public Response updateCategoria(@PathParam("codCategoria") int codCategoria, ItemCategoriaPost descripcion) throws TrabajoJaxRSException
    {
        ItemCategoriaJson.Builder builder = ItemCategoriaJson.Builder.newBuilder((p) -> uriInfo.getBaseUriBuilder().path("categorias/{codCategoria}")
                .build(p.getCodCategoria()));
        ItemCategoriaJson categoriaJson = builder.build(dao.updateCategoriaItem(codCategoria, descripcion.getDescripcion()));
        return Response.ok()
                .entity(categoriaJson)
                .build();
    }
    
    @DELETE
    @Path("{codCategoria}")
    public Response deleteCategoria(@PathParam("codCategoria") int codCategoria) throws TrabajoJaxRSException
    {
        dao.deleteCategoriaItem(codCategoria);
        return Response.status(Response.Status.NO_CONTENT)
                       .build();
    }
}
