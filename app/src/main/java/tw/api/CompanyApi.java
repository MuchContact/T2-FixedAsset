package tw.api;

import tw.domain.Policy;
import tw.domain.json.PolicyRefJson;
import tw.mapper.PolicyMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class CompanyApi {
    @Path("/policies")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewPolicy(@Context UriInfo uri,
                                    @FormParam("title") String title,
                                    @FormParam("formula") String formula,
                                    @FormParam("type") String type,
                                    @FormParam("category") String category){
        Policy policy = new Policy(title, formula, type, category);
        PolicyRefJson policyRefJson = new PolicyRefJson(policy, uri);
        return Response.status(201).entity(policyRefJson).build();
    }

    @Path("/policies/{pid}")
    public PolicyApi getPolicy(@PathParam("pid") int policyId, @BeanParam PolicyMapper policyMapper){
        Policy policy = policyMapper.getPolicyById(policyId);
        return new PolicyApi(policy);
    }
}
