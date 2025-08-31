package org.example.taller2.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_name"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions = new HashSet<>();

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();


    public Role() {}

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public List<User> getUsers() {return users;}
    public void setUsers(List<User> users) {this.users = users;}
    public Set<Permission> getPermissions() {return permissions;}
    public void setPermissions(Set<Permission> permissions) {this.permissions = permissions;}

}
