package tw.api;

import tw.domain.Depreciation;
import tw.domain.FixedAsset;
import tw.domain.NetWorth;
import tw.mapper.NetWorthMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

public class DepreciationsApi {
    private final FixedAsset fixedAssetById;
    private final Depreciation depreciationById;

    public DepreciationsApi(FixedAsset fixedAssetById, Depreciation depreciationById) {

        this.fixedAssetById = fixedAssetById;
        this.depreciationById = depreciationById;
    }

    @Path("/net accounting request")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateNewNetWorth(@BeanParam NetWorthMapper mapper,
                                        @FormParam("amount") double amount,
                                        @FormParam("policy") String policy,
                                        @FormParam("accountDay") String accountDay,
                                        @FormParam("accountant") String accountant){
        NetWorth netWorth = new NetWorth(amount, policy, accountDay, accountant);
        mapper.createNewNetWorth(netWorth, fixedAssetById, depreciationById);
        Map<String, Object> map = new HashMap<>();
        map.put("unique_number", "RE-27483739");
        map.put("id", 1);
        map.put("URI", "compaies/1/fixed assets/1/net worths/1");
        return Response.status(201).entity(map).build();
    }

}
