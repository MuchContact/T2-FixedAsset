package tw.mapper;

import org.junit.Test;
import tw.domain.Depreciation;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DepreciationMapperTest extends BaseMapperTest {

    @Test
    public void should_create_depreciation() throws Exception {
        DepreciationMapper mapper = sqlSession.getMapper(DepreciationMapper.class);
        Depreciation depreciation = new Depreciation("2015-10-1", "compaies/1/policies/1");
        int affectRows = mapper.addDepreciation(depreciation);
        assertThat(affectRows, is(1));

    }

    @Test
    public void should_get_depreciation() throws Exception {
        DepreciationMapper mapper = sqlSession.getMapper(DepreciationMapper.class);
        Depreciation depreciation = new Depreciation("2015-10-1", "compaies/1/policies/1");
        mapper.addDepreciation(depreciation);
        Depreciation depreciationById = mapper.findDepreciationById(depreciation.getId());
        assertThat(depreciationById.getAccountDay(), is("2015-10-1"));
    }
}
