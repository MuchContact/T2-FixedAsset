package tw.mapper;

import org.apache.ibatis.annotations.Param;
import tw.domain.FixedAsset;

public interface FixedAssetMapper {
    FixedAsset findFixedAssetById(@Param("assetid") int assetid);

    int addFixedAsset(@Param("fixedAsset") FixedAsset fixedAsset);
}
