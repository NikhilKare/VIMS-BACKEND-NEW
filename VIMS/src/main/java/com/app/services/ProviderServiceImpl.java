package com.app.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.PolicyDetailsDTO;
import com.app.entities.InsuranceProvider;
import com.app.entities.Policy;
import com.app.entities.User;
import com.app.repository.IPolicyRepository;
import com.app.repository.IProviderRepository;
import com.app.repository.IUserRepository;

@Service
@Transactional
public class ProviderServiceImpl implements IProviderService{
	@Autowired
	private IUserRepository userRepo;
	@Autowired
	private IPolicyRepository policyRepo;
	@Autowired
	private IProviderRepository providerRepo;
	@Autowired 
	private ModelMapper mapper;
	
	@Override
	public boolean addPolicy(Policy policy){
		try {
			policy.setPolicyLaunchDate(LocalDate.now());
			
			policyRepo.save(policy);
			
			return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public InsuranceProvider getProvider(Long userId) {
		 InsuranceProvider provider = providerRepo.getById(userId);
		 System.out.println("In Get Provider");
		 System.out.println(provider);
		 provider.getPolicy().forEach(i->System.out.println(i));
		 return provider;		 
	}

	@Override
	public InsuranceProvider addNewInsuranceProvider(InsuranceProvider provider,User u) {
		provider.setUserId(userRepo.save(u)); 
		return providerRepo.save(provider);
	}
	
	@Override
	public boolean addPolicy(PolicyDetailsDTO policyDTO,User user) {
		try {
			Policy policy=mapper.map(policyDTO, Policy.class);
			policy.setPolicyLaunchDate(LocalDate.now());
			InsuranceProvider provider=getProvider(user.getUserId());
			policy.setProvider(provider);
			policyRepo.save(policy);
			return true;
		}catch (Exception e) {
			return false;
		}		
	}

	@Override
	public List<PolicyDetailsDTO> getPolicies(long id) {
		List<PolicyDetailsDTO> policyDetailsDTO=new ArrayList<PolicyDetailsDTO>();
				policyRepo.findByProviderId(id).forEach(i->policyDetailsDTO.add(mapper.map(i, PolicyDetailsDTO.class)));
		return policyDetailsDTO;
	}

	@Override
	public boolean deletePolicy(long id, long policyId) {
		
		try {
		policyRepo.deleteById(policyId);
		return true;
		}catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public PolicyDetailsDTO getPolicy(long id, long policyId) {
		
		Optional<Policy> policy = policyRepo.findById(policyId);
		return mapper.map(policy, PolicyDetailsDTO.class);
	}
}
