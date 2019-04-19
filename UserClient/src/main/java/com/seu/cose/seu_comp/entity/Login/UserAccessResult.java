package com.seu.cose.seu_comp.entity.Login;

import com.seu.cose.seu_comp.entity.Base.AccessResult;
import com.seu.cose.seu_comp.entity.Base.User;

public class UserAccessResult extends AccessResult<User> {

    private User user = (User) responseBody;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

}
