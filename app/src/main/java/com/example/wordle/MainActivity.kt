package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val gameList = ArrayList<GameVO>()
    lateinit var adapter : GameAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Container 결정
        val rvGame = findViewById<RecyclerView>(R.id.rvGame)
        val btnRetry = findViewById<Button>(R.id.btnRetry)
        val answer = "apple"


        // 2. Template 결정
        // game_list.xml


        // 4. Adpater 결정
        adapter = GameAdapter(this, gameList, answer)

        // 3. Item 결정
        gameSet()

        // 5. container 에 adapter 부착
        rvGame.adapter = adapter
        rvGame.layoutManager = LinearLayoutManager(this)

        // 6. Event 처리
        btnRetry.setOnClickListener {
            gameSet()
        }

    }

    fun gameSet(){
        gameList.clear()
        for(i in 0 until 6){
            val vo = GameVO("", "", "", "", "")
            gameList.add(vo)
        }
        adapter.notifyDataSetChanged()

    }
}