package es.estech.myapplication.ui.fragments

import BreedListAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import es.estech.myapplication.R
import es.estech.myapplication.data.models.breeds.Breed
import es.estech.myapplication.data.retrofit.ApiResult
import es.estech.myapplication.databinding.FragmentListaGatosBinding
import es.estech.myapplication.ui.MyViewModel
import es.estech.myapplication.ui.adapters.OnHolderClick

class ListaGatosFragment : Fragment() {

    private var _binding: FragmentListaGatosBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MyViewModel>()
    private var adapter = BreedListAdapter(ArrayList(), object : OnHolderClick{
        override fun click(raza: Breed, position: Int) {
            viewModel.setRaza(raza)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaGatosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarAdaptador()

        viewModel.dameRazas().observe(viewLifecycleOwner){
            when(it) {
                is ApiResult.Success -> {
                    it.data?.let { data -> updateAdapter(data) }
                    binding.progressBar.visibility = ProgressBar.INVISIBLE
                }
                is ApiResult.ApiError -> {
                    val error = it.errorData
                    binding.progressBar.visibility = ProgressBar.INVISIBLE
                }
                is ApiResult.Loading -> {
                    binding.progressBar.visibility = ProgressBar.VISIBLE
                }

            }
        }

        binding.svBreeds.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
    }

    private fun updateAdapter(data: ArrayList<Breed>) {
        adapter.actualizarListado(data)
    }

    private fun configurarAdaptador() {
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