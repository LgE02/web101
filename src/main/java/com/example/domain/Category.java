package com.example.domain;

import com.example.domain.base.BaseEntity;
import com.example.domain.mapping.MemberPrefer;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 15)
    private String name;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPrefers = new ArrayList<>();

    //memberPrefers
    public void addMemberPrefer(MemberPrefer memberPrefer) {
        this.memberPrefers.add(memberPrefer);
        if(memberPrefer.getCategory() != this){
            memberPrefer.setCategory(this);
        }
    }

    public void removeMemberPrefer(MemberPrefer memberPrefer) {
        this.memberPrefers.remove(memberPrefer);
        if(memberPrefer.getCategory() == this){
            memberPrefer.setCategory(null);
        }
    }
}
