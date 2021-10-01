package com.practic.entity;

import javax.persistence.*;

@Entity
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String letter;
    private String Scale;
    private String originalModelFileName;
    private String originalMtlFileName;
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    private ModelSet set;

    public Model() {
    }

    public Model(String letter, String scale, String originalModelFileName, String originalMtlFileName) {
        this.letter = letter;
        Scale = scale;
        this.originalModelFileName = originalModelFileName;
        this.originalMtlFileName = originalMtlFileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ModelSet getSet() {
        return set;
    }

    public void setSet(ModelSet set) {
        this.set = set;
    }

    public boolean hasMtlFile(){
        return originalMtlFileName != null;
    }

    public ModelSet getSet_id() {
        return set;
    }

    public void setSet_id(ModelSet set) {
        this.set = set;
    }


    public String getScale() {
        return Scale;
    }

    public void setScale(String scale) {
        Scale = scale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getOriginalModelFileName() {
        return originalModelFileName;
    }

    public void setOriginalModelFileName(String originalModelFileName) {
        this.originalModelFileName = originalModelFileName;
    }

    public String getOriginalMtlFileName() {
        return originalMtlFileName;
    }

    public void setOriginalMtlFileName(String originalMtlFileName) {
        this.originalMtlFileName = originalMtlFileName;
    }
}
