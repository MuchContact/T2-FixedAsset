package tw.api;

import tw.domain.Policy;
import tw.domain.json.PolicyRefJson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class PolicyApi {
    private Policy policy;

    public PolicyApi(Policy policy) {

        this.policy = policy;
    }

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPolicy(@Context UriInfo uri){
        PolicyRefJson policyRefJson = new PolicyRefJson(policy, uri);
        return Response.status(200).entity(policyRefJson).build();
    }

}
