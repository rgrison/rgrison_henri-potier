package rgrison.henripotier

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class BookAdapter(private var books: List<Book>, context: Context) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItem(position: Int): Book = books[position]

    override fun getItemId(position: Int): Long  = getItem(position).hashCode().toLong()

    override fun getCount(): Int = books.count()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        val view : BookItemView = when (convertView) {
            null -> inflater.inflate(R.layout.custom_view_item_book, parent, false)
            else -> convertView
        } as BookItemView

        view.bindView(getItem(position))
        return view
    }


    fun setList(books: List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }

}
