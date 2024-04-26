import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import es.estech.myapplication.R
import es.estech.myapplication.data.models.votes.Votes
import es.estech.myapplication.databinding.HolderVotoBinding
import es.estech.myapplication.ui.MyViewModel
import es.estech.myapplication.ui.adapters.OnHolderClick
import es.estech.myapplication.ui.adapters.OnImageClick
import java.time.Instant
import java.util.Date
import java.util.EventListener

class AdaptadorVotos(var listado: ArrayList<Votes>,val listener: OnImageClick) :
    RecyclerView.Adapter<AdaptadorVotos.MiCelda>() {

        companion object {
            lateinit var viewModel : MyViewModel
        }
    inner class MiCelda(var binding: HolderVotoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderVotoBinding.inflate(layoutInflater, parent, false)
        return MiCelda(binding)
    }

    override fun getItemCount(): Int {
        return listado.size
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val voto = listado[position]

        with(holder.binding){
            tvVoto.text = if (voto.value > 0){
                includ.fabUp.visibility = FloatingActionButton.INVISIBLE
                includ.fabDown.visibility = FloatingActionButton.VISIBLE
                "Upvote ^^"
            } else {
                includ.fabUp.visibility = FloatingActionButton.VISIBLE
                includ.fabDown.visibility = FloatingActionButton.INVISIBLE
                "Downvote :("
            }

            fabDelete.setOnClickListener{
                viewModel.borrarRaza(voto.id)
                notifyItemRemoved(position)
                Thread.sleep(500)
                viewModel.actualizarvotos()
            }

            includ.fabUp.setOnClickListener{
                viewModel.votarRaza(voto.imageId,voto.subId,1)
                Thread.sleep(500)
                viewModel.actualizarvotos()
            }

            includ.fabDown.setOnClickListener{
                viewModel.votarRaza(voto.imageId,voto.subId,-1)
                Thread.sleep(500)
                viewModel.actualizarvotos()
            }

            tvFechaVoto.text = voto.createdAt

            Picasso.get()
                .load(voto.image.url)
                .placeholder(R.drawable.cargando)
                .error(R.drawable.cuatrocerocuatro)
                .into(includ.ivGatete)
            includ.ivGatete.setOnClickListener{
                listener.click(voto.imageId, position)
            }
        }

    }


}