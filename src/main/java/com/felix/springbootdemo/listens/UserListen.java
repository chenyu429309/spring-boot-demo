package com.felix.springbootdemo.listens;

import com.felix.springbootdemo.config.UserListenEvent;
import com.felix.springbootdemo.model.User;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserListen {
    @EventListener
    public void test(UserListenEvent userListenEvent) {

        User user = userListenEvent.getUser();
        System.out.println(user);
    }
}
