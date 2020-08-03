package com.intelligent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

@Entity
//@Table(uniqueConstraints = @UniqueConstraint(name = "user_favorite_solution_users_topic", columnNames = {"userId", "solutionId"}))
public class UserFavoriteSolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer solutionId;

    @ManyToOne
    @JsonIgnoreProperties({"userFavoriteSolutions"})
    @JoinColumn(name = "userId", insertable = false, updatable = false, nullable = false)
    private Users users;

    @ManyToOne
    @JsonIgnoreProperties({"userFavoriteSolutions"})
    @JoinColumn(name = "solutionId", insertable = false, updatable = false, nullable = false)
    private Solution solution;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "UserFavoriteSolution{" +
                "id=" + id +
                ", userId=" + userId +
                ", solutionId=" + solutionId +
                ", users=" + users +
                ", solution=" + solution +
                '}';
    }
}
