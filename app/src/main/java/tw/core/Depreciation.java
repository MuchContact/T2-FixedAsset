package tw.core;

public class Depreciation {
    private final String date;
    private final Policy policy;
    private FixedAsset fixedAsset;

    public Depreciation(String date, Policy policy, FixedAsset fixedAsset) {

        this.date = date;
        this.policy = policy;
        this.fixedAsset = fixedAsset;
    }

    public double calculatDecreasedAmount() {
        return policy.getDecreasedAmount(fixedAsset, "2015-10-11");
    }
}
