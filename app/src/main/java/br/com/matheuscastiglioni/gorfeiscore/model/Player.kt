package br.com.matheuscastiglioni.gorfeiscore.model

/**
 * Created by matheus on 26/10/17.
 */
class Player(val name : String) {

    var score : Int = 0

    fun addScore() {
        this.score++
    }

    private fun hasScore() : Boolean {
        return this.score > 0
    }

    fun removeScore() {
        if (hasScore())
            this.score--
    }

}