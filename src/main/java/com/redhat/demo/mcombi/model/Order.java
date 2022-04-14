package com.redhat.demo.mcombi.model;

public class Order {
    public String description;
    public int id;
    public int quantity;

    public Order(){

    }

    public Order (String description,int quantity){
        this.description=description;
        this.quantity=quantity;
    }

}
