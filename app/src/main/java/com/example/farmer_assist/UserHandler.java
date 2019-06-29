package com.example.farmer_assist;

public class UserHandler {
    private User user;

    public UserHandler() {
    }

    public boolean createNewUser(User newUser){
        //create new user in DB
        return false;
    }

    public boolean removeNewUser(User currentUser){
        //create remove user in DB
        return false;
    }

    public boolean updateNewUser(User newUser){
        //update current user in DB
        return false;
    }

    public User authUser(String username, String password){
        //get user by name username and return user by checking checking its password
        return null;
    }
}
