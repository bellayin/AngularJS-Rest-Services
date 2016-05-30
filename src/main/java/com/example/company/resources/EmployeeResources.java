package com.example.company.resources;

import com.example.company.db.MongoDBApplication;
import com.example.company.model.Employee;
import org.bson.Document;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Bella Galoyan
 */
@ApplicationPath("/company")
@Path("employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResources extends Application {

    private MongoDBApplication application = new MongoDBApplication();

    @GET
    public List<Document> listEmployees() {
      return application.findAllEmployees();
    }


    @POST
    public Employee saveEmployee(Employee employee) {
        if (employee.getId() == null) {
           application.createNewEmployee(employee);

        } else {
            application.updateEmployeeDocument(employee);
        }
        return employee;
    }

    @GET
    @Path("{id}")
    public Document getPerson(@PathParam("id") Long id) {

        return application.findEmployeeById(id);
    }
    @DELETE
    @Path("{id}")
    public void deletePerson(@PathParam("id") Long id) {
         application.deleteEmployeeDocument(id);

    }
}
