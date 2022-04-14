package dev.iskandev.internshiptestrussianpost.addresses.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown to indicate that desired address has not been found in system.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Address Not Found")
public class AddressNotFoundException extends Exception {

}
