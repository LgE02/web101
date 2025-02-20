package com.example.repository;

import com.example.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  MissionRepository extends JpaRepository<Mission,Long> {
}
