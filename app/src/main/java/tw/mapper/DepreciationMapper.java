package tw.mapper;

import org.apache.ibatis.annotations.Param;
import tw.domain.Depreciation;

public interface DepreciationMapper {
    Depreciation findDepreciationById(@Param("depreId") int depreId);

    int addDepreciation(@Param("depreciation") Depreciation depreciation);
}
