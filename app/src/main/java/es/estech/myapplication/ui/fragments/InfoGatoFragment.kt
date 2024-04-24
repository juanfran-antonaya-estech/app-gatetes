package es.estech.myapplication.ui.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.text.TextDirectionHeuristics
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.squareup.picasso.Picasso
import es.estech.myapplication.R
import es.estech.myapplication.data.models.breeds.Breed
import es.estech.myapplication.databinding.FragmentInfoGatoBinding
import es.estech.myapplication.ui.MyViewModel

class InfoGatoFragment : Fragment() {

    private var _binding: FragmentInfoGatoBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoGatoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRaza().observe(viewLifecycleOwner){
            llenarDatos(it)
        }
    }

    private fun llenarDatos(raza: Breed) {
        binding.llStatsGato.removeAllViews()

        binding.tvRazaName.text = raza.name
        binding.tvOrigen.text = raza.origin
        binding.tvRazaDetails.text = raza.description

        addStat("Dentro de casa", raza.indoor)
        addStat("Adaptabilidad", raza.adaptability)
        addStat("Nivel de afección", raza.affectionLevel)
        addStat("Amigable de los niños", raza.childFriendly)
        addStat("Amigable de los perros", raza.dogFriendly)
        addStat("Nivel de energía", raza.energyLevel)
        addStat("Problemas de salud", raza.healthIssues)
        addStat("Limpieza", raza.grooming)
        addStat("Inteligencia", raza.intelligence)
        addStat("Necesidades Sociales", raza.socialNeeds)
        addStat("Amigable de los extraños", raza.strangerFriendly)
        addStat("Rareza", raza.rare)
        addStat("Rex", raza.rex)


        val include = binding.incluye

        viewModel.setRandomImage(raza.id)

        viewModel.observeImages().observe(viewLifecycleOwner){gatete ->
            val include = binding.incluye

            Picasso.get()
                .load(gatete.url)
                .placeholder(R.drawable.cargando)
                .error(R.drawable.cuatrocerocuatro)
                .into(include.ivGatete)

            include.fabDown.setOnClickListener{
                viewModel.votarRaza(gatete.id,"wanfra", -1)
                viewModel.setRandomImage(raza.id)
            }
            include.fabUp.setOnClickListener{
                viewModel.votarRaza(gatete.id,"wanfra", 1)
                viewModel.setRandomImage(raza.id)
            }

        }
    }

    private fun addStat(clave: String, valor: Int) {
        val hl = LinearLayout(requireContext())
        hl.orientation = LinearLayout.HORIZONTAL
        val tvKey = TextView(requireContext())
        val tvValue = TextView(requireContext())

        tvKey.setTypeface(null, Typeface.BOLD)
        tvKey.text = clave
        tvValue.text = valor.toString()

        tvKey.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1F)
        tvValue.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1F)

        tvValue.textAlignment = View.TEXT_ALIGNMENT_TEXT_END

        hl.addView(tvKey)
        hl.addView(tvValue)
        binding.llStatsGato.addView(hl)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}