/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrsclient.resources;

import es.upsa.ssi.jarxrsclient.beans.Dao;
import es.upsa.ssi.jarxrsclient.exceptions.TrabajoJaxRSClientException;
import es.upsa.ssi.jarxrsclient.pojos.ItemCategoria;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.View;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author regigicas
 */
@Path("categorias")
@RequestScoped
public class CategoriasResource
{
    @Inject
    private Dao dao;
    
    @Inject
    private Models models;
    
    @GET
    @Controller
    public String getCategorias() throws TrabajoJaxRSClientException
    {
        List<ItemCategoria> categorias = dao.getListadoCategoriasItem();
        models.put("categorias", categorias);
        return "/jsps/categorias.jsp";
    }
    
    @GET
    @Controller
    @Path("{codCategoria}")
    public String getCategoria(@PathParam("codCategoria") int codCategoria) throws TrabajoJaxRSClientException
    {
        ItemCategoria categoria = dao.getCategoriaItem(codCategoria);
        models.put("categoria", categoria);
        return "/jsps/categoria.jsp";
    }
    
    @GET
    @Controller
    @Path("delete/{codCategoria}")
    public String deleteCategoria(@PathParam("codCategoria") int codCategoria) throws TrabajoJaxRSClientException
    {
        dao.deleteCategoriaItem(codCategoria);
        return "redirect:categorias";
    }
    
    @POST
    @Controller
    public String insertarCategoria(@FormParam("descripcion") String descripcion) throws TrabajoJaxRSClientException
    {
        ItemCategoria categoria = dao.insertCategoriaItem(descripcion);
        return String.format("redirect:categorias/%d", categoria.getCodCategoria());
    }
    
    @GET
    @Controller
    @Path("insert")
    @View("/jsps/forms/categoria.jsp")
    public void getInsertarCategoria() {}
    
    @GET
    @Controller
    @Path("editar/{codCategoria}")
    public String getEditarCategoria(@PathParam("codCategoria") int codCategoria) throws TrabajoJaxRSClientException
    {
        ItemCategoria categoria = dao.getCategoriaItem(codCategoria);
        models.put("categoria", categoria);
        return "/jsps/forms/categoria_edit.jsp";
    }
    
    @POST
    @Controller
    @Path("editar/{codCategoria}")
    public String editarCategoria(@PathParam("codCategoria") int codCategoria, @FormParam("descripcion") String descripcion) throws TrabajoJaxRSClientException
    {
        dao.updateCategoriaItem(codCategoria, descripcion);
        return String.format("redirect:categorias/%d", codCategoria);
    }
}
