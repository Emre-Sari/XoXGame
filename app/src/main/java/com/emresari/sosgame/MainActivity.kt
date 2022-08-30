package com.emresari.sosgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import com.emresari.sosgame.databinding.ActivityMainBinding
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    var player1=0
    var player2=1
    var activePlayer=player1
    var gameActive=true
    lateinit var array:IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        array= intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)
        binding.button1.setOnClickListener(this)
        binding.button2.setOnClickListener(this)
        binding.button3.setOnClickListener(this)

        binding.button6.setOnClickListener(this)
        binding.button7.setOnClickListener(this)
        binding.button8.setOnClickListener(this)

        binding.button4.setOnClickListener(this)
        binding.button5.setOnClickListener(this)
        binding.button9.setOnClickListener(this)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.restart){
           restartGame()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(p0: View?) {
        var clickedbtn=findViewById<Button>(p0!!.id)
        var clickedtag=Integer.parseInt(clickedbtn.tag.toString())
        if(!gameActive){
            return
        }
        if(array[clickedtag]!=-1){
            return
        }
        array[clickedtag]=activePlayer


        if (activePlayer==player1){
            clickedbtn.setText("X")
            binding.playerTurn.text="Oyuncu-2"
            activePlayer=player2
            clickedbtn.setBackgroundColor(getColor(R.color.blue))

        }else{
            clickedbtn.setText("O")
            binding.playerTurn.text="Oyuncu-1"
            activePlayer=player1
            clickedbtn.setBackgroundColor(getColor(R.color.red))

        }
        checkWin()

    }
    private fun checkWin(){
        var winnerPosition= arrayOf(intArrayOf(0,1,2), intArrayOf(3,4,5), intArrayOf(6,7,8),
         intArrayOf(0,3,6), intArrayOf(1,4,7), intArrayOf(2,5,8), intArrayOf(0,4,8), intArrayOf(2,4,6))
        for (i in 0 until winnerPosition.size){
            var val0=winnerPosition[i][0]
            var val1=winnerPosition[i][1]
            var val2=winnerPosition[i][2]
            if(array[val0]!=-1){

                if(array[val0]==array[val1] && array[val1]==array[val2]){
                     gameActive=false
                    if(array[val0]==player1){
                        binding.playerTurn.text="Oyuncu-1 Kazandı"
                        MotionToast.createToast(this,
                            "Tebrikler 😍",
                            "Oyuncu-1 Kazandı!",
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(this,R.font.helvetica_regular))
                    }else{
                        binding.playerTurn.text="Oyuncu-2 Kazandı"
                        MotionToast.createToast(this,
                            "Tebrikler 😍",
                            "Oyuncu-2 Kazandı!",
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(this,R.font.helvetica_regular))
                    }
                    return
                }
            }

        }
        var counter=0
        for (i in 0 until array.size){
            if(array[i]==-1){
                counter+=1
            }

        }
        if(counter==0){


            MotionToast.createToast(this,
                "Berabere 😍",
                "Oyun Berabere Kaldı !",
                MotionToastStyle.INFO,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this,R.font.helvetica_regular))
                return
        }

    }
    fun restartGame(){
        array=intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)
        activePlayer=player1
        gameActive=true
        binding.playerTurn.text="Oyuncu-1"
        binding.button1.setBackgroundColor(getColor(R.color.special2))
        binding.button2.setBackgroundColor(getColor(R.color.special2))
        binding.button3.setBackgroundColor(getColor(R.color.special2))
        binding.button4.setBackgroundColor(getColor(R.color.special2))
        binding.button5.setBackgroundColor(getColor(R.color.special2))
        binding.button6.setBackgroundColor(getColor(R.color.special2))
        binding.button7.setBackgroundColor(getColor(R.color.special2))
        binding.button8.setBackgroundColor(getColor(R.color.special2))
        binding.button9.setBackgroundColor(getColor(R.color.special2))
        binding.button1.setText("")
        binding.button2.setText("")
        binding.button3.setText("")
        binding.button4.setText("")
        binding.button5.setText("")
        binding.button6.setText("")
        binding.button7.setText("")
        binding.button8.setText("")
        binding.button9.setText("")

    }
}