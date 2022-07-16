package dao.impl;

import util.ConexionDB;
import entities.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.DaoProducto;
import dto.ProductoDTO;

/**
 * Se implementa DaoProductoImpl con el DaoProducto para obtener los métodos del
 * DAO.
 */
public class DaoProductoImpl implements DaoProducto {

    private final ConexionDB conexiondb;
    private String mensaje;

    public DaoProductoImpl() {
        this.conexiondb = new ConexionDB();
    }

    /**
     * @return retorna una lista de productos
     */
    @Override
    public List<ProductoDTO> productoSel() {
        List<ProductoDTO> list = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id,")
                .append("codigo,")
                .append("nombre,")
                .append("modelo,")
                .append("marca,")
                .append("categoria,")
                .append("cantidad,")
                .append("precio,")
                .append("descripcion")
                .append(" FROM producto_view");
        try ( Connection cn = conexiondb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                ProductoDTO prod = new ProductoDTO();
                prod.setId(rs.getInt(1));
                prod.setCodigo(rs.getString(2));
                prod.setNombre(rs.getString(3));
                prod.setModelo(rs.getString(4));
                prod.setMarca(rs.getString(5));
                prod.setCategoria(rs.getString(6));
                prod.setStock(rs.getInt(7));
                prod.setPrecio(rs.getDouble(8));
                prod.setDescripcion(rs.getString(9));
                list.add(prod);
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return list;
    }

    /**
     * @param id se recibe como parámetro para obtener más información de un
     * producto, según el id.
     * @return retorna una lista de productos.
     */
    @Override
    public Producto productoGet(Integer id) {
        Producto prod = new Producto();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id,")
                .append("codigo,")
                .append("nombre,")
                .append("modelo,")
                .append("marca,")
                .append("categoria,")
                .append("cantidad,")
                .append("precio,")
                .append("descripcion")
                .append(" FROM productos WHERE id = ?");
        try ( Connection cn = conexiondb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    prod.setId(rs.getInt(1));
                    prod.setCodigo(rs.getString(2));
                    prod.setNombre(rs.getString(3));
                    prod.setModelo(rs.getString(4));
                    prod.setMarca(rs.getString(5));
                    prod.setCategoria(rs.getInt(6));
                    prod.setStock(rs.getInt(7));
                    prod.setPrecio(rs.getDouble(8));
                    prod.setDescripcion(rs.getString(9));
                } else {
                    prod = null;
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return prod;
    }

    /**
     * @param id se recibe como parámetro para obtener más información de un
     * producto, según el id.
     * @return retorna una lista de productos.
     */
    @Override
    public ProductoDTO productoDat(Integer id) {
        ProductoDTO prod = new ProductoDTO();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id,")
                .append("codigo,")
                .append("nombre,")
                .append("modelo,")
                .append("marca,")
                .append("categoria,")
                .append("cantidad,")
                .append("precio,")
                .append("descripcion")
                .append(" FROM producto_view WHERE id = ?");
        try ( Connection cn = conexiondb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    prod.setId(rs.getInt(1));
                    prod.setCodigo(rs.getString(2));
                    prod.setNombre(rs.getString(3));
                    prod.setModelo(rs.getString(4));
                    prod.setMarca(rs.getString(5));
                    prod.setCategoria(rs.getString(6));
                    prod.setStock(rs.getInt(7));
                    prod.setPrecio(rs.getDouble(8));
                    prod.setDescripcion(rs.getString(9));
                } else {
                    prod = null;
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return prod;
    }

    /**
     * @param prod se recibe el objeto productos para insertar la información en
     * la DB.
     * @return retorna un mensaje
     */
    @Override
    public String productoIns(Producto prod) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO productos( ")
                .append("codigo,")
                .append("nombre,")
                .append("modelo,")
                .append("marca,")
                .append("categoria,")
                .append("cantidad,")
                .append("precio,")
                .append("descripcion")
                .append(") VALUES (?,?,?,?,?,?,?,?) ");
        try ( Connection cn = conexiondb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, prod.getCodigo());
            ps.setString(2, prod.getNombre());
            ps.setString(3, prod.getModelo());
            ps.setString(4, prod.getMarca());
            ps.setInt(5, prod.getCategoria());
            ps.setInt(6, prod.getStock());
            ps.setDouble(7, prod.getPrecio());
            ps.setString(8, prod.getDescripcion());
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
     * @param prod se recibe el objeto productos para actualizar la información
     * en la DB.
     * @return retorna un mensaje
     */
    @Override
    public String productoUpd(Producto prod) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE productos SET ")
                .append("codigo = ?,")
                .append("nombre = ?,")
                .append("modelo = ?,")
                .append("marca = ?,")
                .append("categoria = ?,")
                .append("cantidad = ?,")
                .append("precio = ?,")
                .append("descripcion = ?")
                .append("WHERE id = ? ");
        try ( Connection cn = conexiondb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, prod.getCodigo());
            ps.setString(2, prod.getNombre());
            ps.setString(3, prod.getModelo());
            ps.setString(4, prod.getMarca());
            ps.setInt(5, prod.getCategoria());
            ps.setInt(6, prod.getStock());
            ps.setDouble(7, prod.getPrecio());
            ps.setString(8, prod.getDescripcion());
            ps.setInt(9, prod.getId());
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                mensaje = "No se logró actualizar";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    /**
     * @param ids se recibe los ids de los productos a eliminar de la DB.
     * @return retorna un mensaje
     */
    @Override
    public String productoDel(List<Integer> ids) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM productos WHERE ")
                .append("id = ? ");
        try ( Connection cn = conexiondb.conexionDB()) {
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
        return mensaje + sql.toString();
    }

    /**
     * @return retorna un mensaje
     */
    @Override
    public String getMessage() {
        return mensaje;
    }
}
