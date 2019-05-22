/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrsclient.json.forms;

/**
 *
 * @author regigicas
 */
public class ItemCategoriaPost
{
    private String descripcion;

    public ItemCategoriaPost()
    {
    }

    public ItemCategoriaPost(String string)
    {
        this.descripcion = string;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }
}
