package dev.iskandev.internshiptestrussianpost.addresses.service;

import dev.iskandev.internshiptestrussianpost.addresses.exception.AddressBadRequestException;
import dev.iskandev.internshiptestrussianpost.addresses.exception.AddressExistConflictException;
import dev.iskandev.internshiptestrussianpost.addresses.exception.AddressNotFoundException;
import dev.iskandev.internshiptestrussianpost.addresses.model.Address;

import java.util.List;
import java.util.UUID;

public interface AddressService {

    /**
     * Return address by its id.
     * @param id is id to search address by
     * @return address by its i
     * @throws AddressNotFoundException if there is no address with specified id in repository
     */
    Address getAddressById(UUID id) throws AddressNotFoundException;

    /**
     * Update address that is specified by id.
     * @param id is id of address to update
     * @param address is new address to update the old one t
     * @throws AddressNotFoundException if there is no address with specified id in repository
     * @throws AddressBadRequestException if {@code address} has incorrect structure
     */
    void updateAddressById(UUID id, Address address) throws AddressNotFoundException, AddressBadRequestException;

    /**
     * Remove address that is specified by id.
     * @param id is id of address to remove
     * @throws AddressNotFoundException if there is no address with specified id in repository
     */
    void removeAddressById(UUID id) throws AddressNotFoundException;

    /**
     * Return paginated list of stored addresses.
     * @param page is number of page to return
     * @param size is the maximum number of records on page
     * @return paginated list of stored addresses
     */
    List<Address> getAddresses(int page, int size);

    /**
     * Create new address with specified parameters and store it in repository.
     * @param address is address to create and store
     * @throws AddressExistConflictException if such an address already exists in repository
     * @throws AddressBadRequestException if {@code address} has incorrect structure
     */
    void createAddress(Address address) throws AddressExistConflictException, AddressBadRequestException;
}
