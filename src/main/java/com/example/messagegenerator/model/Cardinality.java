package com.example.messagegenerator.model;

public class Cardinality {
    private String minCardinality;
    private String maxCardinality;

    public Cardinality(String minCardinality, String maxCardinality) {
        this.minCardinality = minCardinality;
        this.maxCardinality = maxCardinality;
    }

    public String getMinCardinality() {
        return minCardinality;
    }

    public void setMinCardinality(String minCardinality) {
        this.minCardinality = minCardinality;
    }

    public String getMaxCardinality() {
        return maxCardinality;
    }

    public void setMaxCardinality(String maxCardinality) {
        this.maxCardinality = maxCardinality;
    }
}
