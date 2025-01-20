package com.bank.deal.repository;

import com.bank.deal.entity.Employment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmploymentRepository extends JpaRepository<Employment, UUID> {
}
