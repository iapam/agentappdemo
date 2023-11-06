package com.example.transmissionproject.Request;

import lombok.*;

@EqualsAndHashCode
@Getter
@ToString
@AllArgsConstructor
@Setter
public class VoteRequest {
    private final String ndcvotes;
    private final String nppvotes;

}
