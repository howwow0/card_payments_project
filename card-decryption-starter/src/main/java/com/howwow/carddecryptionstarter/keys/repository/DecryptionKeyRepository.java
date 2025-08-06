package com.howwow.carddecryptionstarter.keys.repository;


import com.howwow.carddecryptionstarter.keys.entity.DecryptionKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DecryptionKeyRepository extends JpaRepository<DecryptionKey, String> {
}
