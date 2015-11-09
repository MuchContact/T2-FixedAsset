package tw.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import tw.core.request.NetAccountRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NetWorthTest {

    @org.junit.Test
    public void should_get_a_new_net_worth_for_depreciation() throws Exception {
        FixedAsset fixedAsset = new FixedAsset(1000, "2015-10-1", mock(Policy.class));
        Depreciation depreciation = new Depreciation("2015-10-1", mock(Policy.class), mock(FixedAsset.class));
        NetAccountRequest netAccountRequest = new NetAccountRequest(fixedAsset, depreciation);
        NetWorth netWorth = netAccountRequest.generateNetWorth();
        assertThat(fixedAsset.getLatestNetWorth(), is(netWorth));
        assertThat(netWorth.getAmount(), is(1000d));
    }

    @Test
    public void should_gain_a_new_net_worth_after_a_new_fixed_asset() throws Exception {
        FixedAsset fixedAsset = new FixedAsset(1000, "2015-10-1", mock(Policy.class));
        NetAccountRequest netAccountRequest = new NetAccountRequest(fixedAsset);
        NetWorth netWorth = netAccountRequest.generateNetWorth();
        assertThat(fixedAsset.getLatestNetWorth(), is(netWorth));
        assertThat(netWorth.getAmount(), is(1000d));

    }

    @Test
    public void should_decrease_10_after_10_days_for_depreciation_with_a_policy_which_decrease_1_per_day() throws Exception {
        Policy policy = mock(Policy.class);
        FixedAsset fixedAsset = new FixedAsset(1000, "2015-10-1", policy);
        when(policy.getDecreasedAmount(any(), eq("2015-10-11"))).thenReturn(10d);

        Depreciation depreciation = new Depreciation("2015-10-11", policy, fixedAsset);
        NetAccountRequest netAccountRequest = new NetAccountRequest(fixedAsset, depreciation);
        NetWorth netWorth = netAccountRequest.generateNetWorth();
        assertThat(netWorth.getAmount(), is(990d));
    }

    @Test
    public void should_fixed_asset_increase_when_repairing_happens_without_considering_depreciation() throws Exception {
        FixedAsset fixedAsset = new FixedAsset(1000, "2015-10-1", mock(Policy.class));
        Repairment repairment = new Repairment("2015-10-1", 300);

        NetAccountRequest netAccountRequest = new NetAccountRequest(fixedAsset, repairment);

        NetWorth netWorth = netAccountRequest.generateNetWorth();

        assertThat(netWorth.getAmount(), is(1300d));

    }

    @Test
    public void should_consider_depreciation_when_repairment_happens() throws Exception {
        Policy policy = mock(Policy.class);
        when(policy.getDecreasedAmount(anyObject(), eq("2015-10-11"))).thenReturn(100d);

        FixedAsset fixedAsset = new FixedAsset(1000, "2015-10-1", policy);
        Repairment repairment = new Repairment("2015-10-11", 300);

        NetAccountRequest netAccountRequest = new NetAccountRequest(fixedAsset, repairment);

        NetWorth netWorth = netAccountRequest.generateNetWorth();

        assertThat(netWorth.getAmount(), is(1200d));

    }
}
