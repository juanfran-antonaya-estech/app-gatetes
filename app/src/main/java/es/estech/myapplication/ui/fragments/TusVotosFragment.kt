package es.estech.myapplication.ui.fragments

import AdaptadorVotos
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import es.estech.myapplication.R
import es.estech.myapplication.data.models.votes.Votes
import es.estech.myapplication.databinding.FragmentTusVotosBinding
import es.estech.myapplication.ui.MyViewModel
import java.util.ArrayList


class TusVotosFragment : Fragment() {
    private lateinit var binding: FragmentTusVotosBinding
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


        viewModel.observeVotos().observe(viewLifecycleOwner){
            it?.let { dis -> updateAdapter(dis) }
        }
    }

    private fun updateAdapter(lista: ArrayList<Votes>) {
        AdaptadorVotos.viewModel = viewModel
        val listaordenada = ArrayList(lista.sortedBy { it.createdAt })
        val adaptadorVotos = AdaptadorVotos(listaordenada)
        val layoutManager = LinearLayoutManager(requireContext())

        binding.rvVotos.adapter = adaptadorVotos
        binding.rvVotos.layoutManager = layoutManager
    }
}