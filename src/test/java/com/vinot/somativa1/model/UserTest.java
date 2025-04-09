package com.vinot.somativa1.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testUserFields() {
        User user = new User("Ana", "ana@email.com", "ana01");
        assertEquals("Ana", user.getName());
        assertEquals("ana@email.com", user.getEmail());
        assertEquals("ana01", user.getUserId());
    }

    @Test
    public void testSetName() {
        User user = new User("Ana", "ana@email.com", "ana01");
        user.setName("Carla");
        assertEquals("Carla", user.getName());
    }
}