package com.example.domain.mapping;

import com.example.domain.Member;
import com.example.domain.Mission;
import com.example.domain.base.BaseEntity;
import com.example.domain.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberMission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(15) default 'WAITING' ")
    private MissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    //member
    public void setMember(Member member) {
        if(this.member != null) {
            this.member.getMemberMissions().remove(this);
        }
        this.member = member;
        if(member != null && !member.getMemberMissions().contains(this)) {
            member.getMemberMissions().add(this);
        }
    }
    //mission(오류X)
    public void setMission(Mission mission) {
        if(this.mission != null) {
            this.mission.getMemberMissions().remove(this);
        }
        this.mission = mission;
        if(mission != null && !mission.getMemberMissions().contains(this)) {
            mission.getMemberMissions().add(this);
        }
    }

}
