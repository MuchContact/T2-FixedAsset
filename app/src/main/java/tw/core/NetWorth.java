package tw.core;

public class NetWorth {
    private double amount;
    private String liquidationDate;

    public NetWorth(double amount, String liquidationDate) {
        this.amount = amount;
        this.liquidationDate = liquidationDate;
    }

    public double getAmount() {
        return amount;
    }
}
