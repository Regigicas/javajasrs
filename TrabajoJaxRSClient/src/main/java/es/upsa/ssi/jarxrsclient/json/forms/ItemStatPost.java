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
public class ItemStatPost
{
    private String nombre;

    public ItemStatPost()
    {
    }

    public ItemStatPost(String string)
    {
        this.nombre = string;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
}
