package br.com.vinicius_de_souza_carvalho.dr3_at.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.vinicius_de_souza_carvalho.dr3_at.database.dao.AnotacaoDao
import br.com.vinicius_de_souza_carvalho.dr3_at.model.Anotacao

class AnotacoesViewModel() : ViewModel() {
    private val _anotacoes = MutableLiveData<List<Anotacao>>()
    val anotacoes: LiveData<List<Anotacao>> = _anotacoes
    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg


    fun updateList(userId: String) {
        AnotacaoDao
            .listUserDocs(userId)
            .addOnSuccessListener {
                _anotacoes.value = it.toObjects(Anotacao::class.java)
            }
            .addOnFailureListener {
                _msg.value = it.message
            }
    }

    fun deleteItem(index: Int, userId: String) {
        try {
            val anotacao = _anotacoes.value?.get(index)
            AnotacaoDao.delete(anotacao)
            updateList(userId)
        } catch (e: Exception) {
            Log.d("Firestore Error", e.message.toString())
        }
    }
}