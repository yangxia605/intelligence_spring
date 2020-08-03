package com.intelligent.util;

import com.intelligent.model.Users;

public class UserContext {
    private static ThreadLocal<Users> user = new ThreadLocal<>();

    public static Users getCurrentUser() {
        return user.get();
    }

    public static void setCurrentUser(Users users) {
        user.set(users);
    }

    public static void removeCurrentUser() {
        user.remove();
    }
}
