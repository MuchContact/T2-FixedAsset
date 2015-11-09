package tw.api;


import tw.domain.Depreciation;
import tw.domain.FixedAsset;
import tw.mapper.DepreciationMapper;
import tw.mapper.FixedAssetMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.Map;

public class FixedAssetsApi {

    @Path("/{id}/depreciations")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepreciations(@Context UriInfo uri,
                                     @PathParam("id") int id,
                                     @FormParam("accountDay") String accountDay,
                                     @FormParam("policy") String policy,
                                     @BeanParam DepreciationMapper mapper){
        Depreciation depreciation = new Depreciation(accountDay, policy);
        mapper.addDepreciation(depreciation);
        Map<String, Object> map = new HashMap<>();
        map.put("id", depreciation.getId());
        map.put("URI", uri.getAbsolutePath()+"/"+depreciation.getId());
        map.put("forwardURI", uri.getAbsolutePath()+"/"+depreciation.getId()+"/net accounting request");
        return Response.status(302).entity(map).build();
    }

    @Path("/{id}/depreciations/{depre-id}")
    public DepreciationsApi getDepreciationsApi(@PathParam("id") int assetid,
                                                @PathParam("depre-id") int depreId,
                                                @BeanParam FixedAssetMapper assetMapper,
                                                @BeanParam DepreciationMapper depreMapper){
        FixedAsset fixedAssetById = assetMapper.findFixedAssetById(assetid);
        Depreciation depreciationById = depreMapper.findDepreciationById(depreId);
        return new DepreciationsApi(fixedAssetById, depreciationById);
    }


}
