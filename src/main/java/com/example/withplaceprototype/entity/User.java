package com.example.withplaceprototype.entity;

import com.example.withplaceprototype.entity.value.Address;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter // Setter는 나중에 삭제해야 함.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"seq", "userId", "userPw", "userName", "age", "gender"})
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name="userSeq")
    private Long seq;

    private String userId; // 아이디
    private String userPw; // 비밀번호
    private String userName; // 이름
    private int age; // 나이
    private String gender; // 성별 남자:M, 여자:F

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "user")
    private List<UserMatchingRequest> userMatchingRequestList = new ArrayList<>();

}
