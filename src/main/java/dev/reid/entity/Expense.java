package dev.reid.entity;

public class Expense {
    private int id;
    private double expenseCost;

    private String desc;

    private Type type;//make an enum
    private Status status;

    private int employeeIssuer;

    public Expense(int id, double expenseCost, Status status, int employeeIssuer, String desc, Type type) {
        this.id = id;
        this.expenseCost = expenseCost;
        this.status = status;
        this.employeeIssuer = employeeIssuer;
        this.desc = desc;
        this.type = type;
    }

    public Expense() {

    }
    /*
    {
        "id":1,
        "expenseCost":2.0,
        "status":"PENDING",
        "employerIssue":1,
        "desc":"Gas",
        "type":"TRAVEL"
    }*/

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Type getType() {
        System.out.println("get type:" + type);
        return type;
    }

    public void setType(String type) {

        if (type.toUpperCase() == Type.FOOD.name())
        {
            this.type = Type.FOOD;

        }
        else if (type.toUpperCase() == Type.LODGING.name())
        {
            this.type = Type.LODGING;

        }
        else if (type.toUpperCase() == Type.TRAVEL.name())
        {
            this.type = Type.TRAVEL;

        }
        else
        {
            this.type = Type.OTHER;
        }


    }

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

    public void setStatus(String status) {


        Status s = Status.valueOf(status);
        //System.out.println(status);
        if (s == Status.APPROVED)
        {
            System.out.println("this.status = Status.APPROVED;");
            this.status = Status.APPROVED;

        }
        else if (s == Status.PENDING)
        {
            System.out.println("this.status = Status.PENDING;");
            this.status = Status.PENDING;

        }
        else if (s == Status.DENIED)
        {
            System.out.println("this.status = Status.DENIED;");
            this.status = Status.DENIED;
        }
        else {
            System.out.println("Cant read status");
            this.status = Status.PENDING;
        }


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
