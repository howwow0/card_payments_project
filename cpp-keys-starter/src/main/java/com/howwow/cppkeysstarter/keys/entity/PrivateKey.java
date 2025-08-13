package com.howwow.cppkeysstarter.keys.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "private_keys")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrivateKey {
    @Id
    @Column(name = "key_name")
    private String id;

    @Column(nullable = false, name = "key_value")
    private String keyValue;

}
