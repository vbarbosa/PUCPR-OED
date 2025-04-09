package com.vinot.somativa1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa o inventário de um livro, controlando a quantidade de exemplares físicos e digitais disponíveis.
 */
public class InventoryItem {
    private int bookHash;
    private List<Integer> physicalCopyIds = new ArrayList<>();
    private int digitalCopies;

    public InventoryItem(int bookHash) {
        this.bookHash = bookHash;
    }

    public void addPhysicalCopy() {
        physicalCopyIds.add(generateExemplarId());
    }

    public void removePhysicalCopy() {
        if (!physicalCopyIds.isEmpty()) {
            physicalCopyIds.remove(physicalCopyIds.size() - 1);
        }
    }

    public int getTotalPhysicalCopies() {
        return physicalCopyIds.size();
    }

    public void setDigitalCopies(int count) {
        this.digitalCopies = count;
    }

    public int getDigitalCopies() {
        return digitalCopies;
    }

    public List<Integer> getPhysicalCopyIds() {
        return physicalCopyIds;
    }

    private int generateExemplarId() {
        return Objects.hash(bookHash, System.nanoTime(), physicalCopyIds.size());
    }

    @Override
    public String toString() {
        return "ObraHash=" + bookHash +
                ", Físicos=" + physicalCopyIds.size() +
                ", IDs=" + physicalCopyIds +
                ", Digitais=" + digitalCopies;
    }
}
