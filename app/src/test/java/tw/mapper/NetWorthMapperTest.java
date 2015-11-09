package tw.mapper;

import org.junit.Test;
import tw.domain.Depreciation;
import tw.domain.FixedAsset;
import tw.domain.NetWorth;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NetWorthMapperTest extends BaseMapperTest {
    @Test
    public void should_create_net_worth() throws Exception {
        NetWorthMapper mapper = sqlSession.getMapper(NetWorthMapper.class);
        NetWorth netWorth = new NetWorth(12000d, "compaies/1/policies/1", "2015-11-30", "Wang Xiang");
        FixedAsset asset = mock(FixedAsset.class);
        when(asset.getId()).thenReturn(1);
        Depreciation depreciation = mock(Depreciation.class);
        when(depreciation.getId()).thenReturn(1);
        int affectRows = mapper.createNewNetWorth(netWorth, asset, depreciation);
        assertThat(affectRows, is(1));

    }

    @Test
    public void should_get_net_worth_and_linked_fixed_asset_and_depreciation() throws Exception {
        NetWorthMapper mapper = sqlSession.getMapper(NetWorthMapper.class);
        NetWorth netWorth = new NetWorth(12000d, "compaies/1/policies/1", "2015-11-30", "Wang Xiang");
        FixedAsset asset = mock(FixedAsset.class);
        when(asset.getId()).thenReturn(1);
        Depreciation depreciation = mock(Depreciation.class);
        when(depreciation.getId()).thenReturn(1);
        mapper.createNewNetWorth(netWorth, asset, depreciation);

        NetWorth netWorthById = mapper.findNetWorthById(netWorth.getId());
        assertThat(netWorthById.getAmount(), is(12000d));
        assertThat(netWorthById.getAccountant(), is("Wang Xiang"));
        assertThat(netWorthById.getFixedAssetId(), is(1));
        assertThat(netWorthById.getDepreciationId(), is(1));

    }
}
