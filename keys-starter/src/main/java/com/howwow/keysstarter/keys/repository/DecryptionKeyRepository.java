package com.howwow.keysstarter.keys.repository;


import com.howwow.keysstarter.keys.entity.DecryptionKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DecryptionKeyRepository extends JpaRepository<DecryptionKey, String> {
}
