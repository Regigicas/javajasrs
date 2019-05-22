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
public class ErrorItemNoExiste extends TrabajoJaxRSException
{
    private int codItem;

    public ErrorItemNoExiste(int codItem)
    {
        super(Integer.toString(codItem));
        this.codItem = codItem;
    }

    public int getCodItem()
    {
        return codItem;
    }

    public void setCodItem(int codItem)
    {
        this.codItem = codItem;
    }
}
