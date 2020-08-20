package com.dpinc.beneficiarymicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpinc.beneficiarymicroservice.entity.Beneficiary;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer>{

}
