import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.estech.myapplication.data.models.breeds.Breed
import es.estech.myapplication.databinding.HolderBreedsBinding
import es.estech.myapplication.ui.adapters.OnHolderClick

class BreedListAdapter(var listado: ArrayList<Breed>, val listener: OnHolderClick) :
    RecyclerView.Adapter<BreedListAdapter.MiCelda>() {
    //Your holder here
    inner class MiCelda(var binding: HolderBreedsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderBreedsBinding.inflate(layoutInflater, parent, false)
        return MiCelda(binding)
    }

    override fun getItemCount(): Int {
        return listado.size
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val raza = listado[position]

        with(holder.binding){
            tvNombreRazaLista.text = raza.name
            tvOrigenLista.text = raza.origin
        }

        holder.itemView.setOnClickListener{
            listener.click(raza, position)
        }
    }

    private fun adaptRefToImg(name: String): String {
        return "https://cdn2.thecatapi.com/images/${name}.jpg"
    }


}