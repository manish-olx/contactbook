package com.myproject.contactbook.pojo.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserContactPayload {
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    private String phone;

    @Pattern(regexp = "active|inactive", message = "{NotValid.schemaStatus}")
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
