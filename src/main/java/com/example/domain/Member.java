package com.example.domain;

import com.example.domain.base.BaseEntity;
import com.example.domain.enums.Gender;
import com.example.domain.enums.MemberStatus;
import com.example.domain.enums.SocialType;
import com.example.domain.mapping.MemberMission;
import com.example.domain.mapping.MemberPrefer;
import com.example.domain.mapping.TermsAgreement;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    private Integer age;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false, length =40)
    private String spec_address;

    //nullable = false,
    @Column( length = 50)
    private String email;

    @ColumnDefault("0")
    private Integer point;

    private LocalDate inactiveDate;

    //열거형
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(10)")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(15) default 'ACTIVE' ")
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(10)")
    private SocialType socialType;

    //양방향설정
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<TermsAgreement> termsAgreements = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPrefers = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissions = new ArrayList<>();

    //termsAgreements
    public void addTermsAgreement(TermsAgreement termsAgreement) {
        this.termsAgreements.add(termsAgreement);
        if(termsAgreement.getMember() != this) {
            termsAgreement.setMember(this);
        }
    }

    public void removeTermsAgreement(TermsAgreement termsAgreement) {
        this.termsAgreements.remove(termsAgreement);
        if(termsAgreement.getMember() == this) {
            termsAgreement.setMember(null);
        }
    }

    //memberPrefers
    public void addMemberPrefer(MemberPrefer memberPrefer) {
        this.memberPrefers.add(memberPrefer);
        if(memberPrefer.getMember() != this) {
            memberPrefer.setMember(this);
        }
    }

    public void removeMemberPrefer(MemberPrefer memberPrefer) {
        this.memberPrefers.remove(memberPrefer);
        if(memberPrefer.getMember() == this) {
            memberPrefer.setMember(null);
        }
    }

    //reviews
    public void addReview(Review review) {
        this.reviews.add(review);
        if(review.getMember() != this) {
            review.setMember(this);
        }
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
        if(review.getMember() == this) {
            review.setMember(null);
        }
    }

    //memberMissions
    public void addMemberMission(MemberMission memberMission) {
        this.memberMissions.add(memberMission);
        if(memberMission.getMember() != this) {
            memberMission.setMember(this);
        }
    }

    public void removeMemberMission(MemberMission memberMission) {
        this.memberMissions.remove(memberMission);
        if(memberMission.getMember() == this) {
            memberMission.setMember(null);
        }
    }
}
