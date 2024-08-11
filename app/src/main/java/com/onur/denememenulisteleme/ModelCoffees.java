package com.onur.denememenulisteleme;

import android.annotation.SuppressLint;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ModelCoffees {
    private String id,name,imageUrl;
    private double price;
    private Map<String, Double> sizes;
    private Map<String, Double> extras;
    private String selectedSize = "Small";
    private Set<String> selectedExtras = new HashSet<>();

    public ModelCoffees() {
    }

    public ModelCoffees(String id, String name, String imageUrl, double price, Map<String, Double> sizes, Map<String, Double> extras, String selectedSize, Set<String> selectedExtras) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.sizes = sizes;
        this.extras = extras;
        this.selectedSize = selectedSize;
        this.selectedExtras = selectedExtras;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Map<String, Double> getSizes() {
        return sizes;
    }

    public void setSizes(Map<String, Double> sizes) {
        this.sizes = sizes;
    }

    public Map<String, Double> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, Double> extras) {
        this.extras = extras;
    }

    public String getSelectedSize() {
        return selectedSize;
    }

    public void setSelectedSize(String selectedSize) {
        this.selectedSize = selectedSize;
    }

    public Set<String> getSelectedExtras() {
        return selectedExtras;
    }

    public void setSelectedExtras(Set<String> selectedExtras) {
        this.selectedExtras = selectedExtras;
    }
}
