/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jaxrs.pojos;

/**
 *
 * @author regigicas
 */
public class ItemCategoria
{
    private int codCategoria;
    private String descripcion;

    public ItemCategoria()
    {
    }

    public ItemCategoria(int CodCategoria, String Descripcion)
    {
        this.codCategoria = CodCategoria;
        this.descripcion = Descripcion;
    }

    public int getCodCategoria()
    {
        return codCategoria;
    }

    public void setCodCategoria(int CodCategoria)
    {
        this.codCategoria = CodCategoria;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String Descripcion)
    {
        this.descripcion = Descripcion;
    }
}
