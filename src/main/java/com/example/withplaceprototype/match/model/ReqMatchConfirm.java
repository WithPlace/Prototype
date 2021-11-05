package com.example.withplaceprototype.match.model;

import com.example.withplaceprototype.entitiy.type.QueueStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqMatchConfirm {
    private String id;
    private QueueStatus status;
}
