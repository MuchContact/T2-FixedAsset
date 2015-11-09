package tw.domain;

public class Policy {
    private String title;
    private String formula;
    private String type;
    private String category;
    private int id;

    public Policy(String title, String formula, String type, String category) {
        this.title = title;
        this.formula = formula;
        this.type = type;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getFormula() {
        return formula;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }
}
