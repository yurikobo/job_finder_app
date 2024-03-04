package com.example.kotlincourse.data.adapters

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.Year
import java.time.format.DateTimeFormatter

class YearAdapter : KSerializer<Year> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor(
            "com.example.kotlincourse.domain.adapters.YearAdapter",
            PrimitiveKind.STRING
        )

    override fun deserialize(decoder: Decoder): Year =
        Year.parse(decoder.decodeString(), DateTimeFormatter.ofPattern("yyyy"))

    override fun serialize(encoder: Encoder, value: Year) {
        encoder.encodeString(value.toString())
    }

}
