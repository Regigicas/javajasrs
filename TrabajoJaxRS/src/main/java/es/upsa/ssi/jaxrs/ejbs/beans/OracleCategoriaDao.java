/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jaxrs.ejbs.beans;

import es.upsa.ssi.jarxrs.exceptions.ErrorCategoriaNoExiste;
import es.upsa.ssi.jarxrs.exceptions.ErrorExisteCategoria;
import es.upsa.ssi.jarxrs.exceptions.ErrorInsertadoException;
import es.upsa.ssi.jarxrs.exceptions.SQLTrabajoJaxRSException;
import es.upsa.ssi.jarxrs.exceptions.TrabajoJaxRSException;
import es.upsa.ssi.jaxrs.ejbs.CategoriaDao;
import es.upsa.ssi.jaxrs.pojos.ItemCategoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 *
 * @author regigicas
 */
@Stateless
@Local(CategoriaDao.class)
public class OracleCategoriaDao implements CategoriaDao
{
    @Resource(name = "jdbc/oracle")
    private DataSource dataSource;
    
    private Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }
    
    @Override
    public List<ItemCategoria> getListadoCategoriasItem() throws TrabajoJaxRSException
    {
        final String SQL_SELECT = "SELECT C.ID_CAT, C.DESCRIPCION FROM CATEGORIA_ITEM C ORDER BY C.ID_CAT ASC";
        
        List<ItemCategoria> categorias = new LinkedList<>();
        
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SQL_SELECT))
        {
            if (rs.next())
            {
                do
                {                    
                    ItemCategoria ic = new ItemCategoria();
                    ic.setCodCategoria(rs.getInt(1));
                    ic.setDescripcion(rs.getString(2));
                    categorias.add(ic);
                } while (rs.next());
            }
        }
        catch (SQLException ex)
        {
            throw new SQLTrabajoJaxRSException(ex);
        }
        
        return categorias;
    }

    @Override
    public ItemCategoria insertCategoriaItem(String descripcion) throws TrabajoJaxRSException
    {
        final String SQL_INSERT = "INSERT INTO CATEGORIA_ITEM(ID_CAT, DESCRIPCION) VALUES (SEQ_ID_CATEGORIA.NEXTVAL, ?)";
        final String[] generated = { "ID_CAT" };
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT, generated))
        {
            ps.setString(1, descripcion);
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys())
            {
                if (rs.next())
                {
                    ItemCategoria cat = new ItemCategoria();
                    cat.setCodCategoria(rs.getInt(1));
                    cat.setDescripcion(descripcion);
                    return cat;
                }
                else
                    throw new ErrorInsertadoException(descripcion);
            }
        }
        catch (SQLException ex)
        {
            String msg = ex.getMessage();
            if (msg.contains("UQ_CATEGORIA_ITEM_DESCRIPCION"))
                throw new ErrorExisteCategoria(descripcion);
            
            throw new SQLTrabajoJaxRSException(ex);
        }
    }

    @Override
    public ItemCategoria getCategoriaItem(int codCategoria) throws TrabajoJaxRSException
    {
        final String SQL_SELECT = "SELECT C.ID_CAT, C.DESCRIPCION FROM CATEGORIA_ITEM C WHERE C.ID_CAT = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT))
        {
            ps.setInt(1, codCategoria);
            
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {                
                    ItemCategoria ic = new ItemCategoria();
                    ic.setCodCategoria(rs.getInt(1));
                    ic.setDescripcion(rs.getString(2));
                    return ic;
                }
            }
        }
        catch (SQLException exp)
        {
            throw new SQLTrabajoJaxRSException(exp);
        }
        
        throw new ErrorCategoriaNoExiste(codCategoria);
    }

    @Override
    public ItemCategoria updateCategoriaItem(int codCategoria, String descripcion) throws TrabajoJaxRSException
    {
        final String SQL_UPDATE = "UPDATE CATEGORIA_ITEM SET DESCRIPCION = ? WHERE ID_CAT = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE))
        {
            ps.setString(1, descripcion);
            ps.setInt(2, codCategoria);
            
            int count = ps.executeUpdate();
            if (count == 0)
                throw new ErrorCategoriaNoExiste(codCategoria);
            
            ItemCategoria ic = new ItemCategoria();
            ic.setCodCategoria(codCategoria);
            ic.setDescripcion(descripcion);
            return ic;
        }
        catch (SQLException exp)
        {
            throw new SQLTrabajoJaxRSException(exp);
        }
    }

    @Override
    public void deleteCategoriaItem(int codCategoria) throws TrabajoJaxRSException
    {
        final String SQL_DELETE1 = "DELETE FROM ITEM_STATS WHERE COD_ITEM IN (SELECT ID_ITEM FROM ITEM WHERE COD_CAT = ?)";
        final String SQL_DELETE2 = "DELETE FROM ITEM WHERE COD_CAT = ?";
        final String SQL_DELETE3 = "DELETE FROM CATEGORIA_ITEM WHERE ID_CAT = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps1 = conn.prepareStatement(SQL_DELETE1);
             PreparedStatement ps2 = conn.prepareStatement(SQL_DELETE2);
             PreparedStatement ps3 = conn.prepareStatement(SQL_DELETE3))
        {
            ps1.setInt(1, codCategoria);
            ps2.setInt(1, codCategoria);
            ps3.setInt(1, codCategoria);
            
            ps1.executeUpdate();
            ps2.executeUpdate();
            int count = ps3.executeUpdate();
            if (count == 0)
                throw new ErrorCategoriaNoExiste(codCategoria);
        }
        catch (SQLException exp)
        {
            throw new SQLTrabajoJaxRSException(exp);
        }
    }
}
