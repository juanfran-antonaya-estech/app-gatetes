package es.estech.myapplication.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.estech.myapplication.data.Repository
import es.estech.myapplication.data.models.breeds.Breed
import es.estech.myapplication.data.models.catphoto.ImagenGato
import es.estech.myapplication.data.models.votes.VoteDeleteResponse
import es.estech.myapplication.data.models.votes.VoteResponse
import es.estech.myapplication.data.models.votes.VoteSend
import es.estech.myapplication.data.models.votes.Votes
import es.estech.myapplication.data.retrofit.ApiResult
import kotlinx.coroutines.launch
import java.lang.Exception

class MyViewModel : ViewModel() {
    val repo = Repository()

    private val actualImageLiveData: MutableLiveData<ImagenGato> = MutableLiveData()
    private val actualBreedLiveData: MutableLiveData<Breed> = MutableLiveData()
    private val votesLiveData: MutableLiveData<ArrayList<Votes>> = MutableLiveData()

    /*
    Estas funciones sirven para actualizar las fotos
     */
    fun actualizarvotos() {
        viewModelScope.launch() {
            val response = repo.dameVotos()
            if (response.isSuccessful) {
                votesLiveData.postValue(response.body())
            }
        }
    }

    fun observeVotos(): MutableLiveData<ArrayList<Votes>> {
        return votesLiveData
    }


    fun setRaza(raza: Breed) {
        actualBreedLiveData.value = raza
    }

    fun getRaza(): MutableLiveData<Breed> {
        return actualBreedLiveData
    }

    //Esta funcion obtiene una imagen aleatoria para que se pueda observar con otra función
    fun setRandomImage(breed: String) {
        viewModelScope.launch {
            val response = repo.dameFotoRaza(breed)

            if (response.isSuccessful) {
                response.body()?.let {
                    actualImageLiveData.postValue(it[0])
                }
            }
        }
    }

    fun observeImages(): MutableLiveData<ImagenGato> {
        return actualImageLiveData
    }

    fun dameRazas(): MutableLiveData<ApiResult<ArrayList<Breed>>> {
        val liveData = MutableLiveData<ApiResult<ArrayList<Breed>>>()
        liveData.postValue(ApiResult.Loading())

        try {
            viewModelScope.launch {
                val response = repo.dameRazas()

                if (response.code() == 200) {
                    response.body()?.let {
                        liveData.postValue(ApiResult.Success(it))
                    }
                } else {
                    val message = response.message()
                    liveData.postValue(ApiResult.ApiError(message))
                }

            }
        } catch ( e: Exception ) {
            e.message?.let { Log.d("error", it) }
        }

        return liveData
    }

    fun dameVotos(): MutableLiveData<ArrayList<Votes>> {
        val liveData = MutableLiveData<ArrayList<Votes>>()

        viewModelScope.launch {
            val response = repo.dameVotos()
            if (response.code() == 200) {
                response.body()?.let {
                    liveData.postValue(it)
                }
            }
        }
        return liveData
    }

    fun votarRaza(imageId: String, userId: String, vote: Int): MutableLiveData<VoteResponse> {

        val liveData = MutableLiveData<VoteResponse>()
        val voteSend = VoteSend(imageId, userId, vote)

        viewModelScope.launch {
            val response = repo.votaRaza(voteSend)
            if (response.code() == 201) {
                response.body()?.let {
                    liveData.postValue(response.body())
                }
            }
        }

        return liveData

    }

    fun borrarRaza(id: Int): MutableLiveData<VoteDeleteResponse> {
        val liveData = MutableLiveData<VoteDeleteResponse>()

        viewModelScope.launch {
            val response = repo.eliminaRaza(id)
            if (response.code() == 200) {
                response.body()?.let {
                    liveData.postValue(it)
                }
            }
        }

        return liveData
    }

    fun setRazaPorFoto(imageId: String): MutableLiveData<Breed> {
        viewModelScope.launch {
            val response = repo.dameDetalles(imageId)
            if (response.isSuccessful) {
                response.body()?.let {
                    val detalleimagen = it
                    actualBreedLiveData.postValue(detalleimagen.breeds[0])
                }
            }
        }
        return actualBreedLiveData
    }

}