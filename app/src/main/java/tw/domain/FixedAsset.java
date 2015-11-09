package tw.domain;

public class FixedAsset {
    private int id;
    private String uniqueNumber;
    private double originalWorth;
    private String lifetime;
    private String startDate;

    public int getId() {
        return id;
    }

    public FixedAsset(String uniqueNumber, double originalWorth, String lifetime, String startDate) {

        this.uniqueNumber = uniqueNumber;
        this.originalWorth = originalWorth;
        this.lifetime = lifetime;
        this.startDate = startDate;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public double getOriginalWorth() {
        return originalWorth;
    }

    public String getLifetime() {
        return lifetime;
    }

    public String getStartDate() {
        return startDate;
    }
}
