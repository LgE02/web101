package com.example.repository;

import com.example.domain.Member;
import com.example.domain.Mission;
import com.example.domain.Store;
import com.example.domain.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  MissionRepository extends JpaRepository<Mission,Long> {
    Page<Mission> findAllByStore(Store store, PageRequest pageRequest);


}
