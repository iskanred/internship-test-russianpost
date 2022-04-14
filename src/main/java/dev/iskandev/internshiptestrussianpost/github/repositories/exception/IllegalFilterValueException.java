package dev.iskandev.internshiptestrussianpost.github.repositories.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Filter Bad Request")
public class IllegalFilterValueException extends Exception {

}
