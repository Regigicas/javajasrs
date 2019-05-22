/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrsclient.pojos;

/**
 *
 * @author regigicas
 */
public class StatWrapper
{
    private int codStat;
    private String nombre;
    private int value;

    public void setCodStat(int codStat)
    {
        this.codStat = codStat;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public int getCodStat()
    {
        return codStat;
    }

    public String getNombre()
    {
        return nombre;
    }

    public int getValue()
    {
        return value;
    }
}
