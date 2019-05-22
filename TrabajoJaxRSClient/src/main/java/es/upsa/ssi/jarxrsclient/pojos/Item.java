/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrsclient.pojos;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author regigicas
 */
public class Item
{
    private int codItem;
    private String nombre;
    private String descripcion;
    private ItemCategoria categoria;
    private List<StatWrapper> stats = new LinkedList<>();
    
    public Item()
    {
    }

    public Item(int codItem, String nombre, String descripcion, ItemCategoria categoria, List<StatWrapper> stats)
    {
        this.codItem = codItem;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.stats = stats;
    }

    public int getCodItem()
    {
        return codItem;
    }

    public void setCodItem(int CodItem)
    {
        this.codItem = CodItem;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String Nombre)
    {
        this.nombre = Nombre;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String Descripcion)
    {
        this.descripcion = Descripcion;
    }

    public ItemCategoria getCategoria()
    {
        return this.categoria;
    }

    public void setCategoria(ItemCategoria categoria)
    {
        this.categoria = categoria;
    }

    public List<StatWrapper> getStats()
    {
        return this.stats;
    }
    
    public void setStats(List<StatWrapper> stats)
    {
        this.stats = stats;
    }
    
    public void insertStat(StatWrapper stat)
    {
        this.stats.add(stat);
    }
}
