package tw.core;

import java.util.ArrayList;
import java.util.List;

public class FixedAsset {
    private final int originalAmount;
    private final String buyDate;
    private final List<NetWorth> netWorths;
    private Policy policy;


    public FixedAsset(int originalAmount, String buyDate, Policy policy) {
        this.policy = policy;
        netWorths = new ArrayList<>();
        netWorths.add(new NetWorth(originalAmount, buyDate));
        this.originalAmount = originalAmount;
        this.buyDate = buyDate;
    }

    public NetWorth getLatestNetWorth() {
        return netWorths.get(netWorths.size()-1);
    }

    public void addNewNetWorth(NetWorth netWorth) {
        netWorths.add(netWorth);
    }

    public Policy getPolicy() {
        return policy;
    }
}
