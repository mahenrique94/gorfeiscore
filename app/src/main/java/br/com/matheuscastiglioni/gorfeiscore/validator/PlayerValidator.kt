package br.com.matheuscastiglioni.gorfeiscore.validator

import android.widget.EditText

/**
 * Created by matheus on 27/10/17.
 */
abstract class PlayerValidator {

    companion object {
        fun validatingName(name : EditText) : Boolean {
            if (!hasValue(name)) {
                name.setError("Nome obrigatório para adicionar novo jogador")
                name.requestFocus()
            }
            return hasValue(name)
        }

        fun validatingScore(score : EditText) : Boolean {
            if (!hasValue(score)) {
                score.setError("Pontuação não informada")
                score.requestFocus()
            }
            return hasValue(score)
        }

        private fun hasValue(name : EditText) : Boolean {
            return name.text.toString().length > 0
        }
    }

}