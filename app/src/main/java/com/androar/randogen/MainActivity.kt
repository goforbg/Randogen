package com.androar.randogen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        val arrayList = generateEpisodes()
        val randomButton = findViewById(R.id.randombutton) as TextView
        val episodeImage = findViewById<ImageView>(R.id.episodeImage)
        val episodeTitle = findViewById<TextView>(R.id.episodeTitle)

        randomButton.setOnClickListener {
            val r = Random()
            var random = r. nextInt(10 - 1) + 1;

            Glide.with(this)
                    .load(arrayList.get(random).i)
                    .into(episodeImage)

            episodeImage.scaleType = ImageView.ScaleType.CENTER_CROP
            episodeTitle.setText(arrayList.get(random).t)

            episodeImage.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query="+arrayList.get(random).t))
                startActivity(intent)
            }
        }



    }

    private fun generateEpisodes(): List<Episode> {
        val arrayList = ArrayList<Episode>()
        val retrofit = Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api = retrofit.create(Api::class.java)
        val call = api.episodes
        call.enqueue(object : Callback<List<Episode>> {
            override fun onResponse(call: Call<List<Episode>>, response: Response<List<Episode>>) {
                val episodeList = response.body()!!
                for (i in episodeList.indices) {
                    arrayList.add(Episode(i.toLong(), episodeList[i].t, episodeList[i].p, episodeList[i].i))
                }
            }

            override fun onFailure(call: Call<List<Episode>>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        return arrayList;
    }



}