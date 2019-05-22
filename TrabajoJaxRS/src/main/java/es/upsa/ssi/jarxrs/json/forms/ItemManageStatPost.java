/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrs.json.forms;

/**
 *
 * @author regigicas
 */
public class ItemManageStatPost
{
    private int codStat;
    private int value;

    public ItemManageStatPost()
    {
    }

    public ItemManageStatPost(int codStat, int value)
    {
        this.codStat = codStat;
        this.value = value;
    }

    public int getCodStat()
    {
        return codStat;
    }

    public void setCodStat(int codStat)
    {
        this.codStat = codStat;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }
}
