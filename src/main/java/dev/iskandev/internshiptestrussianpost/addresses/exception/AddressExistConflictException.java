package dev.iskandev.internshiptestrussianpost.addresses.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown to indicate that address already exists in system.
 */
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Address Already Exists")
public class AddressExistConflictException extends Exception {

}
