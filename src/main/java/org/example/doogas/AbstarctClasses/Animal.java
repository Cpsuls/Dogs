package org.example.doogas.AbstarctClasses;



public abstract class Animal {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    public abstract String woof();


}
