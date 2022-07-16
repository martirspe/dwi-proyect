package dao;

import entities.Categoria;
import java.util.List;

public interface DaoCategoria {

    List<Categoria> categoriaSel();
    Categoria categoriaGet(Integer id);
    public String categoriaIns(Categoria cat);
    public String categoriaUpd(Categoria cat);
    public String categoriaDel(List<Integer> ids);
    
}
