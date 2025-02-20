package com.example.domain;

import com.example.domain.base.BaseEntity;
import com.example.domain.mapping.ReviewImage;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double score;

    @Column(nullable = false, length =20)
    private String title;

    @Column(nullable = false)
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewImage> reviewImages = new ArrayList<>();

    //review
    public void setStore(Store store) {
        if (this.store != null) {
            this.store.getReviews().remove(this);
        }
        this.store = store;
        if(store != null && !store.getReviews().contains(this)) {
            store.getReviews().add(this);
        }
    }

    //member
    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getReviews().remove(this);
        }
        this.member = member;
        if(member != null && !member.getReviews().contains(this)) {
            member.getReviews().add(this);
        }
    }

    //reviewImage
    public void addReviewImage(ReviewImage reviewImage) {
        this.reviewImages.add(reviewImage);
        if(reviewImage.getReview() != this) {
            reviewImage.setReview(this);
        }
    }

    public void removeReviewImage(ReviewImage reviewImage) {
        this.reviewImages.remove(reviewImage);
        if(reviewImage.getReview() == this) {
            reviewImage.setReview(null);
        }
    }
}
