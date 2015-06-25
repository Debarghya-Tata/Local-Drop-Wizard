package eu.kielczewski.example.hello;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eu.kielczewski.example.core.PackageItem;
import eu.kielczewski.example.dao.PackageItemDAO;


@Path("/person")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class PackageItemResource {

	PackageItemDAO packageItemDAO;

    public PackageItemResource(PackageItemDAO packageItemDAO) {
        this.packageItemDAO = packageItemDAO;
    }
    
    @GET
    @Path("/{id}")
    public PackageItem get(@PathParam("id") Integer id){
        return packageItemDAO.findById(id);
    }
    
    @POST
    public boolean add(@Valid PackageItem packageItem) {
        int newId = packageItemDAO.insert(packageItem);

        return true;
    }
}
