package rgrison.henripotier

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private var adapter: BookAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = BookAdapter(emptyList(), this)

        Timber.plant(Timber.DebugTree());

        val retrofit = Retrofit.Builder()
                .baseUrl("http://henri-potier.xebia.fr")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val bookService = retrofit.create(HenriPotierService::class.java)

        // création des appels pour récupérer les livres
        val booksCalls = bookService.listBooks()

        val listView: ListView = findViewById(R.id.bookListView)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            this.getDetails(position)
        }

        // récupération des livres
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

                    adapter?.setList(books!!)
                }
            }

        })
    }


    fun getDetails(position: Int) {
        // récupération du livre en utilisant l'indice de la liste
        val book: Book? = adapter?.getItem(position)

        // démarrage de la nouvelle activité en utilisant l'intent
        val intent = Intent(this@MainActivity, BookDetailActivity::class.java)
        intent.putExtra("BOOK", book!!)
        startActivity(intent)
    }

}
