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
public class ErrorExisteCategoria extends TrabajoJaxRSException
{
    private String categoria;

    public ErrorExisteCategoria(String categoria)
    {
        super(categoria);
        this.categoria = categoria;
    }

    public String getCategoria()
    {
        return categoria;
    }

    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }
}
