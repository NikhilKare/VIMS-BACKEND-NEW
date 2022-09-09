package com.app.services;

import com.app.entities.InsuranceProvider;
import com.app.entities.Policy;
import com.app.entities.User;

public interface IProviderService {

	boolean addPolicy(Policy policy);

	InsuranceProvider getProvider(Long userId);

	InsuranceProvider addNewInsuranceProvider(InsuranceProvider provider,User u);

	boolean addPolicy(Policy policy, User user);

}
