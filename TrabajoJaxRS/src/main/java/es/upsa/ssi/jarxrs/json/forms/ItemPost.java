/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrs.json.forms;

import java.util.List;

/**
 *
 * @author regigicas
 */
public class ItemPost
{
    private String nombre;
    private String descripcion;
    private int codCategoria;
    private List<StatWrapperPost> stats;

    public ItemPost()
    {
    }

    public ItemPost(String nombre, String descripcion, int codCategoria, List<StatWrapperPost> stats)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codCategoria = codCategoria;
        this.stats = stats;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public int getCodCategoria()
    {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria)
    {
        this.codCategoria = codCategoria;
    }

    public List<StatWrapperPost> getStats()
    {
        return stats;
    }

    public void setStats(List<StatWrapperPost> stats)
    {
        this.stats = stats;
    }
}
