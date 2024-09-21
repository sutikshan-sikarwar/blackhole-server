package com.example.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
    
    private String profilePic;
    
    private List<Integer> followers = new ArrayList<>();
    
    private List<Integer> following = new ArrayList<>();
    
    @JsonIgnore
    @ManyToMany
    private List<Post> savedPost = new ArrayList<>();
    
    private String gender;
}
