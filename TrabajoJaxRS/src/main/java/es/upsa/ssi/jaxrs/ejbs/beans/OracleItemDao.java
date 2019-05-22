/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jaxrs.ejbs.beans;

import es.upsa.ssi.jarxrs.exceptions.ErrorCategoriaNoExiste;
import es.upsa.ssi.jarxrs.exceptions.ErrorInsertadoException;
import es.upsa.ssi.jarxrs.exceptions.ErrorItemNoExiste;
import es.upsa.ssi.jarxrs.exceptions.ErrorStatNoExiste;
import es.upsa.ssi.jarxrs.exceptions.SQLTrabajoJaxRSException;
import es.upsa.ssi.jarxrs.exceptions.TrabajoJaxRSException;
import es.upsa.ssi.jarxrs.json.forms.ItemPost;
import es.upsa.ssi.jarxrs.json.forms.StatWrapperPost;
import es.upsa.ssi.jaxrs.ejbs.ItemDao;
import es.upsa.ssi.jaxrs.pojos.Item;
import es.upsa.ssi.jaxrs.pojos.ItemCategoria;
import es.upsa.ssi.jaxrs.pojos.StatWrapper;
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
@Local(ItemDao.class)
public class OracleItemDao implements ItemDao
{
    @Resource(name = "jdbc/oracle")
    private DataSource dataSource;
    
