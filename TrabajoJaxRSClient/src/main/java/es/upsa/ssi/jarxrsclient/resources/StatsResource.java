/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrsclient.resources;

import es.upsa.ssi.jarxrsclient.beans.Dao;
import es.upsa.ssi.jarxrsclient.exceptions.TrabajoJaxRSClientException;
import es.upsa.ssi.jarxrsclient.pojos.ItemStatType;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.View;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author regigicas
 */
@Path("stats")
@RequestScoped
public class StatsResource
{
    @Inject
    private Dao dao;
    
    @Inject
    private Models models;
    
    @GET
    @Controller
    public String getStats() throws TrabajoJaxRSClientException
    {
        List<ItemStatType> stats = dao.getListadoItemStats();
        models.put("stats", stats);
        return "/jsps/stats.jsp";
    }
    
    @GET
    @Controller
    @Path("{codStat}")
    public String getStat(@PathParam("codStat") int codStat) throws TrabajoJaxRSClientException
    {
        ItemStatType stat = dao.getItemStat(codStat);
        models.put("stat", stat);
        return "/jsps/stat.jsp";
    }
    
    @GET
    @Controller
    @Path("delete/{codStat}")
    public String deleteStat(@PathParam("codStat") int codStat) throws TrabajoJaxRSClientException
    {
        dao.deleteItemStat(codStat);
        return "redirect:stats";
    }
    
    @POST
    @Controller
    public String insertarStat(@FormParam("nombre") String nombre) throws TrabajoJaxRSClientException
    {
        ItemStatType stat = dao.insertItemStat(nombre);
        return String.format("redirect:stats/%d", stat.getCodStat());
    }
    
    @GET
    @Controller
    @Path("insert")
    @View("/jsps/forms/stat.jsp")
    public void getInsertarStat() {}
    
    @GET
    @Controller
    @Path("editar/{codStat}")
    public String getEditarStat(@PathParam("codStat") int codStat) throws TrabajoJaxRSClientException
    {
        ItemStatType stat = dao.getItemStat(codStat);
        models.put("stat", stat);
        return "/jsps/forms/stat_edit.jsp";
    }
    
    @POST
    @Controller
    @Path("editar/{codStat}")
    public String editarStat(@PathParam("codStat") int codStat, @FormParam("nombre") String nombre) throws TrabajoJaxRSClientException
    {
        dao.updateItemStat(codStat, nombre);
        return String.format("redirect:stats/%d", codStat);
    }
}
