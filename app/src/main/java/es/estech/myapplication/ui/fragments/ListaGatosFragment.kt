package es.estech.myapplication.ui.fragments

import BreedListAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import es.estech.myapplication.R
import es.estech.myapplication.data.models.breeds.Breed
import es.estech.myapplication.databinding.FragmentListaGatosBinding
import es.estech.myapplication.ui.MyViewModel
import es.estech.myapplication.ui.adapters.OnHolderClick

class ListaGatosFragment : Fragment() {

    private var _binding: FragmentListaGatosBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaGatosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dameRazas().observe(viewLifecycleOwner){
            configurarAdaptador(it)
        }


    }

    private fun configurarAdaptador(it: ArrayList<Breed>) {
        val adapter = BreedListAdapter(it, object : OnHolderClick{
            override fun click(raza: Breed, position: Int) {
                viewModel.setRaza(raza)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }

        })
        val layoutManager = LinearLayoutManager(requireContext())

        binding.rvListaGatos.adapter = adapter
        binding.rvListaGatos.layoutManager = layoutManager

        binding.fabVotos.setOnClickListener{
            viewModel.actualizarvotos()
            findNavController().navigate(R.id.action_FirstFragment_to_tusVotosFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}