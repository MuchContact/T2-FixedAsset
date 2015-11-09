package tw.api;

import tw.mapper.PolicyMapper;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/companies")
public class CompaniesApi {

    @Inject
    PolicyMapper policyMapper;

    @Path("/{c-id}")
    public CompanyApi getCompany(@PathParam("c-id") int companyId){

        return new CompanyApi();
    }
}
