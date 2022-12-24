package br.com.vinicius_de_souza_carvalho.dr3_at.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.vinicius_de_souza_carvalho.dr3_at.database.dao.AnotacaoDao
import br.com.vinicius_de_souza_carvalho.dr3_at.model.Anotacao

class EditaAnotacaoViewModel : ViewModel() {
    private val _anotacao = MutableLiveData<Anotacao>()
    val anotacao: LiveData<Anotacao> = _anotacao

    fun getAnotacao(docId: String) {
        val snapshot = AnotacaoDao.read(docId)
        snapshot.addOnSuccessListener {
            _anotacao.value = it.toObject(Anotacao::class.java)
        }
    }

    fun salvar(titulo: String, corpo: String) {
        _anotacao.value?.titulo = titulo
        _anotacao.value?.corpo = corpo
        AnotacaoDao.update(_anotacao.value!!)
    }
}