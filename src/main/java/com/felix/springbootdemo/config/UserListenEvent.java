package com.felix.springbootdemo.config;

import com.felix.springbootdemo.model.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserListenEvent extends ApplicationEvent {
    @Autowired
    private User user;

    public UserListenEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
