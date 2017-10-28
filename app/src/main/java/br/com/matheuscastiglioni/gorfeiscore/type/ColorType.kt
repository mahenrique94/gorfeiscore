package br.com.matheuscastiglioni.gorfeiscore.type

import android.graphics.Color

/**
 * Created by matheus on 27/10/17.
 */
enum class ColorType(val color : String) {

    ERROR("#FF8080"),
    SUCCESS("#00CC99");

    fun get() : Int {
        return Color.parseColor(this.color)
    }

}