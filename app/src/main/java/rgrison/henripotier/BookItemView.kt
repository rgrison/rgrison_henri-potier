package rgrison.henripotier

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import timber.log.Timber

class BookItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        LinearLayout(context, attrs, defStyleAttr) {

    var title: TextView?  = null
    var cover: ImageView? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        title = findViewById(R.id.titleTextView)
        cover = findViewById(R.id.coverImageView)
    }

    fun bindView(book: Book?) {
        title?.text = book?.title

        Timber.i("Chargement de la couverture du livre '${book?.title}'")
        Picasso.get()
                .load(book?.cover)
                .into(cover!!)
    }
}
