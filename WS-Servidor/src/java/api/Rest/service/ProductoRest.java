package api.Rest.service;

import dao.DaoProducto;
import dao.impl.DaoProductoImpl;
import dto.ProductoDTO;
import entities.Producto;
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

@Path("/producto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ProductoRest {

    @GET
    public Response productoDat() {
        DaoProducto dao = new DaoProductoImpl();
        List<ProductoDTO> lista = dao.productoSel();
        if (lista == null) {
            String msg = "No hay producto";
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(msg).build();
        } else {
            GenericEntity<List<ProductoDTO>> entity = new GenericEntity<List<ProductoDTO>>(lista) {
            };
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(entity).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response productoDat(@PathParam("id") Integer id) {
        DaoProducto dao = new DaoProductoImpl();
        ProductoDTO pro = dao.productoDat(id);
        if (pro == null) {
            String msg = "No hay producto";
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(msg).build();
        } else {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(pro).build();
        }
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response productoIns(Producto pro) {
        DaoProducto dao = new DaoProductoImpl();
        String msg = dao.productoIns(pro);
        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .entity(msg).build();
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response productoUpd(Producto producto) {
        DaoProducto dao = new DaoProductoImpl();
        String msg = dao.productoUpd(producto);
        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .entity(msg).build();
    }

    @DELETE
    @Path("/{ids}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response productoDEL(@PathParam("ids") String ids) {
        List<Integer> list = Arrays.stream(ids.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        DaoProducto dao = new DaoProductoImpl();
        String msg = dao.productoDel(list);
        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .entity(msg).build();
    }

}
