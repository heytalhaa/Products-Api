// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.eshopping.data.model;

import java.time.OffsetDateTime;

public class CreateUser {
    private String password;
    private String role;
    private String creationAt;
    private String name;
    private String avatar;
    private long id;
    private String email;
    private String updatedAt;

    public String getPassword() { return password; }
    public void setPassword(String value) { this.password = value; }

    public String getRole() { return role; }
    public void setRole(String value) { this.role = value; }

    public String getCreationAt() { return creationAt; }
    public void setCreationAt(String value) { this.creationAt = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String value) { this.avatar = value; }

    public long getid() { return id; }
    public void setid(long value) { this.id = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String value) { this.updatedAt = value; }
}
