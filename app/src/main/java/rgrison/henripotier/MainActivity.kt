package rgrison.henripotier

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Plant logger cf. Android Timber
        Timber.plant(Timber.DebugTree());

        // TODO build Retrofit
        val retrofit = Retrofit.Builder()
                .baseUrl("http://henri-potier.xebia.fr")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        // TODO create a service
        val bookService = retrofit.create(HenriPotierService::class.java)

        // TODO listBooks()
        val booksCalls = bookService.listBooks()


        val adapter: BookAdapter = BookAdapter(emptyList(), this)
        val listView: ListView = findViewById(R.id.bookListView)
        listView.adapter = adapter

        // TODO enqueue call and display book title
        booksCalls.enqueue(object: Callback<List<Book>> {

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                Timber.e("miskine")
            }

            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                val books: List<Book>? = response.body()
                books.apply {
                    this?.forEach {
                        Timber.i("Titre : $it")
                    }

                    adapter.setList(books!!)
                }
            }

        })
    }
}
