package com.example.domain;

import com.example.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Region extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<Store> stores = new ArrayList<>();

    //stores
    public void addStore(Store store) {
        this.stores.add(store);
        if (store.getRegion() != this) {
            store.setRegion(this);
        }
    }

    public void removeStore(Store store) {
        this.stores.remove(store);
        if (store.getRegion() == this) {
            store.setRegion(null);
        }
    }
}
