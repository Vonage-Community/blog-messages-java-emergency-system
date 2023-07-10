package com.response.emergency.requests;

import lombok.Getter;

@Getter
public class AddContactRequest {

    private Long userId;

    private String name;

    private String phoneNumber;
}
