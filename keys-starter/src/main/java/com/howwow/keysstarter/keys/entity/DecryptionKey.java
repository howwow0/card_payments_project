package com.howwow.keysstarter.keys.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "encryption_keys")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DecryptionKey {
    @Id
    private String id;

    @Column(nullable = false)
    private String keyValue;

}
