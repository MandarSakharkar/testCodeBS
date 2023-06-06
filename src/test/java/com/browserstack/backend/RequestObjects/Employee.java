package com.browserstack.backend.RequestObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    private String employee_name;
    private String employee_salary;
    private String employee_age;
    private int id;
    private String profile_image;
}
