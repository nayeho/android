package com.example.wordle

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnKeyListener
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class GameAdapter(val context: Context, val gameList: ArrayList<GameVO>, val answer: String) :
    RecyclerView.Adapter<GameAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val etGame1: EditText
        val etGame2: EditText
        val etGame3: EditText
        val etGame4: EditText
        val etGame5: EditText

        init {
            etGame1 = itemView.findViewById(R.id.etGame1)
            etGame2 = itemView.findViewById(R.id.etGame2)
            etGame3 = itemView.findViewById(R.id.etGame3)
            etGame4 = itemView.findViewById(R.id.etGame4)
            etGame5 = itemView.findViewById(R.id.etGame5)
        }


    }

    fun enableEditText(etList: ArrayList<EditText>) {
        for (i in 0 until etList.size) {
            etList.get(i).isEnabled = true
        }
    }
    fun disableEditText(etList: ArrayList<EditText>) {
        for (i in 0 until etList.size) {
            etList.get(i).isEnabled = false
            etList.get(i).setTextColor(Color.parseColor("white"))
        }
    }
    fun checkAnswer(answer: String, etList: ArrayList<EditText>) {


        for (i in 0 until etList.size) {
            val answerChar: Char = answer.get(i)
            val etChar: Char = etList.get(i).text.toString().single()

            if (answerChar == etChar) {
                etList.get(i).setBackgroundColor(context.getColor(R.color.word_green))
            } else {
                var check = true

                for (j in 0 until etList.size) {
                    if (etChar == answer.get(j)) {
                        etList.get(i).setBackgroundColor(context.getColor(R.color.word_yellow))
                        check = false
                    }
                }


                if (check) {
                    etList.get(i).setBackgroundColor(context.getColor(R.color.word_gray))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.game_list, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val etList = ArrayList<EditText>()
        etList.add(holder.etGame1)
        etList.add(holder.etGame2)
        etList.add(holder.etGame3)
        etList.add(holder.etGame4)
        etList.add(holder.etGame5)

        holder.etGame1.setText(gameList.get(position).word1)
        holder.etGame2.setText(gameList.get(position).word2)
        holder.etGame3.setText(gameList.get(position).word3)
        holder.etGame4.setText(gameList.get(position).word4)
        holder.etGame5.setText(gameList.get(position).word5)

//        Log.d("테스트5", position.toString())
//        if(position == 0){
//            enableEditText(etList)
//        }else{
//            disableEditText(etList)
//        }

        for (i in 0 until 4) {
            etList.get(i).setOnKeyListener(object : OnKeyListener {
                override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                    if (p2?.action == KeyEvent.ACTION_DOWN) {
                        etList.get(i + 1).requestFocus()
                    }
                    return false
                }

            })
        }

        holder.etGame5.setOnKeyListener(object : OnKeyListener {
            override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == KeyEvent.KEYCODE_ENTER && p2?.action == KeyEvent.ACTION_UP) {
                    if (position == gameList.size - 1) {
                        Toast.makeText(context, answer, Toast.LENGTH_SHORT).show()
                    }
                    checkAnswer(answer, etList)
                    disableEditText(etList)
                }
                return false
            }
        })


    }

    override fun getItemCount(): Int {
        return gameList.size
    }


}