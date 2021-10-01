package com.practic.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sets")
public class ModelSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    @OneToMany(mappedBy = "set", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Model> models;

    public ModelSet() {
    }

    public ModelSet(String title, String description) {
        this.title = title;
        this.description = description;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.util.Set<Model> getModels() {
        return models;
    }

    public void setModels(java.util.Set<Model> models) {
        this.models = models;
    }
}
