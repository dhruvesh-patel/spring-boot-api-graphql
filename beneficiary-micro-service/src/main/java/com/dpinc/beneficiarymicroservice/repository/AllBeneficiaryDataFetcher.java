package com.dpinc.beneficiarymicroservice.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dpinc.beneficiarymicroservice.entity.Beneficiary;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllBeneficiaryDataFetcher implements DataFetcher<List<Beneficiary>>{

	@Autowired
	private BeneficiaryRepository beneficiaryRepository;
	
	@Override
	public List<Beneficiary> get(DataFetchingEnvironment environment) {
		
		return beneficiaryRepository.findAll();
	}

}
