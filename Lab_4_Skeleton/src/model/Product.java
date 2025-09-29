/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rushabh
 */
public class Product {
    
    private String name;
    private int price;
    private int id;

    private ArrayList<Feature> features;
    
    private static int count = 0;
    
    public Product() {
        count++;
        id = count;
        
        this.features = new ArrayList<Feature>();
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public Feature addNewFeature() {
        Feature f = new Feature(this);
        features.add(f);
        return f;
    }

    public void removeFeature(int index) {
        if (index >= 0 && index < features.size()) {
            features.remove(index);
        }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
    
    
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    
    @Override
    public String toString() {
        return name;
    }
}
