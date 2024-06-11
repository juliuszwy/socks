package com.scoks.order.entity;

import lombok.Data;

import java.util.Set;

@Data
public class Role {
    private Integer id;
    private String roleName;

    private Set<Permissions> permissions;

    public Role(Integer id, String roleName, Set<Permissions> permissions) {
        this.id = id;
        this.roleName = roleName;
        this.permissions = permissions;
    }


}
