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
public class ErrorStatNoExiste extends TrabajoJaxRSException
{
    private int codStat;

    public ErrorStatNoExiste(int codStat)
    {
        super(Integer.toString(codStat));
        this.codStat = codStat;
    }

    public int getCodStat()
    {
        return codStat;
    }

    public void setCodCategoria(int codStat)
    {
        this.codStat = codStat;
    }
}