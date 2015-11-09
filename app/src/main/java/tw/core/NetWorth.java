package tw.core;

public class NetWorth {
    private double amount;
    private String liquidationDate;
    private NetWorthChange netWorthChang;

    public NetWorth(double amount, String liquidationDate) {
        this.amount = amount;
        this.liquidationDate = liquidationDate;
    }

    public double getAmount() {
        return amount;
    }

    public NetWorthChange getNetWorthChang() {
        return netWorthChang;
    }

    public NetWorthChange generateNetWorthChange() {
        return null;
    }
}
