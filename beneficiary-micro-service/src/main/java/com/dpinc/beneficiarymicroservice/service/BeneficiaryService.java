package com.dpinc.beneficiarymicroservice.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.dpinc.beneficiarymicroservice.entity.Beneficiary;
import com.dpinc.beneficiarymicroservice.repository.AllBeneficiaryDataFetcher;
import com.dpinc.beneficiarymicroservice.repository.BeneficiaryDataFetcher;
import com.dpinc.beneficiarymicroservice.repository.BeneficiaryRepository;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;


@Service
public class BeneficiaryService {

	@Autowired
	private BeneficiaryRepository beneficiaryRepository;
	
	@Value("classpath:beneficiary.graphql")
	private Resource resource;
	
	private GraphQL graphQl;
	
	@Autowired
	private AllBeneficiaryDataFetcher allBeneficiaryFetcher;
	
	@Autowired
	private BeneficiaryDataFetcher beneficiaryFetcher;
	
	@PostConstruct
	private void loadSchema() throws IOException{
		
		loadDataIntoDB();
		
		//get the schema
		File schemaFile = resource.getFile();
		
		//parse schema
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		graphQl = GraphQL.newGraphQL(schema).build();
			
	}

	private RuntimeWiring buildRuntimeWiring() {
		
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", typeWiring -> typeWiring
						.dataFetcher("allBeneficiary", allBeneficiaryFetcher)
						.dataFetcher("beneficiary", beneficiaryFetcher))
						.build();
	}
	
	public GraphQL getGraphQL() {
		return graphQl;
	}
	
	 	
	private void loadDataIntoDB() {
		
		Stream.of(
				new Beneficiary(111, "Mr Kohli", "Individual", "IN12345", "UTR", "BCCI", "Large", "E14 5AQ", "GB", "UK", "SYSTEM", "ADMIN", "APPROVED", 
						new Date(), new Date()),
				new Beneficiary(222, "Mr Dhoni", "Individual", "IN12345", "UTR", "BCCI", "Large", "E14 5AQ", "GB", "UK", "SYSTEM", "ADMIN", "APPROVED", 
						new Date(), new Date()),
				new Beneficiary(333, "Mr Rohit", "Individual", "IN12345", "UTR", "BCCI", "Large", "E14 5AQ", "GB", "UK", "SYSTEM", "ADMIN", "APPROVED", 
						new Date(), new Date())
				).forEach( beneficiary -> {
					beneficiaryRepository.save(beneficiary);	
				});
				
	}
	
}
