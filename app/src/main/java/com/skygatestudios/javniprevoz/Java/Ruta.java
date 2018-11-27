package com.skygatestudios.javniprevoz.Java;

/**
 * Created by djordjekalezic on 28/10/2016.
 */

public class Ruta {

    private String nameR;
    private String startR;
    private String endR;

    private String startTable;
    private String endTable;
    private String firstStart;
    private String firstEnd;
    private String secondStart;
    private String secondEnd;

    public Ruta(String nameR, String startR, String endR, String startTable, String endTable, String firstStart, String firstEnd, String secondStart, String secondEnd) {
        this.nameR = nameR;
        this.startR = startR;
        this.endR = endR;
        this.startTable = startTable;
        this.endTable = endTable;
        this.firstStart = firstStart;
        this.firstEnd = firstEnd;
        this.secondStart = secondStart;
        this.secondEnd = secondEnd;
    }

    public String getNameR() {
        return nameR;
    }

    public void setNameR(String nameR) {
        this.nameR = nameR;
    }

    public String getStartR() {
        return startR;
    }

    public void setStartR(String startR) {
        this.startR = startR;
    }

    public String getEndR() {
        return endR;
    }

    public void setEndR(String endR) {
        this.endR = endR;
    }

    public String getStartTable() {
        return startTable;
    }

    public void setStartTable(String startTable) {
        this.startTable = startTable;
    }

    public String getEndTable() {
        return endTable;
    }

    public void setEndTable(String endTable) {
        this.endTable = endTable;
    }

    public String getFirstStart() {
        return firstStart;
    }

    public void setFirstStart(String firstStart) {
        this.firstStart = firstStart;
    }

    public String getFirstEnd() {
        return firstEnd;
    }

    public void setFirstEnd(String firstEnd) {
        this.firstEnd = firstEnd;
    }

    public String getSecondStart() {
        return secondStart;
    }

    public void setSecondStart(String secondStart) {
        this.secondStart = secondStart;
    }

    public String getSecondEnd() {
        return secondEnd;
    }

    public void setSecondEnd(String secondEnd) {
        this.secondEnd = secondEnd;
    }
}