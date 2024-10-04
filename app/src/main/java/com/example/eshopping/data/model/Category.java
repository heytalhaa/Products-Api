package com.example.eshopping.data.model;

public class Category {
    private String image;
    private String creationAt;
    private String name;
    private long id;
    private String updatedAt;

    public String getImage() { return image; }
    public void setImage(String value) { this.image = value; }

    public String getCreationAt() { return creationAt; }
    public void setCreationAt(String value) { this.creationAt = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public long getid() { return id; }
    public void setid(long value) { this.id = value; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String value) { this.updatedAt = value; }
}
