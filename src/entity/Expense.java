package entity;
import java.util.Date;

public class Expense {
    private int id;
    private int userId;
    private String name;
    private double amount;
    private int categoryId;
    private Date date;

    public Expense(int id, int userId, double amount, int categoryId, Date date, String name) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.categoryId = categoryId;
        this.date = date;
        this.name = name;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public double getAmount() { return amount; }
    public int getCategoryId() { return categoryId; }
    public Date getDate() { return date; }
}
