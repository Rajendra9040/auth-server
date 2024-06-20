package com.example.auththentication.dto.event;

import com.example.auththentication.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserCreationEvent extends ApplicationEvent {
    private User user;
}
