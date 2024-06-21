package me.dio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.domain.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    boolean existsByAddressZipCode(Long long1);
}
