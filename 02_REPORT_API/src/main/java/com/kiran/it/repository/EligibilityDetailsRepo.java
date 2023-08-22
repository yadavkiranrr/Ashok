package com.kiran.it.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kiran.it.entity.EligibilityDetails;

@Repository
public interface EligibilityDetailsRepo extends JpaRepository<EligibilityDetails,Integer>{

	@Query("select distinct(planName) from EligibilityDetails")
	public List<String> findByPlanNames();
	
	
	@Query("select distinct(planStatus) from EligibilityDetails")
	public List<String> findByPlanStatuses();
}
