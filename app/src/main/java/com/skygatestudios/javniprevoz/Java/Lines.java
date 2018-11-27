package com.skygatestudios.javniprevoz.Java;

/**
 * Created by djordjekalezic on 26/10/2016.
 */

public class Lines {

    private int id;
    private String name;

    private String start;
    private String end;

    public Lines(int id, String name, String start, String end) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }




}
