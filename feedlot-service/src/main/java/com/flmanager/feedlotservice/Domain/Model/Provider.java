package com.flmanager.feedlotservice.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "providers", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class Provider {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String idProvider;
    private String name;
    private String lastName;
    private String address;
    private String phone;
    @Column(unique = true, name = "email")
    private String email;
    private Date deletedAt;
}
