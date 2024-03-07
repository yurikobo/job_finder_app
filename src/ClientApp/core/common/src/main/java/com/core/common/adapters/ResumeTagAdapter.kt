package com.core.common.adapters

//import com.core.common.models.CandidateLevel
//import com.core.common.models.Profession
//import com.core.common.models.ResumeTag
//import kotlinx.serialization.KSerializer
//import kotlinx.serialization.descriptors.PrimitiveKind
//import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
//import kotlinx.serialization.descriptors.SerialDescriptor
//import kotlinx.serialization.encoding.Decoder
//import kotlinx.serialization.encoding.Encoder
//
//class ResumeTagAdapter : KSerializer<ResumeTag> {
//    override val descriptor: SerialDescriptor
//        get() = PrimitiveSerialDescriptor(
//            "com.core.common.adapters.ResumeTagAdapter",
//            PrimitiveKind.STRING
//        )
//
//    override fun deserialize(decoder: Decoder): ResumeTag {
//        return decoder.decodeJsonElement().jsonPrimitive.content
//            .let { toEnum(it) }
//    }
//
//    override fun serialize(encoder: Encoder, value: ResumeTag) {
//        when (value) {
//            is CandidateLevel -> encoder.encodeString(value.toString())
//            is Profession -> encoder.encodeString(value.toString())
//        }
//
//    }
//
//
//}
