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
@Table(name = "orders", schema = "solardb")
public class Orders {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, length = 20)
    private String id;

    @Basic
    @Column(name = "user_id", nullable = true)
    private Integer userId;

    @Basic
    @Column(name = "payment_type", nullable = true)
    private Boolean paymentType;

    @Basic
    @Column(name = "order_status", nullable = true, length = 30)
    private String orderStatus;

    @Basic
    @Column(name = "discount_id", nullable = true, length = 20)
    private String discountId;

    @Basic
    @Column(name = "to_name", nullable = true, length = 100)
    private String toName;

    @Basic
    @Column(name = "to_phone", nullable = true, length = 20)
    private String toPhone;

    @Basic
    @Column(name = "to_city", nullable = true, length = 50)
    private String toCity;

    @Basic
    @Column(name = "to_district", nullable = true, length = 50)
    private String toDistrict;

    @Basic
    @Column(name = "to_ward", nullable = true, length = 50)
    private String toWard;

    @Basic
    @Column(name = "to_address", nullable = true, length = -1)
    private String toAddress;

    @Basic
    @Column(name = "weight", nullable = true)
    private Integer weight;

    @Basic
    @Column(name = "length", nullable = true)
    private Integer length;

    @Basic
    @Column(name = "width", nullable = true)
    private Integer width;

    @Basic
    @Column(name = "height", nullable = true)
    private Integer height;

    @Basic
    @Column(name = "service_type_id", nullable = true)
    private Integer serviceTypeId;

    @Basic
    @Column(name = "service_id", nullable = true)
    private Integer serviceId;

    @Basic
    @Column(name = "required_note", nullable = true, length = 255)
    private String requiredNote;

    @Basic
    @Column(name = "sender_note", nullable = true, length = 255)
    private String senderNote;

    @Basic
    @Column(name = "date_created", nullable = true)
    private Timestamp dateCreated;

    @OneToMany(mappedBy = "ordersByOrderId")
    @JsonManagedReference
    private Collection<Invoices> invoicesById;

    @OneToMany(mappedBy = "ordersByOrderId")
    @JsonManagedReference
    private Collection<OrderItems> orderItemsById;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    private Users usersByUserId;

    @ManyToOne
    @JoinColumn(name = "discount_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    private Discounts discountsByDiscountId;
}
