package com.example.demofunclab;

public class Product {
    private String name;
    private String description;
    private String date;
    private String time;
    private String facilities;
    private int price;
    private int iconResId;

    public Product(String name, String description, String date, String time, String facilities, int price, int iconResId) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.facilities = facilities;
        this.price = price;
        this.iconResId = iconResId;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getFacilities() { return facilities; }
    public int getPrice() { return price; }
    public int getIconResId() { return iconResId; }
}

