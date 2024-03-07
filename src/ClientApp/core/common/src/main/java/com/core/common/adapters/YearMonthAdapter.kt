package com.core.common.adapters

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class YearMonthAdapter : KSerializer<YearMonth> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor(
            "com.core.common.adapters.YearMonthAdapter",
            PrimitiveKind.STRING
        )

    override fun deserialize(decoder: Decoder): YearMonth =
        YearMonth.parse(decoder.decodeString(), DateTimeFormatter.ofPattern("yyyy-MM"))


    override fun serialize(encoder: Encoder, value: YearMonth) {
        encoder.encodeString(value.toString())
    }

}
