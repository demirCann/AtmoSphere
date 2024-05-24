package com.demircandemir.atmosphere.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Minutely(
    val dt: Int,
    val precipitation: Int
)