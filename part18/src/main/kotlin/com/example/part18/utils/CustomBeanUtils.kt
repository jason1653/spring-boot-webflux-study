package com.example.part18.utils

import org.springframework.beans.BeanWrapper
import org.springframework.beans.BeanWrapperImpl
import org.springframework.stereotype.Component

@Component
class CustomBeanUtils<T> {
    fun copyNonNullProperties(source: T?, destination: T?): T? {
        if (source == null || destination == null || source.javaClass != destination.javaClass) {
            return null
        }
        val src: BeanWrapper = BeanWrapperImpl(source)
        val dest: BeanWrapper = BeanWrapperImpl(destination)
        for (property in source.javaClass.getDeclaredFields()) {
            val providedObject = src.getPropertyValue(property.name)
            if (providedObject != null && providedObject !is Collection<*>) {
                dest.setPropertyValue(
                    property.name,
                    providedObject,
                )
            }
        }
        return destination
    }
}
