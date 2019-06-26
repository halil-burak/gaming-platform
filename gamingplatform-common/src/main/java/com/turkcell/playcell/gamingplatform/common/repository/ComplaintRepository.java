package com.turkcell.playcell.gamingplatform.common.repository;


import com.turkcell.playcell.gamingplatform.common.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

}
