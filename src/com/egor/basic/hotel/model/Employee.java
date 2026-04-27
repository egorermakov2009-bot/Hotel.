package com.egor.basic.hotel.model;

import java.io.Serializable;

public class Employee implements Serializable {

    private int id;
    private String name;
    private Role role;

    public Employee() {}

    public Employee(int id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Id: " + id + " | name: " + name + " | role: " + role;
    }
}
