/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jaxrs.ejbs;

import es.upsa.ssi.jarxrs.exceptions.TrabajoJaxRSException;
import es.upsa.ssi.jarxrs.json.forms.ItemPost;
import es.upsa.ssi.jaxrs.pojos.Item;
import java.util.List;

/**
 *
 * @author regigicas
 */
public interface ItemDao
{
    List<Item> getListadoItem() throws TrabajoJaxRSException;
    Item getItem(int codItem) throws TrabajoJaxRSException;
    Item insertItem(ItemPost itemPost) throws TrabajoJaxRSException;
    Item updateItem(int codItem, ItemPost itemPost) throws TrabajoJaxRSException;
    void deleteItem(int codItem) throws TrabajoJaxRSException;
}
