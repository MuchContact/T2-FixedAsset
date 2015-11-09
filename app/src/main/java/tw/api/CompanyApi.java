package tw.api;

import tw.domain.FixedAsset;
import tw.domain.Policy;
import tw.domain.json.FixedAssetRefJson;
import tw.domain.json.PolicyRefJson;
import tw.mapper.FixedAssetMapper;
import tw.mapper.PolicyMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.Map;

public class CompanyApi {
    @Path("/policies")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewPolicy(@Context UriInfo uri,
                                    @FormParam("title") String title,
                                    @FormParam("formula") String formula,
                                    @FormParam("type") String type,
                                    @FormParam("category") String category,
                                    @BeanParam PolicyMapper mapper) {
        Policy policy = new Policy(title, formula, type, category);
        mapper.addNewPolicy(policy);
        PolicyRefJson policyRefJson = new PolicyRefJson(policy, uri);
        return Response.status(201).entity(policyRefJson).build();
    }

    @Path("/policies/{pid}")
    public PolicyApi getPolicy(@PathParam("pid") int policyId, @BeanParam PolicyMapper policyMapper) {
        Policy policy = policyMapper.getPolicyById(policyId);
        return new PolicyApi(policy);
    }

    @Path("/fixed assets")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFixedAssets(@Context UriInfo uri,
                                   @FormParam("unique_number") String uniqueNumber,
                                   @FormParam("original worth") double originalWorth,
                                   @FormParam("lifetime") String lifetime,
                                   @FormParam("start date") String startDate,
                                   @BeanParam FixedAssetMapper mapper) {

        FixedAsset fixedAsset = new FixedAsset(uniqueNumber, originalWorth, lifetime, startDate);
        mapper.addFixedAsset(fixedAsset);
        FixedAssetRefJson fixedAssetRefJson = new FixedAssetRefJson(fixedAsset, uri);
        Map<String, Object> map = new HashMap<>();
        map.put("id", fixedAsset.getId());
        map.put("uniqueNumber", fixedAssetRefJson.getUniqueNumber());
        map.put("URI", fixedAssetRefJson.getUri());
        map.put("forwardURI", fixedAssetRefJson.getUri() + "/net accounting request");
        return Response.status(302).entity(map).build();
    }

    @Path("/fixed assets")
    public FixedAssetsApi getFixedAssets() {
        return new FixedAssetsApi();
    }
}
