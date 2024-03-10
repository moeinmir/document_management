package com.sts.document_management.utils

import kotlin.reflect.KClass

interface Mapper {
    fun <T : Any, R : Any> map(source: T, destinationClass: KClass<R>): R

}