package com.howwow.keysstarter.keys.repository;


import com.howwow.keysstarter.keys.entity.PrivateKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivateKeyRepository extends JpaRepository<PrivateKey, String> {
}
