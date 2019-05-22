/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jaxrs.ejbs.beans;

import es.upsa.ssi.jarxrs.exceptions.ErrorCategoriaNoExiste;
import es.upsa.ssi.jarxrs.exceptions.ErrorExisteStat;
import es.upsa.ssi.jarxrs.exceptions.ErrorInsertadoException;
import es.upsa.ssi.jarxrs.exceptions.ErrorStatNoExiste;
import es.upsa.ssi.jarxrs.exceptions.SQLTrabajoJaxRSException;
import es.upsa.ssi.jarxrs.exceptions.TrabajoJaxRSException;
import es.upsa.ssi.jaxrs.pojos.ItemStatType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import es.upsa.ssi.jaxrs.ejbs.StatDao;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 *
 * @author regigicas
 */
@Stateless
@Local(StatDao.class)
public class OracleStatDao implements StatDao
{
    @Resource(name = "jdbc/oracle")
    private DataSource dataSource;
    
    private Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }
    
    @Override
    public List<ItemStatType> getListadoItemStats() throws TrabajoJaxRSException
    {
        final String SQL_SELECT = "SELECT S.ID_STAT, S.NOMBRE FROM STAT_TYPE_ITEM S ORDER BY S.ID_STAT ASC";
        
        List<ItemStatType> stats = new LinkedList<>();
        
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SQL_SELECT))
        {
            if (rs.next())
            {
                do
                {                    
                    ItemStatType ist = new ItemStatType();
                    ist.setCodStat(rs.getInt(1));
                    ist.setNombre(rs.getString(2));
                    stats.add(ist);
                } while (rs.next());
            }
        }
        catch (SQLException exp)
        {
            throw new SQLTrabajoJaxRSException(exp);
        }
        
        return stats;
    }

    @Override
    public ItemStatType getItemStat(int codStat) throws TrabajoJaxRSException
    {
        final String SQL_SELECT = "SELECT S.ID_STAT, S.NOMBRE FROM STAT_TYPE_ITEM S WHERE S.ID_STAT = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT))
        {
            ps.setInt(1, codStat);
            
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {                
                    ItemStatType ist = new ItemStatType();
                    ist.setCodStat(rs.getInt(1));
                    ist.setNombre(rs.getString(2));
                    return ist;
                }
            }
        }
        catch (SQLException exp)
        {
            throw new SQLTrabajoJaxRSException(exp);
        }
        
        throw new ErrorCategoriaNoExiste(codStat);
    }

    @Override
    public ItemStatType insertItemStat(String nombre) throws TrabajoJaxRSException
    {
        final String SQL_INSERT = "INSERT INTO STAT_TYPE_ITEM(ID_STAT, NOMBRE) VALUES (SEQ_ID_STAT_TYPE.NEXTVAL, ?)";
        final String[] generated = { "ID_STAT" };
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT, generated))
        {
            ps.setString(1, nombre);
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys())
            {
                if (rs.next())
                {
                    ItemStatType cat = new ItemStatType();
                    cat.setCodStat(rs.getInt(1));
                    cat.setNombre(nombre);
                    return cat;
                }
                else
                    throw new ErrorInsertadoException(nombre);
            }
        }
        catch (SQLException ex)
        {
            String msg = ex.getMessage();
            if (msg.contains("UQ_STAT_ITEM_NOMBRE"))
                throw new ErrorExisteStat(nombre);
            
            throw new SQLTrabajoJaxRSException(ex);
        }
    }

    @Override
    public ItemStatType updateItemStat(int codStat, String nombre) throws TrabajoJaxRSException
    {
        final String SQL_UPDATE = "UPDATE STAT_TYPE_ITEM SET NOMBRE = ? WHERE ID_STAT = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE))
        {
            ps.setString(1, nombre);
            ps.setInt(2, codStat);
            
            int count = ps.executeUpdate();
            if (count == 0)
                throw new ErrorStatNoExiste(codStat);
            
            ItemStatType ist = new ItemStatType();
            ist.setCodStat(codStat);
            ist.setNombre(nombre);
            return ist;
        }
        catch (SQLException exp)
        {
            throw new SQLTrabajoJaxRSException(exp);
        }
    }

    @Override
    public void deleteItemStat(int codStat) throws TrabajoJaxRSException
    {
        final String SQL_DELETE1 = "DELETE FROM ITEM_STATS WHERE COD_STAT = ?";
        final String SQL_DELETE2 = "DELETE FROM STAT_TYPE_ITEM WHERE ID_STAT = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps1 = conn.prepareStatement(SQL_DELETE1);
             PreparedStatement ps2 = conn.prepareStatement(SQL_DELETE2))
        {
            ps1.setInt(1, codStat);
            ps2.setInt(1, codStat);
            
            ps1.executeUpdate();
            int count = ps2.executeUpdate();
            if (count == 0)
                throw new ErrorStatNoExiste(codStat);
        }
        catch (SQLException exp)
        {
            throw new SQLTrabajoJaxRSException(exp);
        }
    }
}
