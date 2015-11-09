package tw.domain;

public class NetWorth {
    private int id;
    private final double amount;
    private final String policy;
    private final String accountDay;
    private final String accountant;

    public NetWorth(double amount, String policy, String accountDay, String accountant) {

        this.amount = amount;
        this.policy = policy;
        this.accountDay = accountDay;
        this.accountant = accountant;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getPolicy() {
        return policy;
    }

    public String getAccountDay() {
        return accountDay;
    }

    public String getAccountant() {
        return accountant;
    }
}
