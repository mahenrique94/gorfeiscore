package br.com.matheuscastiglioni.gorfeiscore.helper

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import br.com.matheuscastiglioni.gorfeiscore.R
import br.com.matheuscastiglioni.gorfeiscore.model.Player

/**
 * Created by matheus on 30/10/17.
 */
abstract class ViewHelper {

    companion object {
        fun hideKeyboard(activity: Activity) {
            val view = activity.currentFocus
            if (view != null) {
                val service : InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                service.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        fun updateMain(activity: Activity, players : MutableList<Player>) {
            val tvMain_placeholder : TextView = activity.findViewById(R.id.tvMain_placeholder)
            val lvMain_lista : ListView = activity.findViewById(R.id.lvMain_lista)
            val etMain_playerName : EditText = activity.findViewById(R.id.etMain_playerName)

            if (listHasPlayer(players)) {
                tvMain_placeholder.visibility = View.INVISIBLE
                lvMain_lista.visibility = View.VISIBLE
            } else {
                tvMain_placeholder.visibility = View.VISIBLE
                lvMain_lista.visibility = View.INVISIBLE
            }

            etMain_playerName.setText(null)
        }

        private fun listHasPlayer(players: MutableList<Player>): Boolean {
            return players.size > 0
        }
    }

}