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
public class ErrorInsertadoException extends TrabajoJaxRSException
{
    private String texto;

    public ErrorInsertadoException(String texto)
    {
        super(texto);
        this.texto = texto;
    }

    public String getTexto()
    {
        return texto;
    }
}
