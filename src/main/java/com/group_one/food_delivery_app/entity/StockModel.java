package com.group_one.food_delivery_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "stocks")
public class StockModel
{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "stock_id")
        private Long stockId;

        @Column(name = "item_name", nullable = false)
        private String itemName;

        @Column(name = "quantity", nullable = false)
        private Integer quantity;

        @Column(name = "unit", nullable = false)
        private String unit;  // e.g. "kg", "pcs", "bottles"

        @Column(name = "supplier_id")
        private Long supplierId;

        @Column(name = "price_per_unit")
        private Double pricePerUnit;

        @Column(name = "last_restocked_at")
        private LocalDateTime lastRestockedAt;

        @Column(name = "created_at", updatable = false)
        private LocalDateTime createdAt;

        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

        @PrePersist
        protected void onCreate() {
            createdAt = LocalDateTime.now();
            updatedAt = LocalDateTime.now();
        }

        @PreUpdate
        protected void onUpdate() {
            updatedAt = LocalDateTime.now();
        }
    }
