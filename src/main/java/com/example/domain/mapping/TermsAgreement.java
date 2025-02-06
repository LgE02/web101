package com.example.domain.mapping;

import com.example.domain.Member;
import com.example.domain.Terms;
import com.example.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TermsAgreement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isAgreed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="terms_id")
    private Terms terms;

    //member
    public void setMember(Member member) {
        if(this.member != null){
            this.member.getTermsAgreements().remove(this);
        }
        this.member = member;
        if(member != null && !member.getTermsAgreements().contains(this)){
            member.getTermsAgreements().add(this);
        }
    }

    //terms
    public void setTerms(Terms terms) {
        if(this.terms != null){
            this.terms.getTermsAgreements().remove(this);
        }
        this.terms = terms;
        if(terms != null && !terms.getTermsAgreements().contains(this)){
            terms.getTermsAgreements().add(this);
        }
    }
}
