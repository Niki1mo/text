package com.example.victorina

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.view.View.OnTouchListener
import androidx.appcompat.app.AlertDialog
import com.google.android.material.shape.ShapeAppearanceModel.builder
import org.w3c.dom.Text
import java.util.*
import java.util.stream.DoubleStream.builder
import java.util.stream.IntStream.builder
import java.util.stream.Stream.builder
import kotlin.Array


class Level1 : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level1)

        val btn_nach= findViewById<Button>(R.id.button_nachalo)
        btn_nach.setOnClickListener(){
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        val dialog= Dialog(this@Level1)
        dialog.setCancelable(false)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)//убирает заголовок
        dialog.setContentView(R.layout.priviedialog)
        val btn = dialog.findViewById(R.id.btncontinue) as Button
        btn.setOnClickListener(){
            dialog.dismiss()
        }
        dialog.show()
        val array = Array()//Создали новый объект из класса Array
        //val numleft = 0//Переменная для левой картинки+текст
       // val numRight= 0//Переменная для правой картинки+текст
        val random = Random()
        val imageleft= findViewById<ImageView>(R.id.imageleft)
        imageleft.clipToOutline=true//скругляем углы у картинок вручную т.к. стилем нельзя
        val imageright= findViewById<ImageView>(R.id.imageright)
        imageright.clipToOutline=true
        val text_left= findViewById<TextView>(R.id.text_left)
        val text_right= findViewById<TextView>(R.id.text_right)
        var chet=0 //счетчки правильных ответов
        val animation= AnimationUtils.loadAnimation(this,R.anim.alpha)//      Подключаем анимацию


        val size=array.images1.size
        val koldokudageroi=21
        var otobrozili= mutableListOf<Int>()//массив в который вносим уже исплозованных персонажей
        var numleft=random.nextInt(size)//генерируем случайное число от 0 до размера фоток героев
        imageleft.setImageResource(array.images1[numleft])//достаём из массива картинку
        text_left.setText(array.text1[numleft])

        var numright=random.nextInt(size)

        while (numleft==numright||numleft>koldokudageroi==numright>koldokudageroi||numleft<koldokudageroi==numright<koldokudageroi){
            numright=random.nextInt(size)
        }
        otobrozili.add(numleft)
        otobrozili.add(numright)
        Log.d("Pervonachal","Index: $otobrozili")
        imageright.setImageResource(array.images1[numright])
        text_right.setText(array.text1[numright])
        val text=findViewById<TextView>(R.id.text_levels)
        val progress= arrayOf(
            R.id.point1,R.id.point2,R.id.point3,R.id.point4,R.id.point5,
            R.id.point6,R.id.point7,R.id.point8,R.id.point9,R.id.point10,
            R.id.point11,R.id.point12,R.id.point13,R.id.point14,R.id.point15,
            R.id.point16,R.id.point17,R.id.point18,R.id.point19,R.id.point20)

        imageleft.setOnTouchListener { view, motionEvent ->
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                imageright.isEnabled=false

                if (numleft<koldokudageroi) {
                    imageleft.setImageResource(R.drawable.truee)
                } else {
                    imageleft.setImageResource(R.drawable.fulse)
                }
                Log.d("size","Index: ${otobrozili.size}")
                if(otobrozili.size>39)otobrozili.clear()
            } else if (motionEvent.getAction()==MotionEvent.ACTION_UP){
                if(numleft<koldokudageroi){
                    if(chet<20){
                        chet=chet+1
                    }
                    //закрашиваем в серый цвет наш прогресс
                    for(i in 0..19){
                        val tv=findViewById<TextView>(progress[i])
                        tv.setBackgroundResource(R.drawable.style_point)
                    }
                    //определяем правильные ответы и закрашиваем зеленым
                    for(i in 0..chet-1){
                        val tv=findViewById<TextView>(progress[i])
                        tv.setBackgroundResource(R.drawable.style_point_green)
                    }
                }else{
                    if(chet>0){
                        if(chet==1|| chet==2){
                            chet=0
                        }else{
                            chet=chet-2
                        }
                    }
                    for(i in chet..18){
                        val tv=findViewById<TextView>(progress[i])
                        tv.setBackgroundResource(R.drawable.style_point)
                    }
                    for(i in 0..chet-1){
                        val tv=findViewById<TextView>(progress[i])
                        tv.setBackgroundResource(R.drawable.style_point_green)
                    }
                }
                if(chet==20){
                    val dialog2= Dialog(this@Level1)
                    dialog2.setCancelable(false)
                    dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE)//убирает заголовок
                    dialog2.setContentView(R.layout.dialogend)
                    val btn2 = dialog2.findViewById(R.id.btncontinue1) as Button
                    btn2.setOnClickListener(){
                        dialog2.dismiss()
                        val intent1= Intent(this,MainActivity::class.java)
                        startActivity(intent1)
                    }
                    dialog2.show()

                }else{
                        numleft=random.nextInt(size)
                        var ere=otobrozili.indexOf(numleft)
                        if(ere!=-1){
                        while(ere!=-1) {
                            numleft = random.nextInt(size)
                            ere=otobrozili.indexOf(numleft)
                            Log.d("prisovpodenii",": $ere")
                        }

                    }
                    Log.d("poz",": $ere")
                    //генерируем случайное число от 0 до размера фоток героев
                    otobrozili.add(numleft)
                    imageleft.setImageResource(array.images1[numleft])//достаём из массива картинку
                    imageleft.startAnimation(animation)
                    text_left.setText(array.text1[numleft])

                    numright=random.nextInt(size)
                    ere=otobrozili.indexOf(numright)
                    if(ere!=-1){
                        while(ere!=-1) {
                            numright = random.nextInt(size)
                            ere=otobrozili.indexOf(numright)
                        }
                    }
                    Log.d("Mylogleft","Index: $otobrozili")
                    while (numleft==numright||numleft>koldokudageroi==numright>koldokudageroi||numleft<koldokudageroi==numright<koldokudageroi){
                        numright=random.nextInt(size)
                        ere=otobrozili.indexOf(numright)
                        if(ere!=-1){
                            while(ere!=-1) {
                                numright = random.nextInt(size)
                                ere=otobrozili.indexOf(numright)
                            }
                        }
                    }
                    otobrozili.add(numright)
                    imageright.setImageResource(array.images1[numright])
                    imageright.startAnimation(animation)
                    text_right.setText(array.text1[numright])
                    imageright.isEnabled=true

                }

            }
           true
        }

        imageright.setOnTouchListener { view, motionEvent ->
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                imageleft.isEnabled=false

                if (numright<koldokudageroi) {//||numright<koldokudageroi
                    imageright.setImageResource(R.drawable.truee)
                } else {
                    imageright.setImageResource(R.drawable.fulse)
                }
                Log.d("size","Index: ${otobrozili.size}")
                if(otobrozili.size>40)otobrozili.clear()
            } else if (motionEvent.getAction()==MotionEvent.ACTION_UP){
                if(numright<koldokudageroi){
                    if(chet<20){
                        chet=chet+1
                        Log.d("Chet","Index : $chet")
                    }
                    //закрашиваем в серый цвет наш прогресс
                    for(i in 0..19){
                        val tv=findViewById<TextView>(progress[i])
                        tv.setBackgroundResource(R.drawable.style_point)
                    }
                    //определяем правильные ответы и закрашиваем зеленым
                    for(i in 0..chet-1){
                        val tv=findViewById<TextView>(progress[i])
                        tv.setBackgroundResource(R.drawable.style_point_green)
                    }
                }else{
                    if(chet>0){
                        if(chet==1||chet==2){
                            chet=0
                        }else{
                            chet=chet-2
                            Log.d("chet","Index: $chet")
                        }
                    }
                    for(i in chet..18){
                        val tv=findViewById<TextView>(progress[i])
                        tv.setBackgroundResource(R.drawable.style_point)
                    }
                    for(i in 0..chet-1){
                        val tv=findViewById<TextView>(progress[i])
                        tv.setBackgroundResource(R.drawable.style_point_green)
                    }
                }
                if(chet==20){
                    val dialog3= Dialog(this@Level1)
                    dialog3.setCancelable(false)
                    dialog3.requestWindowFeature(Window.FEATURE_NO_TITLE)//убирает заголовок
                    dialog3.setContentView(R.layout.dialogend)
                    val btn3 = dialog3.findViewById(R.id.btncontinue1) as Button
                    btn3.setOnClickListener(){
                        dialog3.dismiss()
                        val intent2= Intent(this,MainActivity::class.java)
                        startActivity(intent2)
                    }
                    dialog3.show()

                }else{
                    numleft=random.nextInt(size)//генерируем случайное число от 0 до размера фоток героев
                    var ere=otobrozili.indexOf(numleft)
                    if(ere!=-1){
                        while(ere!=-1) {
                            numleft = random.nextInt(size)
                            ere=otobrozili.indexOf(numleft)
                        }
                    }
                    otobrozili.add(numleft)
                    imageleft.setImageResource(array.images1[numleft])//достаём из массива картинку
                    imageleft.startAnimation(animation)
                    text_left.setText(array.text1[numleft])

                   numright=random.nextInt(size)
                    ere=otobrozili.indexOf(numright)
                    if(ere!=-1){
                        while(ere!=-1) {
                            numright = random.nextInt(size)
                            ere=otobrozili.indexOf(numright)
                        }
                    }
                    Log.d("Mylogleft","Index: $otobrozili")
                    while (numleft==numright||numleft>koldokudageroi==numright>koldokudageroi||numleft<koldokudageroi==numright<koldokudageroi){
                        numright=random.nextInt(size)
                        ere=otobrozili.indexOf(numright)
                        if(ere!=-1){
                            while(ere!=-1) {
                                numright = random.nextInt(size)
                                ere=otobrozili.indexOf(numright)
                            }
                        }
                    }
                    otobrozili.add(numright)
                    imageright.setImageResource(array.images1[numright])
                    imageright.startAnimation(animation)
                    text_right.setText(array.text1[numright])
                    imageleft.isEnabled=true

                }

            }
            true
        }
        //Массив для прогресса игры

    }

}