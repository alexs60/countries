package com.alessandrofarandagancio.sampleapp.domain

interface BaseError


sealed interface Resource<out D, out E : BaseError> {
    class Success<out D, out E : BaseError>(val data: D) : Resource<D, E>
    class Failure<out D, out E : BaseError>(val error: E) : Resource<D, E>
}