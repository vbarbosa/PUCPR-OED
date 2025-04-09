package com.vinot.somativa1.model;

/**
 * Representa um usuário da biblioteca com identificador único e nome.
 */
public class User {
    private String name;
    private String email;
    private String userId;

    public User() {
    }

    public User(String name, String email, String userId) {
        this.name = name;
        this.email = email;
        this.userId = userId;
    }

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

    /**
     * Retorna um usuário com base no ID, se existir.
     * @return usuário encontrado ou vazio
     */
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "model.User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

}
