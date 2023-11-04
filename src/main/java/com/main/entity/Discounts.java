package com.main.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "discounts", schema = "solardb", catalog = "")
public class Discounts {

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
    private LocalDateTime startUse;

    @Basic
    @Column(name = "end_use", nullable = true)
    private LocalDateTime endUse;

    @Basic
    @Column(name = "discount_description", nullable = true, length = 255)
    private String discountDescription;

    @Basic
    @Column(name = "is_active", nullable = true)
    private Boolean isActive;

    @OneToMany(mappedBy = "discountsByDiscountId")
    @JsonManagedReference
    private Collection<Orders> ordersById;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Discounts that = (Discounts) o;
//
//        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
//        if (id != null ? !id.equals(that.id) : that.id != null) return false;
//        if (startUse != null ? !startUse.equals(that.startUse) : that.startUse != null) return false;
//        if (endUse != null ? !endUse.equals(that.endUse) : that.endUse != null) return false;
//        if (discountDescription != null ? !discountDescription.equals(that.discountDescription) : that.discountDescription != null)
//            return false;
//        if (isActive != that.isActive) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
//        result = 31 * result + (startUse != null ? startUse.hashCode() : 0);
//        result = 31 * result + (endUse != null ? endUse.hashCode() : 0);
//        result = 31 * result + (discountDescription != null ? discountDescription.hashCode() : 0);
//        result = 31 * result + (isActive ? 1 : 0);
//        return result;
//    }


}
