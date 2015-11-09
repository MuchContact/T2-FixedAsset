package tw.core;

public class NetWorthChange {
    private final double netWorthAmountChange;
    private final NetWorth netWorth;
    private double amount;

    public NetWorthChange(double netWorthAmountChange, NetWorth netWorth) {

        this.netWorthAmountChange = netWorthAmountChange;
        this.netWorth = netWorth;
    }

    public NetWorth getCorrepondingNetWorth() {
        return netWorth;
    }

    public double getAmount() {
        return netWorthAmountChange;
    }
}
