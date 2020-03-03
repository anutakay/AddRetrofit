package com.issart.addretrofit.core

interface EntityConverter<ENTITY, EXTERNAL> {
    fun toEntity(value: EXTERNAL): ENTITY
    fun fromEntity(value: ENTITY): EXTERNAL
}