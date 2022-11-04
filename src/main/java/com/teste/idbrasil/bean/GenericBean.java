package com.teste.idbrasil.bean;

import javax.persistence.*;

@MappedSuperclass
public abstract class GenericBean {
    @Id
    @Column(
            name = "id"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
