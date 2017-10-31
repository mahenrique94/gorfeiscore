package br.com.matheuscastiglioni.gorfeiscore.model

/**
 * Created by matheus on 26/10/17.
 */
class Player(var name : String) {

    var score : Int = 0
    var position : Int = 0;

    fun addScore(score : Int) {
        this.score += score
    }

    private fun hasScore() : Boolean {
        return this.score > 0
    }

    fun removeScore(score : Int) {
        if (hasScore())
            this.score -= score

        if (scoreSmallerZero())
            reset()
    }

    fun reset() {
        this.score = 0
    }

    private fun scoreSmallerZero() : Boolean {
        return this.score < 0
    }

}