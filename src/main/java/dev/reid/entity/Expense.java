package dev.reid.entity;

public class Expense {
    private int id;
    private double expenseCost;

    private String desc;

    private Type type;//make an enum
    private Status status;

    private int employeeIssuer;

    public Expense(int id, double expenseCost, Status status, int employeeIssuer) {
        this.id = id;
        this.expenseCost = expenseCost;
        this.status = status;
        this.employeeIssuer = employeeIssuer;
    }
    /*
    {
        "id":1,
        "expenseCost":2.0,
        "status":"PENDING",
        "employerIssue":1
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getExpenseCost() {
        return expenseCost;
    }

    public void setExpenseCost(double expenseCost) {
        this.expenseCost = expenseCost;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getEmployeeIssuer() {
        return employeeIssuer;
    }

    public void setEmployeeIssuer(int employeeIssuer) {
        this.employeeIssuer = employeeIssuer;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", expenseCost=" + expenseCost +
                ", status='" + status + '\'' +
                ", employeeIssuer=" + employeeIssuer +
                '}';
    }
}
