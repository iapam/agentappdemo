package com.example.transmissionproject.Request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class RegistrationRequest {
    private String agentName;
    private String agentNumber;
    private String agentTown;
    private String agentConsituency;
    private String userId;
    private String password;
}
