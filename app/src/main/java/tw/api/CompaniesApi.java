package tw.api;

import tw.mapper.DepreciationMapper;
import tw.mapper.FixedAssetMapper;
import tw.mapper.NetWorthMapper;
import tw.mapper.PolicyMapper;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/companies")
public class CompaniesApi {

    @Inject
    PolicyMapper policyMapper;

    @Inject
    FixedAssetMapper assetMapper;

    @Inject
    DepreciationMapper depreciationMapper;

    @Inject
    NetWorthMapper netWorthMapper;

    @Path("/{c-id}")
    public CompanyApi getCompany(@PathParam("c-id") int companyId){

        return new CompanyApi();
    }
}
