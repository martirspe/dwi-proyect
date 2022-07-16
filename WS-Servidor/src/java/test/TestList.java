package test;

import dao.impl.DaoProductoImpl;
import dto.ProductoDTO;
import java.util.List;
import dao.DaoProducto;

public class TestList {

    public static void main(String[] args) {
        DaoProducto prod = new DaoProductoImpl();
        List<ProductoDTO> list = prod.productoSel();
        if (list != null) {
            try {
                list.forEach((p) -> {
                    System.out.println(
                            p.getId() + " "
                            + p.getCodigo() + " "
                            + p.getNombre() + " "
                            + p.getModelo() + " "
                            + p.getMarca() + " "
                            + p.getCategoria() + " "
                            + p.getStock() + " "
                            + p.getPrecio() + " "
                            + p.getDescripcion() + " ");
                });
            } catch (Exception e) {
                System.out.println(prod.getMessage());
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Lista vacía");
        }
    }

}
