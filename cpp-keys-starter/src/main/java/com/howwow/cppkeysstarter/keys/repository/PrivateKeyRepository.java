package com.howwow.cppkeysstarter.keys.repository;


import com.howwow.cppkeysstarter.keys.entity.PrivateKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivateKeyRepository extends JpaRepository<PrivateKey, String> {
}
