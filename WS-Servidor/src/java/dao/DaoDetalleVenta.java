package dao;

import entities.DetalleVenta;
import java.util.List;

public interface DaoDetalleVenta {

    List<DetalleVenta> dventaSel();

    List<DetalleVenta> dventaCbo();

    DetalleVenta dventaGet(Integer id);

    String dventaIns(DetalleVenta dventa);

    String dventaUpd(DetalleVenta dventa);

    String dventaDel(List<Integer> ids);

    String getMessage();
}
