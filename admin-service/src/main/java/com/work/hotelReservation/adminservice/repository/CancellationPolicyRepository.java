package com.work.hotelReservation.adminservice.repository;

import com.work.hotelReservation.adminservice.model.CancellationPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancellationPolicyRepository extends JpaRepository<CancellationPolicy, Long> {
}
