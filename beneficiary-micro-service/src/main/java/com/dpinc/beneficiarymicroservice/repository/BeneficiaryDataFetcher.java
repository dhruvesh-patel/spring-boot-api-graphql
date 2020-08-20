package com.dpinc.beneficiarymicroservice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dpinc.beneficiarymicroservice.entity.Beneficiary;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BeneficiaryDataFetcher implements DataFetcher<Beneficiary>{

	@Autowired
	private BeneficiaryRepository beneficiaryRepository;
	
	
	@Override
	public Beneficiary get(DataFetchingEnvironment environment) {
		
		String beneficiaryId = environment.getArgument("beneficiaryId");
		
		return beneficiaryRepository.findById(Integer.valueOf(beneficiaryId)).get();
	}

}
