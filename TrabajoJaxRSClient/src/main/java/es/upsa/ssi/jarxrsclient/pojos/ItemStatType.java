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
public class ItemStatType
{
    private int codStat;
    private String nombre;

    public ItemStatType()
    {
    }

    public ItemStatType(int CodStat, String Nombre)
    {
        this.codStat = CodStat;
        this.nombre = Nombre;
    }

    public int getCodStat()
    {
        return codStat;
    }

    public void setCodStat(int CodStat)
    {
        this.codStat = CodStat;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String Nombre)
    {
        this.nombre = Nombre;
    }
}
