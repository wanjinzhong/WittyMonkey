package com.wittymonkey.socket;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by neilw on 2017/5/22.
 */
public class UsersOnLine {
    private static Map<Integer, NotifySocket> users = new HashMap<Integer, NotifySocket>();

    public static Map<Integer, NotifySocket> getUsers() {
        return users;
    }

    public static void setUsers(Map<Integer, NotifySocket> users) {
        UsersOnLine.users = users;
    }

    public static Integer total(){
        return users.size();
    }
}
