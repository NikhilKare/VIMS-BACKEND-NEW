package com.app.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.PolicyDetailsDTO;
import com.app.dto.ProviderDTO;
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
	public ProviderDTO getProvider(Long userId) {
		 InsuranceProvider provider = providerRepo.getById(userId);
		 System.out.println("In Get Provider");
		 System.out.println(provider);
		 
		 return mapper.map(provider, ProviderDTO.class);		 
	}

	@Override
	public InsuranceProvider addNewInsuranceProvider(InsuranceProvider provider,User u) {
		provider.setUser(userRepo.save(u)); 
		return providerRepo.save(provider);
	}
	
	@Override
	public boolean addPolicy(PolicyDetailsDTO policyDTO,long userId) {
		try {
			Policy policy=mapper.map(policyDTO, Policy.class);
			policy.setPolicyLaunchDate(LocalDate.now());
			
			InsuranceProvider provider = providerRepo.getById(userId);
			policy=policyRepo.saveAndFlush(policy);
			provider.getPolicy().add(policy);
			policy.setProvider(provider);
			policyRepo.save(policy);
			return true;
		}catch (Exception e) {
			return false;
		}		
	}

	@Override
	public List<PolicyDetailsDTO> getPolicies(long id,int pageNo) {
		List<PolicyDetailsDTO> policyDetailsDTO=new ArrayList<PolicyDetailsDTO>();
		Pageable page=Pageable.ofSize(2).withPage(pageNo-1);
				policyRepo.findByProviderId(id,page).forEach(i->policyDetailsDTO.add(mapper.map(i, PolicyDetailsDTO.class)));
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
	public PolicyDetailsDTO getPolicy(long id, long policyId) throws Exception {
		
		Policy policy = policyRepo.findById(policyId).orElseThrow(()->  new Exception("Policy Not Found"));
		if(policy.getProvider().getUser().getUserId()==id)
			return mapper.map(policy, PolicyDetailsDTO.class);
		return null;
	}
	
	@Override
	public boolean updatePolicy(PolicyDetailsDTO policyDTO,long userId) {
		try {
			Policy policy=policyRepo.getById(policyDTO.getPolicyId());
			mapper.map(policyDTO,policy);
			InsuranceProvider provider = providerRepo.getById(userId);
			provider.getPolicy().add(policy);
			policy.setProvider(provider);
//			policyRepo.save(policy);
			return true;
		}catch (Exception e) {
			return false;
		}		
	}
	
	@Override
	public long getNoOfPolicies(long id) {
		
				long countByProvider = policyRepo.countByProvider(id);
				System.out.println(countByProvider);
				return countByProvider;
						
	}
	
	
	
}
