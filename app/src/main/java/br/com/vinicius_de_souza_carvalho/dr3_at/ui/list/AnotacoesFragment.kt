package br.com.vinicius_de_souza_carvalho.dr3_at.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.vinicius_de_souza_carvalho.dr3_at.R
import br.com.vinicius_de_souza_carvalho.dr3_at.adapter.AnotacoesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AnotacoesFragment : Fragment() {

    private lateinit var viewModel: AnotacoesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAdicionar: FloatingActionButton
    private lateinit var btnLogout: ImageButton
    private var auth = Firebase.auth
    private var user = auth.currentUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_anotacoes, container, false)
        exitIfNoUser()
        setupWidgets(view)
        setupViewModel(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.updateList(user!!.uid)

        fabAdicionar.setOnClickListener {
            findNavController().navigate(R.id.adicionarAnotacaoFragment)
        }

        btnLogout.setOnClickListener {
            auth.signOut()
            exitIfNoUser()
        }
    }

    private fun setupViewModel(view: View) {
        viewModel = ViewModelProvider(this).get(AnotacoesViewModel::class.java)
        viewModel.msg.observe(viewLifecycleOwner) {
            if (!it.isNullOrBlank()) Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
        }
        viewModel.anotacoes.observe(viewLifecycleOwner) {
            recyclerView.adapter = AnotacoesAdapter(it, this::deletaAnotacao, this::detalhaAnotacao)
        }
    }

    private fun setupWidgets(view: View) {
        recyclerView = view.findViewById(R.id.fragment_anotacoes_recycler_view)
        fabAdicionar = view.findViewById(R.id.fragment_anotacoes_fab_adicionar_anotacao)
        btnLogout = view.findViewById(R.id.fragment_anotacoes_btn_logout)
    }

    private fun deletaAnotacao(position: Int) {
        viewModel.deleteItem(position, user!!.uid)
    }

    private fun detalhaAnotacao(docId: String) {
        findNavController().navigate(
            R.id.detalhaAnotacaoFragment,
            bundleOf(getString(R.string.DOCUMENT_ID_REQUEST) to docId)
        )
    }

    private fun exitIfNoUser() {
        if(auth.currentUser == null) {
            findNavController().navigate(R.id.signInFragment)
        }
    }

}