package tw.core;

import org.junit.Test;
import tw.core.request.NetAccountRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NetWorthChangeTest {
    @Test
    public void should_net_worth_changes_size_equals_with_net_worth_size() throws Exception {

        FixedAsset fixedAsset = new FixedAsset(1000, "2015-10-1", mock(Policy.class));
        assertThat(fixedAsset.geNetWorths().size(), is(fixedAsset.getNetWorthChanges().size()));
    }

    @Test
    public void should_net_worth_change_corresponds_to_a_net_worth() throws Exception {
        FixedAsset fixedAsset = new FixedAsset(1000, "2015-10-1", mock(Policy.class));
        assertThat(fixedAsset.geNetWorths().get(0), is(fixedAsset.getNetWorthChanges().get(0).getCorrepondingNetWorth()));

    }

    @Test
    public void should_generate_a_new_net_worth_change_for_a_new_net_worth() throws Exception {
        Policy policy = mock(Policy.class);
        FixedAsset fixedAsset = new FixedAsset(1000, "2015-10-1", policy);
        when(policy.getDecreasedAmount(any(), eq("2015-10-11"))).thenReturn(10d);

        Depreciation depreciation = new Depreciation("2015-10-11", policy, fixedAsset);
        NetAccountRequest netAccountRequest = new NetAccountRequest(fixedAsset, depreciation);
        netAccountRequest.generateNetWorth();
        NetWorthChange netWorthChange = fixedAsset.getNetWorthChanges().get(fixedAsset.getNetWorthChanges().size() - 1);
        assertThat(netWorthChange.getAmount(), is(-10d));

    }

    @Test
    public void should_generate_a_new_net_worth_change_from_net_worth() throws Exception {
        Policy policy = mock(Policy.class);
        FixedAsset fixedAsset = new FixedAsset(1000, "2015-10-1", policy);
        NetWorthChange netWorthChange = fixedAsset.generateNetWorthChangeFor(fixedAsset.getLatestNetWorth());
        assertThat(netWorthChange.getCorrepondingNetWorth(), is(fixedAsset.getLatestNetWorth()));
    }

    @Test
    public void should_get_an_existing_net_change_when_generate_net_worth_change() throws Exception {
        Policy policy = mock(Policy.class);
        FixedAsset fixedAsset = new FixedAsset(1000, "2015-10-1", policy);
        int originalSize = fixedAsset.getNetWorthChanges().size();
        NetWorthChange netWorthChange = fixedAsset.generateNetWorthChangeFor(fixedAsset.getLatestNetWorth());
        assertThat(netWorthChange.getCorrepondingNetWorth(), is(fixedAsset.getLatestNetWorth()));
        assertThat(fixedAsset.getNetWorthChanges().size(), is(originalSize));
    }
}
