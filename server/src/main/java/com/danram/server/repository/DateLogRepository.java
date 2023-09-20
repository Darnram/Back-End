package com.danram.server.repository;

import com.danram.server.domain.DateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DateLogRepository extends JpaRepository<DateLog, Long> {
}
