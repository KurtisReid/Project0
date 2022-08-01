package dev.reid.entity;

public class Expense {
    private int id;
    private double expenseCost;

    private String status;

    private int employeeIssuer;

    public Expense(int id, double expenseCost, String status) {
        this.id = id;
        this.expenseCost = expenseCost;
        this.status = status;
    }
}
