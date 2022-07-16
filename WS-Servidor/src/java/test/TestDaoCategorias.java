package test;

import dao.impl.DaoCategoriaImpl;
import entities.Categoria;
import dao.DaoCategoria;

public class TestDaoCategorias {

    public static void main(String[] args) {

        DaoCategoria dao = new DaoCategoriaImpl();

        Categoria lc = dao.categoriaGet(1);
        System.out.println("ID: " + lc.getId());
        System.out.println("Nombre: " + lc.getNombre());
        System.out.println("Descripción: " + lc.getDescripcion());
    }

}
