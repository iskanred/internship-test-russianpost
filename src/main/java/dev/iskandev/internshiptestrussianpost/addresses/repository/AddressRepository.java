package dev.iskandev.internshiptestrussianpost.addresses.repository;

import dev.iskandev.internshiptestrussianpost.addresses.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository for model {@link Address}
 */
public interface AddressRepository extends JpaRepository<Address, UUID> {

}
