/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jaxrs.ejbs;

import es.upsa.ssi.jarxrs.exceptions.TrabajoJaxRSException;
import es.upsa.ssi.jaxrs.pojos.ItemCategoria;
import java.util.List;

/**
 *
 * @author regigicas
 */
public interface CategoriaDao
{
    List<ItemCategoria> getListadoCategoriasItem() throws TrabajoJaxRSException;
    ItemCategoria getCategoriaItem(int codCategoria) throws TrabajoJaxRSException;
    ItemCategoria insertCategoriaItem(String descripcion) throws TrabajoJaxRSException;
    ItemCategoria updateCategoriaItem(int codCategoria, String descripcion) throws TrabajoJaxRSException;
    void deleteCategoriaItem(int codCategoria) throws TrabajoJaxRSException;
}
