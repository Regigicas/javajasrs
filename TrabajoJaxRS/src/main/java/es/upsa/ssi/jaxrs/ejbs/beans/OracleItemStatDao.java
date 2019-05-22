/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jaxrs.ejbs.beans;

import es.upsa.ssi.jarxrs.exceptions.ErrorItemNoExiste;
import es.upsa.ssi.jarxrs.exceptions.ErrorStatNoExiste;
import es.upsa.ssi.jarxrs.exceptions.SQLTrabajoJaxRSException;
import es.upsa.ssi.jarxrs.exceptions.TrabajoJaxRSException;
import es.upsa.ssi.jarxrs.json.forms.ItemManageStatPost;
import es.upsa.ssi.jaxrs.ejbs.ItemStatDao;
import es.upsa.ssi.jaxrs.pojos.StatWrapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@Local(ItemStatDao.class)
public class OracleItemStatDao implements ItemStatDao
{
    @Resource(name = "jdbc/oracle")
    private DataSource dataSource;
    
    private Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }
    
    @Override
    public List<StatWrapper> getStatsDeItem(int codItem) throws TrabajoJaxRSException
    {
        final String SQL_SELECT = "SELECT S.COD_ITEM, S.COD_STAT, ST.NOMBRE, S.VALOR FROM ITEM_STATS S LEFT JOIN STAT_TYPE_ITEM ST ON S.COD_STAT = ST.ID_STAT"
                + " WHERE S.COD_ITEM = ?";
        
        List<StatWrapper> stats = new LinkedList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT))
        {
            ps.setInt(1, codItem);
            
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {  
                    do
                    {
                        StatWrapper ist = new StatWrapper();
                        ist.setCodStat(rs.getInt(2));
                        ist.setNombre(rs.getString(3));
                        ist.setValue(rs.getInt(4));
                        stats.add(ist);
                    } while (rs.next());
                    
                    return stats;
                }
            }
        }
        catch (SQLException exp)
        {
            throw new SQLTrabajoJaxRSException(exp);
        }
        
        throw new ErrorItemNoExiste(codItem);
    }

    @Override
    public StatWrapper getStatDeItem(int codItem, int codStat) throws TrabajoJaxRSException
    {
        final String SQL_SELECT = "SELECT S.COD_ITEM, S.COD_STAT, ST.NOMBRE, S.VALOR FROM ITEM_STATS S LEFT JOIN STAT_TYPE_ITEM ST ON S.COD_STAT = ST.ID_STAT"
                + " WHERE S.COD_ITEM = ? AND S.COD_STAT = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT))
        {
            ps.setInt(1, codItem);
            ps.setInt(2, codStat);
            
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {  
                    StatWrapper ist = new StatWrapper();
                    ist.setCodStat(rs.getInt(2));
                    ist.setNombre(rs.getString(3));
                    ist.setValue(rs.getInt(4));     
                    return ist;
                }
            }
        }
        catch (SQLException exp)
        {
            throw new SQLTrabajoJaxRSException(exp);
        }
        
        throw new ErrorItemNoExiste(codItem);
    }

    @Override
    public void deleteStatDeItem(int codItem, int codStat) throws TrabajoJaxRSException
    {
        final String SQL_DELETE = "DELETE FROM ITEM_STATS WHERE COD_ITEM = ? AND COD_STAT = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE))
        {
            ps.setInt(1, codItem);
            ps.setInt(2, codStat);
            
            int count = ps.executeUpdate();
            if (count == 0)
                throw new ErrorStatNoExiste(codStat);
        }
        catch (SQLException exp)
        {
            throw new SQLTrabajoJaxRSException(exp);
        }
    }

    @Override
    public StatWrapper insertStatDeItem(int codItem, ItemManageStatPost post) throws TrabajoJaxRSException
    {
        final String SQL_INSERT = "INSERT INTO ITEM_STATS(COD_ITEM, COD_STAT, VALOR) VALUES (?, ?, ?)";
        final String SQL_SELECT = "SELECT ST.NOMBRE FROM STAT_TYPE_ITEM ST WHERE ST.ID_STAT = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps1 = conn.prepareStatement(SQL_INSERT);
             PreparedStatement ps2 = conn.prepareStatement(SQL_SELECT))
        {
            ps1.setInt(1, codItem);
            ps1.setInt(2, post.getCodStat());
            ps1.setInt(3, post.getValue());
            ps1.executeUpdate();
            
            StatWrapper ist = new StatWrapper();
            ist.setCodStat(post.getCodStat());
            ist.setValue(post.getValue());
            
            ps2.setInt(1, post.getCodStat());
            try (ResultSet rs = ps2.executeQuery())
            {
                if (rs.next())
                    ist.setNombre(rs.getString(1));
                else
                    throw new ErrorStatNoExiste(post.getCodStat());
            }
            
            return ist;
        }
        catch (SQLException ex)
        {
            String msg = ex.getMessage();
            if (msg.contains("FK_ITEM_STATS_COD_ITEM"))
                throw new ErrorItemNoExiste(codItem);
            
            if (msg.contains("FK_ITEM_COD_CAT"))
                throw new ErrorStatNoExiste(post.getCodStat());
            
            throw new SQLTrabajoJaxRSException(ex);
        }
    }

    @Override
    public StatWrapper updateStatDeItem(int codItem, ItemManageStatPost post) throws TrabajoJaxRSException
    {
        final String SQL_UPDATE = "UPDATE ITEM_STATS SET VALOR = ? WHERE COD_ITEM = ? AND COD_STAT = ?";
        final String SQL_SELECT = "SELECT ST.NOMBRE FROM STAT_TYPE_ITEM ST WHERE ST.ID_STAT = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps1 = conn.prepareStatement(SQL_UPDATE);
             PreparedStatement ps2 = conn.prepareStatement(SQL_SELECT))
        {
            ps1.setInt(1, post.getValue());
            ps1.setInt(2, codItem);
            ps1.setInt(3, post.getCodStat());
            int count = ps1.executeUpdate();
            if (count == 0)
                throw new ErrorItemNoExiste(codItem);
            
            StatWrapper ist = new StatWrapper();
            ist.setCodStat(post.getCodStat());
            ist.setValue(post.getValue());
            
            ps2.setInt(1, post.getCodStat());
            try (ResultSet rs = ps2.executeQuery())
            {
                if (rs.next())
                    ist.setNombre(rs.getString(1));
                else
                    throw new ErrorStatNoExiste(post.getCodStat());
            }
            
            return ist;
        }
        catch (SQLException ex)
        {
            String msg = ex.getMessage();
            if (msg.contains("FK_ITEM_STATS_COD_ITEM"))
                throw new ErrorItemNoExiste(codItem);
            
            if (msg.contains("FK_ITEM_COD_CAT"))
                throw new ErrorStatNoExiste(post.getCodStat());
            
            throw new SQLTrabajoJaxRSException(ex);
        }
    }
}
