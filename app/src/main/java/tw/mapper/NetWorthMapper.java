package tw.mapper;

import org.apache.ibatis.annotations.Param;
import tw.domain.Depreciation;
import tw.domain.FixedAsset;
import tw.domain.NetWorth;

public interface NetWorthMapper {
    int createNewNetWorth(@Param("netWorth") NetWorth netWorth,
                          @Param("fixedAssetById") FixedAsset fixedAssetById,
                          @Param("depreciationById") Depreciation depreciationById);

    NetWorth findNetWorthById(@Param("id") int id);
}
