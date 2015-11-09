package tw.core;

public class Repairment {
    private final String occureDate;
    private final int amount;

    public Repairment(String occureDate, int amount) {
        this.occureDate = occureDate;
        this.amount = amount;
    }

    public double calculatChangedAmount() {
        return amount;
    }

    public String getRepairDate() {
        return occureDate;
    }
}
