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
public class ErrorCategoriaNoExiste extends TrabajoJaxRSException
{
    private int codCategoria;

    public ErrorCategoriaNoExiste(int codCategoria)
    {
        super(Integer.toString(codCategoria));
        this.codCategoria = codCategoria;
    }

    public int getCodCategoria()
    {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria)
    {
        this.codCategoria = codCategoria;
    }
}
