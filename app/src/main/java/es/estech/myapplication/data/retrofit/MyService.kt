package es.estech.myapplication.data.retrofit

import es.estech.myapplication.data.models.breeds.Breed
import es.estech.myapplication.data.models.catphoto.ImagenGato
import es.estech.myapplication.data.models.catphoto.ImagenGatoDetalle
import es.estech.myapplication.data.models.votes.VoteDeleteResponse
import es.estech.myapplication.data.models.votes.VoteResponse
import es.estech.myapplication.data.models.votes.VoteSend
import es.estech.myapplication.data.models.votes.Votes
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MyService {
    companion object {
        const val API_KEY = "live_crmoe9RY7cm3uoFKH94hjxCD4UjfI25q47uGfxCkd4ctpnCS7nB77XempyEhT6v6"
        const val CONTENT_TYPE = "application/json"
    }

    @GET("breeds")
    suspend fun allRaces() : Response<ArrayList<Breed>>

    @GET("votes")
    suspend fun yourVotes() : Response<ArrayList<Votes>>

    @POST("votes")
    suspend fun votePhoto(
        @Body body: VoteSend
    ) : Response<VoteResponse>

    @DELETE("votes/{id}")
    suspend fun eliminarVoto(
        @Path("id") id: Int
    ) : Response<VoteDeleteResponse>

    @GET("images/search")
    suspend fun imagenPorRaza(
        @Query("breed_ids") breed_id: String
    ) : Response<ArrayList<ImagenGato>>

    @GET("images/{id}")
    suspend fun detallesImage(
        @Path("id") id: String
    ) : Response<ImagenGatoDetalle>
}