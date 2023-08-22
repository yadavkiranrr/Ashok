package com.kiran.it.apprunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.kiran.it.entity.EligibilityDetails;
import com.kiran.it.repository.EligibilityDetailsRepo;

@Component
public class ApplicationRun implements ApplicationRunner {

	@Autowired
	private EligibilityDetailsRepo eligRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		EligibilityDetails entity = new EligibilityDetails();

		entity.setEligId(1);
		entity.setName("John");
		entity.setMobile(1234555l);
		entity.setSsn(68986546589l);
		entity.setPlanName("SNAP");
		entity.setPlanStatus("Approved");
		entity.setEmail("kiran@gmail.com");
		eligRepo.save(entity);

		EligibilityDetails entity1 = new EligibilityDetails();

		entity1.setEligId(2);
		entity1.setName("John1");
		entity1.setMobile(123455556l);
		entity1.setSsn(6898654658569l);
		entity1.setPlanName("CCAP");
		entity1.setEmail("Kiran12@gmail.com");
		entity1.setPlanStatus("Denied");
		eligRepo.save(entity1);

		
		
		EligibilityDetails entity3 = new EligibilityDetails();

		entity3.setEligId(3);
		entity3.setName("Robert");
		entity3.setMobile(1234589562l);
		entity3.setSsn(6898654658569568l);
		entity3.setEmail("Kiran123@gmail.com");
		entity3.setPlanName("Medicaid");
		entity3.setPlanStatus("Closed");
		
		eligRepo.save(entity3);

	}

}
