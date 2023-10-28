package com.main.repository;

import com.main.entity.InvoiceItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItems, String> {
}
