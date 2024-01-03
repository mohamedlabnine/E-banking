package com.auto.entity.Entities;

;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Admins")
public class Admin extends User {
}
