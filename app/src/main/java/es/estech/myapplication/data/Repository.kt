package es.estech.myapplication.data

import es.estech.myapplication.data.models.breeds.Breed
import es.estech.myapplication.data.models.votes.VoteSend
import es.estech.myapplication.data.retrofit.RetrofitHelper
import retrofit2.Response

class Repository {
    private val retrogatos = RetrofitHelper.myService

    suspend fun dameRazas() =  retrogatos.allRaces()
    suspend fun dameVotos() = retrogatos.yourVotes()
    suspend fun votaRaza(voto: VoteSend) = retrogatos.votePhoto(voto)
    suspend fun eliminaRaza(id : Int) = retrogatos.eliminarVoto(id)
    suspend fun dameFotoRaza(raza : String) = retrogatos.imagenPorRaza(raza)

}