package com.byfood.api.model;

import com.byfood.api.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, length = 30)
    private String phone;

    @Column(name = "whatsapp_number", nullable = false, length = 30)
    private String whatsappNumber;

    @Column(name = "opening_hours", length = 255)
    private String openingHours;
}
