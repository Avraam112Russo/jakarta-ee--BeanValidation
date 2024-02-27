package com.example.beanvalidation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.*;

@ApplicationScoped
public class MyUser {


    private Integer age;

    private String name;
    private String email;

    @Pattern(regexp = "[A-Z][a-z]*", message = "Incorrect name. Name must be follow example: Russo")
    @Size(min = 3, max = 20)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Min(value = 18, message = "Age must be greater than 18.")
    @Max(value = 65, message = "Age must be less than 65.")
    @NotNull
    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Pattern(regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message = "Incorrect email. Try again later")
    @Size(min = 5, max = 30)
    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "LoginForm{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
