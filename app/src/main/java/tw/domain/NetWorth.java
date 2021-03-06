package tw.domain;

public class NetWorth {
    private int id;
    private Integer depreciationId;
    private double amount;
    private String policy;
    private String accountDay;
    private String accountant;
    private Integer fixedAssetId;

    public NetWorth(){

    }

    public NetWorth(Double amount, String policy, String accountDay, String accountant) {

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

    public Integer getDepreciationId() {
        return depreciationId;
    }

    public Integer getFixedAssetId() {
        return fixedAssetId;
    }
}
