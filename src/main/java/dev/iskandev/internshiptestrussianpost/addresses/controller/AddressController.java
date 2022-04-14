package dev.iskandev.internshiptestrussianpost.addresses.controller;

import dev.iskandev.internshiptestrussianpost.addresses.exception.AddressBadRequestException;
import dev.iskandev.internshiptestrussianpost.addresses.exception.AddressExistConflictException;
import dev.iskandev.internshiptestrussianpost.addresses.exception.AddressNotFoundException;
import dev.iskandev.internshiptestrussianpost.addresses.model.Address;
import dev.iskandev.internshiptestrussianpost.addresses.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/${server.version_based_url_prefix}/addresses")
public class AddressController {

    private final Logger logger = LoggerFactory.getLogger(AddressController.class);

    private final AddressService service;

    @Autowired
    public AddressController(AddressService service) {
        this.service = service;
    }

    /**
     * Return address by its id.
     * <br>Possible response status codes:<br>
     * <ol>
     *     <li>200 OK - if operation has been performed successfully</li>
     *     <li>400 Bad Request - if input data is not valid</li>
     *     <li>404 Not Found - if address with specified id is not found</li>
     *     <li>500 Internal Server Error</li>
     * </ol>
     * @param id is id to search address by
     * @return address by its id
     * @throws AddressNotFoundException if there is no address with specified id in repository
     */
    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable UUID id) throws AddressNotFoundException {
        logger.info("GET /v1.0/addresses/{id} request for getting the address with id " + id + " is received");
        return service.getAddressById(id);
    }

    /**
     * Update address that is specified by id.
     * <br>Possible response status codes:<br>
     * <ol>
     *     <li>204 No Content - if operation has been performed successfully</li>
     *     <li>400 Bad Request - if input data is not valid</li>
     *     <li>404 Not Found - if address with specified id is not found</li>
     *     <li>500 Internal Server Error</li>
     * </ol>
     * @param id is id of address to update
     * @param address is new address to update the old one to
     * @throws AddressNotFoundException if there is no address with specified id in repository
     * @throws AddressBadRequestException if request if bad i.e. input address has incorrect structure
     */
    @PutMapping(value = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAddressById(@PathVariable UUID id, @RequestBody Address address) throws AddressNotFoundException, AddressBadRequestException {
        logger.info("PUT /v1.0/addresses/{id} request for updating the address with id " + id + " is received");
        service.updateAddressById(id, address);
    }

    /**
     * Remove address that is specified by id.
     * <br>Possible response status codes:<br>
     * <ol>
     *     <li>204 No Content - if operation has been performed successfully</li>
     *     <li>400 Bad Request - if input data is not valid</li>
     *     <li>404 Not Found - if address with specified id is not found</li>
     *     <li>500 Internal Server Error</li>
     * </ol>
     * @param id is id of address to remove
     * @throws AddressNotFoundException if there is no address with specified id in repository
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAddressById(@PathVariable UUID id) throws AddressNotFoundException {
        logger.info("DELETE /v1.0/addresses/{id} request for removing the address with id " + id + " is received");
        service.removeAddressById(id);
    }

    /**
     * Return paginated list of all existing addresses.
     * <br>Possible response status codes:<br>
     * <ol>
     *     <li>200 OK - if operation has been performed successfully</li>
     *     <li>400 Bad Request - if input data is not valid</li>
     *     <li>500 Internal Server Error</li>
     * </ol>
     * @param page is number of page to show
     * @param pageSize is the maximum number of records on page
     * @return paginated list of all existing addresses
     * @throws ResponseStatusException with code:
     *  <ul>
     *      <li>400 Bad Request - if some request parameters have incorrect values</li>
     *  </ul>
     */
    @GetMapping
    public List<Address> getAddresses(@RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "20") int pageSize) throws ResponseStatusException {
        logger.info("GET /v1.0/addresses request for getting all addresses is received with parameters page = " + page + "; size = " + pageSize);
        if (pageSize < 1 || pageSize > 1000) {
            var exception = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Size of page must be in range [1, 1000], but got: " + pageSize);
            logger.info("Incorrect size parameter", exception);
            throw exception;
        }
        return service.getAddresses(page, pageSize);
    }

    /**
     * Create new address with specified parameters and store it.
     * <br>Possible response status codes:</br>
     * <ol>
     *     <li>204 No Content - if operation has been performed successfully</li>
     *     <li>400 Bad Request - if input data is not valid</li>
     *     <li>409 Conflict - if address with specified id already exists</li>
     *     <li>500 Internal Server Error</li>
     * </ol>
     * @param address is address to create and store
     * @throws AddressExistConflictException if such an address already exists in repository
     * @throws AddressBadRequestException if request if bad i.e. input address has incorrect structure
     */
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createAddress(@RequestBody Address address) throws AddressExistConflictException, AddressBadRequestException {
        logger.info("POST /v1.0/addresses request for creating a new address is received");
        service.createAddress(address);
    }
}
