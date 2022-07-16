package dao.impl;

import entities.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConexionDB;
import dao.DaoCategoria;

public class DaoCategoriaImpl implements DaoCategoria {

    private final ConexionDB ConexionDB;
    private String mensaje;

    public DaoCategoriaImpl() {
        this.ConexionDB = new ConexionDB();
    }

    @Override
    public List<Categoria> categoriaSel() {
        List<Categoria> list = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id,")
                .append("nombre,")
                .append("descripcion")
                .append(" FROM categorias");
        try ( Connection cn = ConexionDB.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setId(rs.getInt(1));
                cat.setNombre(rs.getString(2));
                cat.setDescripcion(rs.getString(3));
                list.add(cat);
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return list;
    }

    @Override
    public Categoria categoriaGet(Integer id) {
        Categoria cat = new Categoria();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id,")
                .append("nombre,")
                .append("descripcion")
                .append(" FROM categorias WHERE id = ?");
        try ( Connection cn = ConexionDB.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cat.setId(rs.getInt(1));
                    cat.setNombre(rs.getString(2));
                    cat.setDescripcion(rs.getString(3));
                } else {
                    cat = null;
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return cat;
    }

    @Override
    public String categoriaIns(Categoria cat) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO categorias( ")
                .append("nombre,")
                .append("descripcion")
                .append(") VALUES (?,?)");
        try ( Connection cn = ConexionDB.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, cat.getNombre());
            ps.setString(2, cat.getDescripcion());
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                mensaje = "No se insertaron registros";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    @Override
    public String categoriaUpd(Categoria cat) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE categorias SET ")
                .append("nombre = ?,")
                .append("descripcion = ?")
                .append("WHERE id = ?");
        try ( Connection cn = ConexionDB.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, cat.getNombre());
            ps.setString(2, cat.getDescripcion());
            ps.setInt(3, cat.getId());
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                mensaje = "No se pudo actualizar";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    @Override
    public String categoriaDel(List<Integer> ids) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM categorias WHERE ")
                .append("id = ?");
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
}
