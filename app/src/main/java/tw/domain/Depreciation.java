package tw.domain;

public class Depreciation {
    private  int id;
    private String accountDay;
    private String policy;

    public Depreciation(Integer id, String accountDay, String policy) {
        this.id = id;
        this.accountDay = accountDay;
        this.policy = policy;
    }

    public Depreciation(String accountDay, String policy) {

        this.accountDay = accountDay;
        this.policy = policy;
    }

    public int getId() {
        return id;
    }

    public String getAccountDay() {
        return accountDay;
    }

    public String getPolicy() {
        return policy;
    }
}
