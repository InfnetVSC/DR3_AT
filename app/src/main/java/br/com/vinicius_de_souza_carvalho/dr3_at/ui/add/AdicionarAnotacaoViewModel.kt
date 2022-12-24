package br.com.vinicius_de_souza_carvalho.dr3_at.ui.add

import androidx.lifecycle.ViewModel
import br.com.vinicius_de_souza_carvalho.dr3_at.database.dao.AnotacaoDao
import br.com.vinicius_de_souza_carvalho.dr3_at.model.Anotacao

class AdicionarAnotacaoViewModel : ViewModel() {

    fun adicionar(userId: String, titulo: String, corpo: String) {
        AnotacaoDao.insert(Anotacao(userId, titulo, corpo))
    }


}