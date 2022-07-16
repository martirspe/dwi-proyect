package dao;

import dto.ProductoDTO;
import java.util.List;
import entities.Producto;

public interface DaoProducto {

    public List<ProductoDTO> productoSel();

    public ProductoDTO productoDat(Integer id);

    public Producto productoGet(Integer id);

    public String productoIns(Producto prod);

    public String productoUpd(Producto prod);

    public String productoDel(List<Integer> ids);

    public String getMessage();
}
