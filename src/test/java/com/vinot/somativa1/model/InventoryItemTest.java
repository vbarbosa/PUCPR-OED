package com.vinot.somativa1.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryItemTest {

    @Test
    public void testAddPhysicalCopy() {
        InventoryItem item = new InventoryItem(123);
        int before = item.getTotalPhysicalCopies();
        item.addPhysicalCopy();
        assertEquals(before + 1, item.getTotalPhysicalCopies());
    }

    @Test
    public void testRemovePhysicalCopy() {
        InventoryItem item = new InventoryItem(123);
        item.addPhysicalCopy();
        int before = item.getTotalPhysicalCopies();
        item.removePhysicalCopy();
        assertEquals(before - 1, item.getTotalPhysicalCopies());
    }

    @Test
    public void testSetAndGetDigitalCopies() {
        InventoryItem item = new InventoryItem(123);
        item.setDigitalCopies(3);
        assertEquals(3, item.getDigitalCopies());
    }
}