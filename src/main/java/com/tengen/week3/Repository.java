package com.tengen.week3;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class Repository {
    @Id
    public String name;

    @Reference
    public Organization organization;

    @Reference
    public GithubUser owner;

    public Repository() {
    }

    public Repository(String name, Organization organization) {
        this.name = organization.name + "/" + name;
        this.organization = organization;
    }
}
