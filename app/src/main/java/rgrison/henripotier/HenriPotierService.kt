package rgrison.henripotier

import retrofit2.http.GET
import retrofit2.Call
import rgrison.henripotier.Book

interface HenriPotierService {

    @GET("books")
    fun listBooks(): Call<List<Book>>

}
