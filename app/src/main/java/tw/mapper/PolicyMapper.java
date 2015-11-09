package tw.mapper;

import org.apache.ibatis.annotations.Param;
import tw.domain.Policy;

public interface PolicyMapper {
    Policy getPolicyById(@Param("id") int id);

    int addNewPolicy(@Param("policy") Policy policy);
}
