package com.app.services;

import java.util.List;

import com.app.dto.PolicyDetailsDTO;
import com.app.entities.InsuranceProvider;
import com.app.entities.Policy;
import com.app.entities.User;

public interface IProviderService {

	boolean addPolicy(Policy policy);

	InsuranceProvider getProvider(Long userId);

	InsuranceProvider addNewInsuranceProvider(InsuranceProvider provider,User u);


	boolean addPolicy(PolicyDetailsDTO policy, long providerId);

	List<PolicyDetailsDTO> getPolicies(long id);

	boolean deletePolicy(long id, long policyId);

	PolicyDetailsDTO getPolicy(long id, long policyId) throws Exception;

	boolean updatePolicy(PolicyDetailsDTO policyDTO, long providerId);

}
