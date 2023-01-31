package com.sunk.datastructure.chapter09;

/**
 * @author sunk
 * @since 2023/1/31
 **/
public class Emp {

    int id;

    String name;

    Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
