import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.tutorialapi.Musica
import com.example.tutorialapi.R

class MusicaAdapter: RecyclerView.Adapter<MusicaAdapter.MusicaViewHolder>() {

    private var data: List<Musica> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.text_row_item, parent, false)
        return MusicaViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MusicaViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun updateData(data: List<Musica>) {
        this.data = data
        print(data.size)
        print("tamanho")
    }

    class MusicaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.textViewNomeMusica)
        private val artist: TextView = itemView.findViewById(R.id.textViewNomeArtista)
        //private val album: TextView = itemView.findViewById(R.id.) TODO: Usar o nome do album
        private val image: ImageView = itemView.findViewById(R.id.imagemCapaAlbum)

        fun bind(item: Musica) {
            title.text = item.titulo
            artist.text = item.artista
            //album.text = item.album
            Glide.with(itemView.context)
                .load(item.imagem)
                .into(image)
        }
    }
}
