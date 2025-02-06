package com.example.domain.mapping;

import com.example.domain.Review;
import com.example.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image_url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="review_id")
    private Review review;

    //review
    public void setReview(Review review) {
        if (this.review != null) {
            this.review.getReviewImages().remove(this);
        }
        this.review = review;
        if (review != null && !review.getReviewImages().contains(this)) {
            review.getReviewImages().add(this);
        }
    }
}
