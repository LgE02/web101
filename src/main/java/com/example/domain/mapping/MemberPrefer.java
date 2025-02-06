package com.example.domain.mapping;

import com.example.domain.Category;
import com.example.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberPrefer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    //member
    public void setMember(Member member) {
        if(this.member != null){
            this.member.getMemberPrefers().remove(this);
        }
        this.member = member;
        if(member != null && !member.getMemberPrefers().contains(this)){
            member.getMemberPrefers().add(this);
        }
    }

    //category
    public void setCategory(Category category) {
        if(this.category != null){
            this.category.getMemberPrefers().remove(this);
        }
        this.category = category;
        if(category != null && !category.getMemberPrefers().contains(this)){
            category.getMemberPrefers().add(this);
        }
    }
}
