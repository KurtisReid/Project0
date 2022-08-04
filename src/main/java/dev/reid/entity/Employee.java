package dev.reid.entity;

public class Employee {
    private int id;
    private String name;


    public Employee(int id, String name) {
        this.id = id;
        name = name;
    }

    public Employee() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                '}';
    }
}
