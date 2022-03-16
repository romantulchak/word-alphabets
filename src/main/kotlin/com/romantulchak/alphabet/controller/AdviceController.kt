package com.romantulchak.alphabet.controller

import com.romantulchak.alphabet.exception.AlphabetAlreadyExistsException
import com.romantulchak.alphabet.exception.LanguageAlreadyExistsException
import com.romantulchak.alphabet.exception.LanguageNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@RestControllerAdvice
class AdviceController : ResponseEntityExceptionHandler() {
    @ExceptionHandler(LanguageAlreadyExistsException::class)
    fun handleLanguageAlreadyExistsException(ex: LanguageAlreadyExistsException?, webRequest: WebRequest?): ResponseEntity<*>? {
        val body: Map<String, Any?>? = ex?.let { getBody(it) }
        return ex?.let {
            handleExceptionInternal(
                it, body, HttpHeaders(), HttpStatus.BAD_REQUEST,
                webRequest!!
            )
        }
    }

    @ExceptionHandler(LanguageNotFoundException::class)
    fun handleLanguageNotFoundException(ex: LanguageNotFoundException?, webRequest: WebRequest?): ResponseEntity<*>? {
        val body: Map<String, Any?>? = ex?.let { getBody(it) }
        return ex?.let {
            handleExceptionInternal(
                it, body, HttpHeaders(), HttpStatus.BAD_REQUEST,
                webRequest!!
            )
        }
    }
    @ExceptionHandler(AlphabetAlreadyExistsException::class)
    fun handleAlphabetAlreadyExistsException(ex: AlphabetAlreadyExistsException?, webRequest: WebRequest?): ResponseEntity<*>? {
        val body: Map<String, Any?>? = ex?.let { getBody(it) }
        return ex?.let {
            handleExceptionInternal(
                it, body, HttpHeaders(), HttpStatus.BAD_REQUEST,
                webRequest!!
            )
        }
    }

    private fun getBody(ex: RuntimeException): Map<String, Any?> {
        val body: MutableMap<String, Any?> = HashMap()
        body["message"] = ex.message
        body["date"] = LocalDateTime.now()
        return body
    }
}