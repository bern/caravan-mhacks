package com.mhacks.bern422.caravan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M3800 on 9/12/2015.
 */
public class UserList {
    private String id;
    private List<User> userList;

    public UserList() {

    }

    public UserList(String id, User... users) {
        this.id = id;
        this.userList = new ArrayList<User>();

        for(int i = 0; i < users.length; i++) {
            this.userList.add(users[i]);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }
}
