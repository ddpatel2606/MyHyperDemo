package com.dixitpatel.mycoffeevenue.mymodule.presentation.viewutils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.AssetManager
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.recyclerview.widget.RecyclerView
import com.dixitpatel.mycoffeevenue.R

fun AssetManager.readAssetsFile(fileName : String): String = open(fileName).bufferedReader().use{it.readText()}

@SuppressLint("NotifyDataSetChanged")
fun RecyclerView.recyclerViewAnimate()
{
    val context: Context = this.context
    val controller: LayoutAnimationController =
        AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation)
    this.layoutAnimation = controller
    this.adapter?.notifyDataSetChanged()
    this.scheduleLayoutAnimation()
}