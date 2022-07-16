package dao.impl;

import util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.DaoDetalleVenta;
import entities.DetalleVenta;
import java.time.LocalDate;

/**
 * Se implementa DaoVentaImpl con el DaoVenta para obtener los métodos del DAO.
 */
public class DaoDetalleVentaImpl implements DaoDetalleVenta {

    private final ConexionDB ConexionDB;
    private String mensaje;

    public DaoDetalleVentaImpl() {
        this.ConexionDB = new ConexionDB();
    }

    /**
     * @return retorna una lista de detalle_ventas
     */
    @Override
    public List<DetalleVenta> dventaSel() {
        List<DetalleVenta> list = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id,")
                .append("producto,")
                .append("venta,")
                .append("cantidad,")
                .append("moneda,")
                .append("t_cambio,")
                .append("igv,")
                .append("descuento,")
                .append("precio,")
                .append("total,")
                .append("fecha,")
                .append("met_pago,")
                .append("referencia,")
                .append("nota")
                .append(" FROM detalle_ventas");
        try ( Connection cn = ConexionDB.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                DetalleVenta dventa = new DetalleVenta();
                dventa.setId(rs.getInt(1));
                dventa.setProducto(rs.getInt(2));
                dventa.setVenta(rs.getInt(3));
                dventa.setCantidad(rs.getInt(4));
                dventa.setMoneda(rs.getString(5));
                dventa.setT_cambio(rs.getDouble(6));
                dventa.setIgv(rs.getDouble(7));
                dventa.setDescuento(rs.getDouble(8));
                dventa.setPrecio(rs.getDouble(9));
                dventa.setTotal(rs.getDouble(10));
                dventa.setFecha(LocalDate.parse(rs.getString(11)));
                dventa.setMet_pago(rs.getString(12));
                dventa.setReferencia(rs.getString(13));
                dventa.setNota(rs.getString(14));
                list.add(dventa);
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return list;
    }

    /**
     * @param id se recibe como parámetro para obtener más información de un
     * dventa, según el id.
     * @return retorna una lista de dventas.
     */
    @Override
    public DetalleVenta dventaGet(Integer id) {
        DetalleVenta dventa = new DetalleVenta();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id,")
                .append("producto,")
                .append("venta,")
                .append("cantidad,")
                .append("moneda,")
                .append("t_cambio,")
                .append("igv,")
                .append("descuento,")
                .append("precio,")
                .append("total,")
                .append("fecha,")
                .append("met_pago,")
                .append("referencia,")
                .append("nota")
                .append(" FROM detalle_ventas WHERE id = ?");
        try ( Connection cn = ConexionDB.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dventa.setId(rs.getInt(1));
                    dventa.setProducto(rs.getInt(2));
                    dventa.setVenta(rs.getInt(3));
                    dventa.setCantidad(rs.getInt(4));
                    dventa.setMoneda(rs.getString(5));
                    dventa.setT_cambio(rs.getDouble(6));
                    dventa.setIgv(rs.getDouble(7));
                    dventa.setDescuento(rs.getDouble(8));
                    dventa.setPrecio(rs.getDouble(9));
                    dventa.setTotal(rs.getDouble(10));
                    dventa.setFecha(LocalDate.parse(rs.getString(11)));
                    dventa.setMet_pago(rs.getString(12));
                    dventa.setReferencia(rs.getString(13));
                    dventa.setNota(rs.getString(14));
                } else {
                    dventa = null;
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return dventa;
    }

    @Override
    public List<DetalleVenta> dventaCbo() {
        List<DetalleVenta> list = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id,")
                .append("producto,")
                .append("venta,")
                .append("cantidad,")
                .append("moneda,")
                .append("t_cambio,")
                .append("igv,")
                .append("descuento,")
                .append("precio,")
                .append("total,")
                .append("fecha,")
                .append("met_pago,")
                .append("referencia,")
                .append("nota")
                .append(" FROM detalle_ventas");
        try ( Connection cn = ConexionDB.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                DetalleVenta dventa = new DetalleVenta();
                dventa.setId(rs.getInt(1));
                dventa.setProducto(rs.getInt(2));
                dventa.setVenta(rs.getInt(3));
                dventa.setCantidad(rs.getInt(4));
                dventa.setMoneda(rs.getString(5));
                dventa.setT_cambio(rs.getDouble(6));
                dventa.setIgv(rs.getDouble(7));
                dventa.setDescuento(rs.getDouble(8));
                dventa.setPrecio(rs.getDouble(9));
                dventa.setTotal(rs.getDouble(10));
                dventa.setFecha(LocalDate.parse(rs.getString(11)));
                dventa.setMet_pago(rs.getString(12));
                dventa.setReferencia(rs.getString(13));
                dventa.setNota(rs.getString(14));
                list.add(dventa);
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return list;
    }

    /**
     * @param dventa se recibe el objeto dventas para insertar la información en
     * la DB.
     * @return retorna un mensaje
     */
    @Override
    public String dventaIns(DetalleVenta dventa) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO dventas( ")
                .append("producto,")
                .append("venta,")
                .append("cantidad,")
                .append("moneda,")
                .append("t_cambio,")
                .append("igv,")
                .append("descuento,")
                .append("precio,")
                .append("total,")
                .append("fecha,")
                .append("met_pago,")
                .append("referencia,")
                .append("nota")
                .append(") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ");
        try ( Connection cn = ConexionDB.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, dventa.getProducto());
            ps.setInt(2, dventa.getVenta());
            ps.setInt(3, dventa.getCantidad());
            ps.setString(4, dventa.getMoneda());
            ps.setDouble(5, dventa.getT_cambio());
            ps.setDouble(6, dventa.getIgv());
            ps.setDouble(7, dventa.getDescuento());
            ps.setDouble(8, dventa.getPrecio());
            ps.setDouble(9, dventa.getTotal());
            ps.setString(10, dventa.getFecha().toString());
            ps.setString(11, dventa.getMet_pago());
            ps.setString(12, dventa.getReferencia());
            ps.setString(13, dventa.getNota());
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                mensaje = "No se insertaron registros";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    /**
     * @param ids se recibe los ids de los dventas a eliminar de la DB.
     * @return retorna un mensaje
     */
    @Override
    public String dventaDel(List<Integer> ids) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM detalle_ventas WHERE ")
                .append("id = ? ");
        try ( Connection cn = ConexionDB.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            cn.setAutoCommit(false);
            boolean ok = true;
            for (int id = 0; id < ids.size(); id++) {
                ps.setInt(1, ids.get(id));
                int ctos = ps.executeUpdate();
                if (ctos == 0) {
                    ok = false;
                    mensaje = "ID: " + id + " no existe";
                }
            }
            if (ok) {
                cn.commit();
            } else {
                cn.rollback();
            }
            cn.setAutoCommit(true);
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    /**
     * @param dventa se recibe el objeto dventas para actualizar la información
     * en la DB.
     * @return retorna un mensaje
     */
    @Override
    public String dventaUpd(DetalleVenta dventa) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE detalle_ventas SET ")
                .append("producto = ?,")
                .append("venta = ?,")
                .append("cantidad = ?,")
                .append("moneda = ?,")
                .append("t_cambio = ?,")
                .append("igv = ?,")
                .append("descuento = ?,")
                .append("precio = ?,")
                .append("total = ?,")
                .append("fecha = ?,")
                .append("met_pago = ?,")
                .append("referencia = ?,")
                .append("nota = ?")
                .append("WHERE id = ? ");
        try ( Connection cn = ConexionDB.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, dventa.getProducto());
            ps.setInt(2, dventa.getVenta());
            ps.setInt(3, dventa.getCantidad());
            ps.setString(4, dventa.getMoneda());
            ps.setDouble(5, dventa.getT_cambio());
            ps.setDouble(6, dventa.getIgv());
            ps.setDouble(7, dventa.getDescuento());
            ps.setDouble(8, dventa.getPrecio());
            ps.setDouble(9, dventa.getTotal());
            ps.setString(10, dventa.getFecha().toString());
            ps.setString(11, dventa.getMet_pago());
            ps.setString(12, dventa.getReferencia());
            ps.setString(13, dventa.getNota());
            ps.setInt(14, dventa.getId());
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                mensaje = "No se pudo actualizar";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    /**
     * @return retorna un mensaje
     */
    @Override
    public String getMessage() {
        return mensaje;
    }

}
