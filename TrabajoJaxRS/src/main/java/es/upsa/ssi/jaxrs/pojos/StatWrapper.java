/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jaxrs.pojos;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

/**
 *
 * @author regigicas
 */
public class StatWrapper
{
    private int codStat;
    private String nombre;
    private int value;
    
    public static List<StatWrapper> buildListFrom(Map<ItemStatType, Integer> stats)
    {
        List<StatWrapper> list = new LinkedList<>();
        for (Map.Entry<ItemStatType, Integer> entry : stats.entrySet())
        {
            ItemStatType key = entry.getKey();
            Integer value = entry.getValue();
            
            StatWrapper wrapper = new StatWrapper();
            wrapper.setCodStat(key.getCodStat());
            wrapper.setNombre(key.getNombre());
            wrapper.setValue(value);
            list.add(wrapper);
        }
        return list;
    }
    
    public static StatWrapper buildFromPair(Pair<ItemStatType, Integer> pair)
    {
        StatWrapper wrapper = new StatWrapper();
        wrapper.setCodStat(pair.getKey().getCodStat());
        wrapper.setNombre(pair.getKey().getNombre());
        wrapper.setValue(pair.getValue());
        return wrapper;
    }

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
