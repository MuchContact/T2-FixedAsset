package tw.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FixedAsset {
    private final int originalAmount;
    private final String buyDate;
    private final List<NetWorth> netWorths;
    private Policy policy;
    private final List<NetWorthChange> netWorthChanges;


    public FixedAsset(int originalAmount, String buyDate, Policy policy) {
        this.policy = policy;
        this.originalAmount = originalAmount;
        this.buyDate = buyDate;

        netWorths = new ArrayList<>();
        netWorths.add(new NetWorth(originalAmount, buyDate));

        netWorthChanges = new ArrayList<>();
        netWorthChanges.add(new NetWorthChange(originalAmount, getLatestNetWorth()));

    }

    public NetWorth getLatestNetWorth() {
        return netWorths.get(netWorths.size() - 1);
    }

    public void addNewNetWorth(NetWorth netWorth) {
        NetWorth latestNetWorth = getLatestNetWorth();
        double netWorthAmountChange = netWorth.getAmount() - latestNetWorth.getAmount();
        netWorths.add(netWorth);

        NetWorthChange netWorthChange = new NetWorthChange(netWorthAmountChange, netWorth);
        netWorthChanges.add(netWorthChange);
    }

    public Policy getPolicy() {
        return policy;
    }

    public List<NetWorth> geNetWorths() {
        return netWorths;
    }

    public List<NetWorthChange> getNetWorthChanges() {
        return netWorthChanges;
    }

    public NetWorthChange generateNetWorthChangeFor(NetWorth netWorth) {
        Stream<NetWorthChange> netWorthChangeStream = netWorthChanges
                .stream()
                .filter(netWorthChange -> netWorthChange.getCorrepondingNetWorth().equals(netWorth));
        Optional<NetWorthChange> first = netWorthChangeStream.findFirst();
        if (first.isPresent())
            return first.get();

        int index = netWorths.indexOf(netWorth);
        double amount = 0;
        if (index < 1) {
            amount = netWorth.getAmount();
        } else {
            amount = netWorth.getAmount() - netWorths.get(index - 1).getAmount();
        }
        NetWorthChange netWorthChange = new NetWorthChange(amount, netWorth);
        netWorthChanges.add(netWorthChange);
        return netWorthChange;
    }
}
