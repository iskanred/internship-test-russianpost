package dev.iskandev.internshiptestrussianpost.addresses.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Thrown to indicate that request for creating/updating address is bad
 *  because the address has incorrect structure (e.g. non-null field has actual null value)
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Address Bad Request")
public class AddressBadRequestException extends Exception {

}
