package com.clubfactory.demo.test.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private Integer id;

    private String name;

    private String password;

    private Integer roleId;

}