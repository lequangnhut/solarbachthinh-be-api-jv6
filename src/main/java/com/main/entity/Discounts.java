package com.main.entity;

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
@Table(name = "discounts", schema = "solardb", catalog = "")
public class Discounts {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, length = 20)
    private String id;

    @Basic
    @Column(name = "discount_cost", nullable = true, precision = 0)
    private Integer discountCost;

    @Basic
    @Column(name = "quantity", nullable = true)
    private Integer quantity;

    @Basic
    @Column(name = "start_use", nullable = true)
    private Timestamp startUse;

    @Basic
    @Column(name = "end_use", nullable = true)
    private Timestamp endUse;

    @Basic
    @Column(name = "discount_description", nullable = true, length = 255)
    private String discountDescription;

    @Basic
    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "discountsByDiscountId")
    @JsonManagedReference
    private Collection<Orders> ordersById;
}
