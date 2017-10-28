package br.com.matheuscastiglioni.gorfeiscore.validator

import android.widget.EditText

/**
 * Created by matheus on 27/10/17.
 */
abstract class PlayerValidator {

    companion object {
        fun validatingName(name : EditText) : Boolean {
            if (!hasName(name)) {
                name.setError("Nome obrigatório para adicionar novo jogador")
                name.requestFocus()
            }
            return hasName(name)
        }

        private fun hasName(name : EditText) : Boolean {
            return name.text.toString().length > 0
        }
    }



}