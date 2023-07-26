package com.eviro.assessment.grad001.Mpumelelo.Ngozo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eviro.assessment.grad001.Mpumelelo.Ngozo.entity.AccountProfile;

@Repository
public interface AccountProfileRepository extends JpaRepository<AccountProfile, Long> {

}