    private Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }

    @Override
    public List<Item> getListadoItem() throws TrabajoJaxRSException
    {
        final String SQL_SELECT = "SELECT I.ID_ITEM, I.NOMBRE, I.DESCRIPCION, C.ID_CAT, C.DESCRIPCION, S.ID_STAT, S.NOMBRE, ST.VALOR FROM ITEM I" +
                                    " LEFT JOIN CATEGORIA_ITEM C ON I.COD_CAT = C.ID_CAT" +
                                    " LEFT JOIN ITEM_STATS ST ON I.ID_ITEM = ST.COD_ITEM" +
                                    " LEFT JOIN STAT_TYPE_ITEM S ON ST.COD_STAT = S.ID_STAT" +
                                    " ORDER BY I.ID_ITEM ASC";
        
        List<Item> items = new LinkedList<>();
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SQL_SELECT))
        {
            if (rs.next())
            {
                boolean hasNext = false;
                do
                {
                    Item item = new Item();
                    item.setCodItem(rs.getInt(1));
                    item.setNombre(rs.getString(2));
                    item.setDescripcion(rs.getString(3));
                    item.setCategoria(new ItemCategoria(rs.getInt(4), rs.getString(5)));

                    if (rs.getString(7) != null)
                    {
                        do
                        {                        
                            StatWrapper ist = new StatWrapper();
                            ist.setCodStat(rs.getInt(6));
                            ist.setNombre(rs.getString(7));
                            ist.setValue(rs.getInt(8));
                            item.insertStat(ist);
                            hasNext = rs.next();
                        } while (hasNext && rs.getInt(1) == item.getCodItem());
                    }

                    items.add(item);
                } while (hasNext);
            }
        }
        catch (SQLException ex)
        {
            throw new SQLTrabajoJaxRSException(ex);
        }
        
        return items;
    }

    @Override
    public Item getItem(int codItem) throws TrabajoJaxRSException
    {
        final String SQL_SELECT = "SELECT I.ID_ITEM, I.NOMBRE, I.DESCRIPCION, C.ID_CAT, C.DESCRIPCION, S.ID_STAT, S.NOMBRE, ST.VALOR FROM ITEM I" +
                                    " LEFT JOIN CATEGORIA_ITEM C ON I.COD_CAT = C.ID_CAT" +
                                    " LEFT JOIN ITEM_STATS ST ON I.ID_ITEM = ST.COD_ITEM" +
                                    " LEFT JOIN STAT_TYPE_ITEM S ON ST.COD_STAT = S.ID_STAT" +
                                    " WHERE I.ID_ITEM = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(SQL_SELECT))
        {
            st.setInt(1, codItem);
            
            try (ResultSet rs = st.executeQuery())
            {
                if (rs.next())
                {
                    Item item = new Item();
                    item.setCodItem(rs.getInt(1));
                    item.setNombre(rs.getString(2));
                    item.setDescripcion(rs.getString(3));
                    item.setCategoria(new ItemCategoria(rs.getInt(4), rs.getString(5)));
                    
                    if (rs.getString(7) != null)
                    {
                        do
                        {                        
                            StatWrapper ist = new StatWrapper();
                            ist.setCodStat(rs.getInt(6));
                            ist.setNombre(rs.getString(7));
                            ist.setValue(rs.getInt(8));
                            item.insertStat(ist);
                        } while (rs.next());
                    }
                    
                    return item;
                }
                else
                    throw new ErrorItemNoExiste(codItem);
            }
        }
        catch (SQLException ex)
        {
            throw new SQLTrabajoJaxRSException(ex);
        }
    }

    @Override
    public Item insertItem(ItemPost itemPost) throws TrabajoJaxRSException
    {
        final String SQL_INSERT = "INSERT INTO ITEM(ID_ITEM, NOMBRE, DESCRIPCION, COD_CAT) VALUES (SEQ_ID_ITEM.NEXTVAL, ?, ?, ?)";
        final String[] generated = { "ID_ITEM" };
        
        final String SQL_INSERT2 = "INSERT INTO ITEM_STATS(COD_ITEM, COD_STAT, VALOR) VALUES (?, ?, ?)";
        
        final String SQL_SELECT1 = "SELECT C.DESCRIPCION FROM CATEGORIA_ITEM C WHERE C.ID_CAT = ?";
        final String SQL_SELECT2 = "SELECT ST.NOMBRE FROM STAT_TYPE_ITEM ST WHERE ST.ID_STAT = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps1 = conn.prepareStatement(SQL_INSERT, generated);
             PreparedStatement ps2 = conn.prepareStatement(SQL_INSERT2);
             PreparedStatement ps3 = conn.prepareStatement(SQL_SELECT1);
             PreparedStatement ps4 = conn.prepareStatement(SQL_SELECT2))
        {
            ps1.setString(1, itemPost.getNombre());
            ps1.setString(2, itemPost.getDescripcion());
            ps1.setInt(3, itemPost.getCodCategoria());
            ps1.executeUpdate();
            
            try (ResultSet rs = ps1.getGeneratedKeys())
            {
                if (rs.next())
                {
                    Item item = new Item();
                    item.setCodItem(rs.getInt(1));          
                    item.setNombre(itemPost.getNombre());
                    item.setDescripcion(itemPost.getDescripcion());
                    
                    ItemCategoria ict = new ItemCategoria();
                    ict.setCodCategoria(itemPost.getCodCategoria());    
                    ps3.setInt(1, itemPost.getCodCategoria());
                    try (ResultSet rs2 = ps3.executeQuery())
                    {
                        if (rs2.next())
                            ict.setDescripcion(rs2.getString(1));
                        else
                            throw new ErrorCategoriaNoExiste(itemPost.getCodCategoria());
                    }           
                    item.setCategoria(ict);
                    
                    for (StatWrapperPost stat : itemPost.getStats())
                    {
                        StatWrapper ist = new StatWrapper();
                        ist.setCodStat(stat.getCodStat());
                        ist.setValue(stat.getValue());
                        ps4.setInt(1, stat.getCodStat());
                        try (ResultSet rs2 = ps4.executeQuery())
                        {
                            if (rs2.next())
                                ist.setNombre(rs2.getString(1));
                            else
                                throw new ErrorStatNoExiste(ist.getCodStat());
                        }
                        item.insertStat(ist);
                        
                        ps2.setInt(1, item.getCodItem());
                        ps2.setInt(2, stat.getCodStat());
                        ps2.setInt(3, stat.getValue());
                        ps2.executeUpdate();
                    }
                    
                    return item;
                }
                else
                    throw new ErrorInsertadoException(itemPost.getNombre());
            }
        }
        catch (SQLException ex)
        {
            String msg = ex.getMessage();
            if (msg.contains("FK_ITEM_COD_CAT"))
                throw new ErrorCategoriaNoExiste(itemPost.getCodCategoria());
            
            throw new SQLTrabajoJaxRSException(ex);
        }
    }

    @Override
    public Item updateItem(int codItem, ItemPost itemPost) throws TrabajoJaxRSException
    {
        final String SQL_UPDATE = "UPDATE ITEM SET NOMBRE = ?, DESCRIPCION = ?, COD_CAT = ? WHERE ID_ITEM = ?";
        final String SQL_SELECT1 = "SELECT C.DESCRIPCION FROM CATEGORIA_ITEM C WHERE C.ID_CAT = ?";
        final String SQL_SELECT2 = "SELECT ST.ID_STAT, ST.NOMBRE, S.VALOR FROM ITEM_STATS S LEFT JOIN STAT_TYPE_ITEM ST ON S.COD_STAT = ST.ID_STAT"
                + " WHERE S.COD_ITEM = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps1 = conn.prepareStatement(SQL_UPDATE);
             PreparedStatement ps2 = conn.prepareStatement(SQL_SELECT1);
             PreparedStatement ps3 = conn.prepareStatement(SQL_SELECT2))
        {
            ps1.setString(1, itemPost.getNombre());
            ps1.setString(2, itemPost.getDescripcion());
            ps1.setInt(3, itemPost.getCodCategoria());
            ps1.setInt(4, codItem);
            
            int count = ps1.executeUpdate();
            if (count == 0)
                throw new ErrorItemNoExiste(codItem);
            
            Item item = new Item();
            item.setCodItem(codItem);          
            item.setNombre(itemPost.getNombre());
            item.setDescripcion(itemPost.getDescripcion());
                
            ItemCategoria ict = new ItemCategoria();
            ict.setCodCategoria(itemPost.getCodCategoria());
            ps2.setInt(1, itemPost.getCodCategoria());
            try (ResultSet rs2 = ps2.executeQuery())
            {
                if (rs2.next())
                    ict.setDescripcion(rs2.getString(1));
                else
                    throw new ErrorCategoriaNoExiste(itemPost.getCodCategoria());
            }
            item.setCategoria(ict);
            
            ps3.setInt(1, codItem);
            try (ResultSet rs2 = ps3.executeQuery())
            {
                if (rs2.next())
                {
                    do
                    {
                        StatWrapper ist = new StatWrapper();
                        ist.setCodStat(rs2.getInt(1));
                        ist.setNombre(rs2.getString(2));
                        ist.setValue(rs2.getInt(3));
                        item.insertStat(ist);
                    } while (rs2.next());
                }
            }
            return item;
        }
        catch (SQLException exp)
        {
            throw new SQLTrabajoJaxRSException(exp);
        }
    }

    @Override
    public void deleteItem(int codItem) throws TrabajoJaxRSException
    {
        final String SQL_DELETE1 = "DELETE FROM ITEM_STATS WHERE COD_ITEM = ?";
        final String SQL_DELETE2 = "DELETE FROM ITEM WHERE ID_ITEM = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps1 = conn.prepareStatement(SQL_DELETE1);
             PreparedStatement ps2 = conn.prepareStatement(SQL_DELETE2))
        {
            ps1.setInt(1, codItem);
            ps2.setInt(1, codItem);
            
            ps1.executeUpdate();
            int count = ps2.executeUpdate();
            if (count == 0)
                throw new ErrorItemNoExiste(codItem);
        }
        catch (SQLException exp)
        {
            throw new SQLTrabajoJaxRSException(exp);
        }
    }
}
