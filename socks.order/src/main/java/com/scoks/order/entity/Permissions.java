package com.scoks.order.entity;
import lombok.Data;

@Data
public class Permissions {
    private Integer id;
    private String permissionsName;

    public Permissions(Integer id, String permissionsName) {
        this.id = id;
        this.permissionsName = permissionsName;
    }
}