package com.jcode.jshop.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcode.jshop.backend.persistence.domain.backend.Plan;
import com.jcode.jshop.backend.persistence.repositories.PlanRepository;
import com.jcode.jshop.enums.PlansEnum;

@Service
@Transactional(readOnly = true)
public class PlanService {

	@Autowired
	private PlanRepository planRepository;
	
	/**
	 * Returns the plan id for the given id or null if it couldn't find one.
	 * @param planId The plan id
	 * @return The plan id for the given id or null if it couldn't find one.
	 */
	public Plan finPlanById(int planId) {
		return planRepository.findById(planId).get();
	}
	
	/**
	 * It creates a Basic or Pro plan.
	 * @param planId The plan id
	 * @return the created plan
	 * @throws IllegalArgumentException if the plan id is not 1 or 2
	 */
	@Transactional
	public Plan createPlan(int planId) {
		Plan plan = null;
		if (planId == PlansEnum.BASIC.getId()) {
			plan = planRepository.save(new Plan(PlansEnum.BASIC));
		} else if (planId == PlansEnum.PRO.getId()) {
			plan = planRepository.save(new Plan(PlansEnum.PRO));
		} else {
			throw new IllegalArgumentException("Plan id " + planId + " not recognised.");
		}
		return plan;
	}
}
