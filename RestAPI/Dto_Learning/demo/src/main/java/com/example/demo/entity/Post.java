package com.example.demo.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Post{
    private Long id;
    private String text;
    private Long userId;
}