package com.example.FinanceTracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.FinanceTracker.Models.TrackerModel;

public interface TrackerRepository extends JpaRepository<TrackerModel, Integer> {

}
