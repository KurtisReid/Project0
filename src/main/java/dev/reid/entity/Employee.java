package dev.reid.entity;

public class Employee {
    private int id;
    private String Name;

    public Employee(int id, String name) {
        this.id = id;
        Name = name;
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
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                '}';
    }
}
