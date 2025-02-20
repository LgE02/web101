package com.example.converter;

import com.example.domain.Category;
import com.example.domain.mapping.MemberPrefer;

import java.util.List;

public class MemberPreferConverter {

    public static List<MemberPrefer> toMemberPrefers(List<Category> categories){
        return categories.stream().map(category -> MemberPrefer.builder()
                                                                        .category(category)
                                                                        .build())
                                    .toList();
    }
}
