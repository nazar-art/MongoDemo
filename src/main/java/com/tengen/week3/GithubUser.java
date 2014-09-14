package com.tengen.week3;

import org.mongodb.morphia.annotations.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(value = "users", noClassnameStored = true)
@Indexes({
        @Index(value = "userName -followers", name = "popular"),
        @Index(value = "lastActive", name = "idle", expireAfterSeconds = 1000000000)
})
public class GithubUser {
    @Id
    public String userName;
    public String fullName;

    @Property("since")
    public Date memberSince;
    public Date lastActive;

    @Reference(lazy = true)
    public List<Repository> repositories = new ArrayList<>();
    public int followers = 0;
    public int folloving = 0;

    public GithubUser() {
    }

    
}
