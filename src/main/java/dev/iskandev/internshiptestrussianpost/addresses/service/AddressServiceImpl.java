package dev.iskandev.internshiptestrussianpost.addresses.service;

import dev.iskandev.internshiptestrussianpost.addresses.exception.AddressBadRequestException;
import dev.iskandev.internshiptestrussianpost.addresses.exception.AddressExistConflictException;
import dev.iskandev.internshiptestrussianpost.addresses.model.Address;
import dev.iskandev.internshiptestrussianpost.addresses.repository.AddressRepository;
import dev.iskandev.internshiptestrussianpost.addresses.exception.AddressNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    private final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    private final AddressRepository repository;

    @Autowired
    public AddressServiceImpl(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address getAddressById(UUID id) throws AddressNotFoundException {
        /* Get address by id from repository; if no such address => throw exception */
        var addressOptional = repository.findById(id);
        if (addressOptional.isEmpty()) {
            logger.info("Address Not Found for id " + id);
            throw new AddressNotFoundException();
        }
        return addressOptional.get();
    }

    @Override
    public void updateAddressById(UUID id, Address address) throws AddressNotFoundException, AddressBadRequestException {
        if (!repository.existsById(id)) {
            logger.info("Address Not Found for id " + id);
            throw new AddressNotFoundException();
        }

        address.setId(id); // set input id to input address to save it in repository

        // if input address is NOT formed correctly => throw exception
        if (address.isIncorrect()) {
            logger.info("Address Bad Request for address " + address);
            throw new AddressBadRequestException();
        }

        repository.save(address);
    }

    @Override
    public void removeAddressById(UUID id) throws AddressNotFoundException {
        if (!repository.existsById(id)) {
            logger.info("Address Not Found for id " + id);
            throw new AddressNotFoundException();
        }
        repository.deleteById(id);
    }

    @Override
    public List<Address> getAddresses(int page, int size) {
        var addresses = repository.findAll();
        int fromIndex = page * size; // index of first entry on page
        int toIndex = fromIndex + size; // index of last entry on page

        // if there are no more entries for such page number => return empty page
        if (fromIndex > addresses.size())
            return new ArrayList<>();
        // if the last page contains fewer entries than page's size => shrink its size
        if (toIndex > addresses.size())
            toIndex = addresses.size();

        return addresses.subList(fromIndex, toIndex);
    }

    @Override
    public void createAddress(Address address) throws AddressExistConflictException, AddressBadRequestException {
        // if input address exists already => throw exception
        if (repository.existsById(address.getId())) {
            logger.info("Address Exists Conflict for id " + address.getId());
            throw new AddressExistConflictException();
        }
        // if input address has NOT formed correctly => throw exception
        if (address.isIncorrect()) {
            logger.info("Address Bad Request for address " + address);
            throw new AddressBadRequestException();
        }

        repository.save(address);
    }
}
