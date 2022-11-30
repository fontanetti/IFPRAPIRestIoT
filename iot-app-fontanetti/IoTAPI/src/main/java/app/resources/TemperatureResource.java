package app.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import app.models.MyError;
import app.models.Temperature;
import app.repository.TemperatureRepository;

@Path("temperaturas")
public class TemperatureResource {
	
	TemperatureRepository repository;
	
	
	public TemperatureResource() {
		repository = new TemperatureRepository();
	}
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll(@QueryParam("limit") String limite) {
		
		List<Temperature> temperatures = null;
		
		if (limite!=null) {
			temperatures = repository.getByTimeInterval(limite);
		}else {
			temperatures = repository.findAll();
		}
		return Response.status(Response.Status.OK).entity(temperatures).build();
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response create(Temperature temperature) throws URISyntaxException {
		Temperature t = repository.register(temperature);
		
		return Response.created(new URI("/temperaturas/" + t.getId())).entity(t).build();
	}
	
	public void findById() {
		
	}
	
	@DELETE
	@Path("{id}")
	public Response apaga(@PathParam("id") int id) {
		if (repository.deleteById(id) != null) {
			return Response.ok().build();
		}
		else {
			return Response.notModified().build();
		}
		
	}

	
}
