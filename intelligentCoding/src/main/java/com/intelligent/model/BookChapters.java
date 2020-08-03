package com.intelligent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
//@Table(uniqueConstraints = @UniqueConstraint(name = "", columnNames = {"booksId", "chapterNo"}))
public class BookChapters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Integer booksId;
    @NotNull
    private Integer chapterNo;
    @NotNull
    private String chapterName;
    @NotNull
    private String chapterFile;

    @ManyToOne
    @JsonIgnoreProperties({"bookChapters"})
    @JoinColumn(name = "booksId", insertable = false, updatable = false, nullable = false)
    private Books books;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBooksId() {
        return booksId;
    }

    public void setBooksId(Integer booksId) {
        this.booksId = booksId;
    }

    public Integer getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(Integer chapterNo) {
        this.chapterNo = chapterNo;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterFile() {
        return chapterFile;
    }

    public void setChapterFile(String chapterFile) {
        this.chapterFile = chapterFile;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "BookChapters{" +
                "id=" + id +
                ", booksId=" + booksId +
                ", chapterNo=" + chapterNo +
                ", chapterName='" + chapterName + '\'' +
                ", chapterFile='" + chapterFile + '\'' +
                ", books=" + books +
                '}';
    }
}
