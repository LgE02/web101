package com.example.domain;

import com.example.domain.base.BaseEntity;
import com.example.domain.mapping.TermsAgreement;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Terms extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false)
    private String body;

    private boolean mandatory;

    @OneToMany(mappedBy = "terms", cascade = CascadeType.ALL)
    private List<TermsAgreement> termsAgreements = new ArrayList<>();

    //termsAgreements
    public void addTermsAgreement(TermsAgreement termsAgreement) {
        this.termsAgreements.add(termsAgreement);
        if(termsAgreement.getTerms() != this) {
            termsAgreement.setTerms(this);
        }
    }

    public void removeTermsAgreement(TermsAgreement termsAgreement) {
        this.termsAgreements.remove(termsAgreement);
        if(termsAgreement.getTerms() == this) {
            termsAgreement.setTerms(null);
        }
    }
}
