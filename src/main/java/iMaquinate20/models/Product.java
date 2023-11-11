package iMaquinate20.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "products")
public class Product {


    public Product() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private @NotNull String name;
    private String[] imageUrl;
    private @NotNull double price;
    private @NotNull String description;
    private Integer[] characteristics;


    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private Category category;
    public Category getCategory() {
        return category;
    }

    public Product(String name, String[] imageUrl, double price, String description, Category category, Integer[] characteristics) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.description = description;
        this.category = category;
        this.characteristics = characteristics;
    }




    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String[] imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer[] getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Integer[] characteristics) {
        this.characteristics = characteristics;
    }

}
