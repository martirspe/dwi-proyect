package api.Rest.service;

import dao.DaoCategoria;
import dao.impl.DaoCategoriaImpl;
import entities.Categoria;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/categoria")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class CategoriaRest {

    @GET
    public Response categoriaSel() {
        DaoCategoria dao = new DaoCategoriaImpl();
        List<Categoria> lista = dao.categoriaSel();
        if (lista == null) {
            String msg = "No hay categoría";
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(msg).build();
        } else {
            GenericEntity<List<Categoria>> entity = new GenericEntity<List<Categoria>>(lista) {
            };
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(entity).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response categoriaGet(@PathParam("id") Integer id) {
        DaoCategoria dao = new DaoCategoriaImpl();
        Categoria cat = dao.categoriaGet(id);
        if (cat == null) {
            String msg = "No hay categoría";
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(msg).build();
        } else {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(cat).build();
        }
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response categoriaIns(Categoria cat) {
        DaoCategoria dao = new DaoCategoriaImpl();
        String msg = dao.categoriaIns(cat);
        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .entity(msg).build();
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response categoriaUpd(Categoria categoria) {
        DaoCategoria dao = new DaoCategoriaImpl();
        String msg = dao.categoriaUpd(categoria);
        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .entity(msg).build();
    }

    @DELETE
    @Path("/{ids}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response categoriaDel(@PathParam("ids") String ids) {
        List<Integer> list = Arrays.stream(ids.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        DaoCategoria dao = new DaoCategoriaImpl();
        String msg = dao.categoriaDel(list);
        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .entity(msg).build();
    }

}
