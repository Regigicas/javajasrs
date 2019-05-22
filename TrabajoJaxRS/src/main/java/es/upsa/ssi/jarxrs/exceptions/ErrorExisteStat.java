/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrs.exceptions;

/**
 *
 * @author regigicas
 */
public class ErrorExisteStat extends TrabajoJaxRSException
{
    private String nombre;

    public ErrorExisteStat(String nombre)
    {
        super(nombre);
        this.nombre = nombre;
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
