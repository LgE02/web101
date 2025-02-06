package com.example.domain;

import com.example.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(nullable = false)
    private Double score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="region_id")
    private Region region;

    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> missions = new ArrayList<>();

    //region
    public void setRegion(Region region) {
        if(this.region != null) {
            this.region.getStores().remove(this);
        }
        this.region = region;
        if(region != null && !region.getStores().contains(this)) {
            region.getStores().add(this);
        }
    }

    //reviews
    public void addReview(Review review) {
        this.reviews.add(review);
        if(review.getStore() != this) {
            review.setStore(this);
        }
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
        if(review.getStore() == this) {
            review.setStore(null);
        }
    }

    //missions
    public void addMission(Mission mission) {
        this.missions.add(mission);
        if(mission.getStore() != this) {
            mission.setStore(this);
        }
    }

    public void removeMission(Mission mission) {
        this.missions.remove(mission);
        if(mission.getStore() == this) {
            mission.setStore(null);
        }
    }
}
