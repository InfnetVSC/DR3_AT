package br.com.vinicius_de_souza_carvalho.dr3_at.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

data class Anotacao(
    val userId: String = "",
    var titulo: String = "",
    var corpo: String = "",
    val data: Timestamp = Timestamp.now(),
    @DocumentId val id: String? = null
)
