package com.intelligent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class UseCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String input;
    private String output;

    @OneToMany(mappedBy = "useCase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("useCase")
    private List<UseCaseTopic> useCaseTopics;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public List<UseCaseTopic> getUseCaseTopics() {
        return useCaseTopics;
    }

    public void setUseCaseTopics(List<UseCaseTopic> useCaseTopics) {
        this.useCaseTopics = useCaseTopics;
    }

    @Override
    public String toString() {
        return "UseCase{" +
                "id=" + id +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", useCaseTopics=" + useCaseTopics +
                '}';
    }
}
