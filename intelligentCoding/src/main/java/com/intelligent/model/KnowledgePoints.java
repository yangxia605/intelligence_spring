package com.intelligent.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class KnowledgePoints {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;

    @OneToMany(mappedBy = "knowledgePoints", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("knowledgePoints")
    private List<KnowledgePointsTopics> knowledgePointsTopics;

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

    public List<KnowledgePointsTopics> getKnowledgePointsTopics() {
        return knowledgePointsTopics;
    }

    public void setKnowledgePointsTopics(List<KnowledgePointsTopics> knowledgePointsTopics) {
        this.knowledgePointsTopics = knowledgePointsTopics;
    }

    @Override
    public String toString() {
        return "KnowledgePoints{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", knowledgePointsTopics=" + knowledgePointsTopics +
                '}';
    }
}
