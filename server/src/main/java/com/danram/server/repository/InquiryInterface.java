package com.danram.server.repository;

import com.danram.server.domain.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryInterface extends JpaRepository<Inquiry, Long> {
}
