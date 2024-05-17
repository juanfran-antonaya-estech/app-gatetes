package es.estech.myapplication.ui.fragments

import AdaptadorVotos
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import es.estech.myapplication.R
import es.estech.myapplication.data.models.votes.Votes
import es.estech.myapplication.databinding.FragmentTusVotosBinding
import es.estech.myapplication.ui.MyViewModel
import es.estech.myapplication.ui.adapters.OnImageClick
import kotlin.collections.ArrayList


class TusVotosFragment : Fragment() {
    private lateinit var binding: FragmentTusVotosBinding
    private val adaptador : AdaptadorVotos = AdaptadorVotos(ArrayList(), object : OnImageClick{
        override fun click(imageId: String, position: Int) {
            viewModel.setRazaPorFoto(imageId)
            findNavController().navigate(R.id.action_tusVotosFragment_to_SecondFragment)
        }
        })
    val viewModel by activityViewModels<MyViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTusVotosBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter(ArrayList())
        viewModel.observeVotos().observe(viewLifecycleOwner){
            it?.let { dis -> updateAdapter(dis) }
        }
    }
    private fun updateAdapter(lista: ArrayList<Votes>) {
        adaptador.actualizarLista(ArrayList(lista.sortedByDescending { it.createdAt }))
    }

    private fun setupAdapter(lista: ArrayList<Votes>) {
        AdaptadorVotos.viewModel = viewModel
        val layoutManager = LinearLayoutManager(requireContext())

        binding.rvVotos.adapter = adaptador
        binding.rvVotos.layoutManager = layoutManager
    }
}