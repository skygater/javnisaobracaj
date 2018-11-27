package com.skygatestudios.javniprevoz.Java;

/**
 * Created by djordjekalezic on 29/10/2016.
 */

public class Stanice {

    private int id;
    private String nameS;

    public Stanice(int id, String nameS) {
        this.id = id;
        this.nameS = nameS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameS() {
        return nameS;
    }

    public void setNameS(String nameS) {
        this.nameS = nameS;
    }
}
