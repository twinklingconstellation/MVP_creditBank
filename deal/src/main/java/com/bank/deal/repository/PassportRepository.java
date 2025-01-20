package com.bank.deal.repository;

import com.bank.deal.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PassportRepository extends JpaRepository<Passport, UUID> {
}
