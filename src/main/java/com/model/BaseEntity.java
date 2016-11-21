package com.model;


import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass()
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -4156938009387725129L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%s(id=%d)", this.getClass().getSimpleName(), this.getId());
    }
}
