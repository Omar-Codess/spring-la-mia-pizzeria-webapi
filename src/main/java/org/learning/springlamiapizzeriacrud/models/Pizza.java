package org.learning.springlamiapizzeriacrud.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pizzas")

public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotEmpty
    @Size(min = 1, max = 50, message = "Hai superato il limite massimo di caratteri")
    private String name;
    @NotEmpty
    @Size(min = 1, max = 200, message = "Hai superato il limite massimo di caratteri")
    private String description;
    @NotNull
    @Positive
    private float price;

    @JsonIgnore
    @OneToMany(mappedBy = "pizza")
    private List<Sale> sales;

    @ManyToMany
    @JoinTable(
            name = "pizza_category",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    public Pizza(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Pizza(){
        super();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
