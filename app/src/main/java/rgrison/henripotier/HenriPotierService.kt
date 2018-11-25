package rgrison.henripotier

import retrofit2.http.GET
import retrofit2.Call

interface HenriPotierService {

    @GET("books")
    fun listBooks(): Call<List<Book>>

}
