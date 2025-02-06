package com.example.domain;

import com.example.domain.base.BaseEntity;
import com.example.domain.mapping.MemberMission;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer point;

    @Column(nullable = false)
    private LocalDate deadline;

    @Column(nullable = false)
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="store_id")
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissions = new ArrayList<>();

    //memberMissions(오류O)
    public void addMemberMission(MemberMission memberMission) {
        this.memberMissions.add(memberMission);
        if (memberMission.getMission() != this) {
            memberMission.setMission(this);
        }
    }

    public void removeMemberMission(MemberMission memberMission) {
        this.memberMissions.remove(memberMission);
        if (memberMission.getMission() == this) {
            memberMission.setMission(null);
        }
    }

    //store
    public void setStore(Store store) {
        if(this.store != null){
            this.store.getMissions().remove(this);
        }
        this.store = store;
        if(store != null && !store.getMissions().contains(this)) {
            store.getMissions().add(this);
        }
    }
}
