package com.example.shopapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name ="orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name ="full_name")
  private String fullName;

  @Column(name = "email", length = 100)
  private String email;

  @Column(name = "phome_number", length = 100, nullable = false)
  private String phoneNumber;

  @Column(name = "address", length = 100)
  private String address;

  @Column(name = "note", length = 100)
  private String note;

  @Column(name ="order_Date")
  private LocalDateTime orderDate;

  @Column(name = "status")
  private String status;

  @Column(name = "total_money")
  private Integer totalMoney;

  @Column(name = "shipping_method")
  private String shippingMethod;

  @Column(name = "shipping_address")
  private String shippingAdress;

  @Column(name = "shipping_date")
  private Date shippingDate;

  @Column(name = "tracking_number")
  private String trackingNumber;

  @Column(name = "payment_method")
  private String paymentMethod;

//  @Column(name = "payment_status")
//  private String paymentStatus;
//
//  @Column(name = "payment_date")
//  private Date paymentDate;

  @Column(name = "active")
  private Boolean active;

}
