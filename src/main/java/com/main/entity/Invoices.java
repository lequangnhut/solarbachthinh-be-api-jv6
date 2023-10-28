package com.main.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoices", schema = "solardb", catalog = "")
public class Invoices {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, length = 20)
    private String id;

    @Basic
    @Column(name = "order_id", nullable = false, length = 20)
    private String orderId;

    @Basic
    @Column(name = "ship_cost", nullable = true, precision = 0)
    private Integer shipCost;

    @Basic
    @Column(name = "date_created", nullable = true)
    private Timestamp dateCreated;

    @OneToMany(mappedBy = "invoicesByInvoiceId")
    @JsonManagedReference
    private Collection<InvoiceItems> invoiceItemsById;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @JsonBackReference
    private Orders ordersByOrderId;
}
