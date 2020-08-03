package com.intelligent.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private boolean gender;
    private String intro;
    private String location;
    private String email;
    private boolean career = false;
    private String text;
    @ManyToMany
    @JsonIgnoreProperties("users")
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Roles> roles = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("users")
    private List<Answer> answers;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("users")
    private List<Discussion> discussions;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("users")
    private List<Notes> notes;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("users")
    private List<Solution> solutions;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("users")
    private List<UserFavoriteSolution> userFavoriteSolutions;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("users")
    private List<UserFavoriteTopic> userFavoriteTopics;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEamil() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isCareer() {
        return career;
    }

    public void setCareer(boolean career) {
        this.career = career;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Discussion> getDiscussions() {
        return discussions;
    }

    public void setDiscussions(List<Discussion> discussions) {
        this.discussions = discussions;
    }

    public List<Notes> getNotes() {
        return notes;
    }

    public void setNotes(List<Notes> notes) {
        this.notes = notes;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

    public List<UserFavoriteSolution> getUserFavoriteSolutions() {
        return userFavoriteSolutions;
    }

    public void setUserFavoriteSolutions(List<UserFavoriteSolution> userFavoriteSolutions) {
        this.userFavoriteSolutions = userFavoriteSolutions;
    }

    public List<UserFavoriteTopic> getUserFavoriteTopics() {
        return userFavoriteTopics;
    }

    public void setUserFavoriteTopics(List<UserFavoriteTopic> userFavoriteTopics) {
        this.userFavoriteTopics = userFavoriteTopics;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public String getEmail() {
        return email;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", intro='" + intro + '\'' +
                ", location='" + location + '\'' +
                ", carreer=" + career +
                ", text='" + text + '\'' +
                ", answers=" + answers +
                ", discussions=" + discussions +
                ", notes=" + notes +
                ", solutions=" + solutions +
                ", userFavoriteSolutions=" + userFavoriteSolutions +
                ", userFavoriteTopics=" + userFavoriteTopics +
                ", roles=" + roles +
                '}';
    }
}
