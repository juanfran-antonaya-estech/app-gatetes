import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.estech.myapplication.data.models.breeds.Breed
import es.estech.myapplication.databinding.HolderBreedsBinding
import es.estech.myapplication.ui.adapters.OnHolderClick

class BreedListAdapter(var listado: ArrayList<Breed>, val listener: OnHolderClick) :
    RecyclerView.Adapter<BreedListAdapter.MiCelda>(), Filterable {
    //Your holder here
        var listaCopia = ArrayList<Breed>()
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

    fun actualizarListado(listado: ArrayList<Breed>){
        this.listado = listado
        this.listaCopia = listado
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val resultados = ArrayList<Breed>()
                val busqueda = constraint.toString().lowercase().trim()
                listaCopia.forEach {
                    if(it.name != null){
                        val name = it.name.lowercase()
                        if(name.contains(busqueda)){
                            resultados.add(it)
                        }
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = resultados
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                val resultados = results?.values as ArrayList<Breed>
                this@BreedListAdapter.listado = resultados
                notifyDataSetChanged()
            }

        }
    }


}