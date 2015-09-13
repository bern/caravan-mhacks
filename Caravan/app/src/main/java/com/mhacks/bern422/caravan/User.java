package com.mhacks.bern422.caravan;

/**
 * Created by M3800 on 9/12/2015.
 */
public class User {
    private String id;
    private Location loc;

    public User() {

    }

    public User(String id, Location loc) {
        this.id = id;
        this.loc = loc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }
}
