package com.intelligent.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;

    @OneToMany(mappedBy = "books", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("books")
    private List<BookChapters> bookChapters;

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

    public List<BookChapters> getBookChapters() {
        return bookChapters;
    }

    public void setBookChapters(List<BookChapters> bookChapters) {
        this.bookChapters = bookChapters;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bookChapters=" + bookChapters +
                '}';
    }
}
