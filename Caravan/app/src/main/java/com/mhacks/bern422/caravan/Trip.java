package com.mhacks.bern422.caravan;

/**
 * Created by M3800 on 9/12/2015.
 */
public class Trip {
    private String id;
    private Location orig;
    private Location dest;

    public Trip() {

    }

    public Trip(String id, Location orig, Location dest) {
        this.id = id;
        this.orig = orig;
        this.dest = dest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getOrig() {
        return orig;
    }

    public void setOrig(Location orig) {
        this.orig = orig;
    }

    public Location getDest() {
        return dest;
    }

    public void setDest(Location dest) {
        this.dest = dest;
    }
}
