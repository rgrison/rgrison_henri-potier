package rgrison.henripotier

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        // Récupération du livre à afficher
        val book: Book = intent.getParcelableExtra("BOOK")

        // remplissage des éléments de l'activité
        val titleTextView   : TextView  = findViewById<TextView> (R.id.idDetailTextView)
        val coverImageView  : ImageView = findViewById<ImageView>(R.id.coverImageView  )
        val synopsisTextView: TextView  = findViewById<TextView> (R.id.synopsisTextView)

        titleTextView.text = book.title
        synopsisTextView.text = book.synopsis.fold("", {synopsis, ligne -> "$synopsis\n$ligne"})

        Picasso.get()
                .load(book?.cover)
                .into(coverImageView)
    }
}
