package com.example.tutorialapi

import MusicaAdapter
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity


import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MusicaAdapter
    private lateinit var imageView: ImageView
    private lateinit var imageViewAvatar: ImageView
    private lateinit var drawable: Drawable
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_main)

        //Definicoes
        imageView = findViewById(R.id.imagemCapaPlaylist)
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.gradientbg, null)!!
        view = window.decorView
        //view.background = drawable

        recyclerView = findViewById(R.id.recyclerViewMusicas)
        adapter = MusicaAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        imageViewAvatar = findViewById(R.id.imagemPerfilUsuario)


        Glide.with(this)
            .load(Constants.IMAGEM_AVATAR_USUARIO)
            .into(imageViewAvatar)

        Glide.with(this)
            .load(Constants.IMAGEM_CAPA_PLAYLIST)
            .into(imageView)


        getData()

    }

    fun getData() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance(Constants.BASE_URL)

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getPosts()

        callback.enqueue(object : Callback<List<Musica>> {
            override fun onFailure(call: Call<List<Musica>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()

                t.message?.let { Log.e("erro!", it) }
                Log.d("problema", t.stackTraceToString())
                Log.d("chamada", call.toString())
            }

            override fun onResponse(call: Call<List<Musica>>, response: Response<List<Musica>>) {

                val data = response.body()
                data?.let {
                    adapter.updateData(it)
                    adapter.notifyDataSetChanged()

                }

                Log.d("entrou - codigo",response.raw().code().toString())
                Log.d("entrou - codigo",response.raw().toString())

            }
        })

    }
}