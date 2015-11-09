package tw.mapper;

import tw.domain.Depreciation;
import tw.domain.FixedAsset;
import tw.domain.NetWorth;

public interface NetWorthMapper {
    int createNewNetWorth(NetWorth netWorth, FixedAsset fixedAssetById, Depreciation depreciationById);
}
