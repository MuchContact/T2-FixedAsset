package tw.mapper;

import org.junit.Test;
import tw.domain.FixedAsset;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FixedAssetMapperTest extends BaseMapperTest {
    @Test
    public void should_create_fixed_asset() throws Exception {
        FixedAssetMapper mapper = sqlSession.getMapper(FixedAssetMapper.class);
        FixedAsset fixedAsset = new FixedAsset("FA-1234566", 15000d, "10 year", "2015-10-1");
        int affectRows = mapper.addFixedAsset(fixedAsset);
        assertThat(affectRows, is(1));
    }

    @Test
    public void should_query_fixed_asset_by_id() throws Exception {
        FixedAssetMapper mapper = sqlSession.getMapper(FixedAssetMapper.class);
        FixedAsset fixedAsset = new FixedAsset("FA-1234566", 15000d, "10 year", "2015-10-1");
        mapper.addFixedAsset(fixedAsset);
        FixedAsset fixedAssetById = mapper.findFixedAssetById(fixedAsset.getId());
        assertThat(fixedAssetById.getUniqueNumber(), is("FA-1234566"));
    }
}
