package br.com.matheuscastiglioni.gorfeiscore.helper

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.widget.Button
import android.widget.EditText
import br.com.matheuscastiglioni.gorfeiscore.MainActivity
import br.com.matheuscastiglioni.gorfeiscore.R
import br.com.matheuscastiglioni.gorfeiscore.adapter.PlayerAdapter
import br.com.matheuscastiglioni.gorfeiscore.model.Player
import br.com.matheuscastiglioni.gorfeiscore.type.ColorType
import kotlinx.android.synthetic.main.player_info.*

/**
 * Created by matheus on 28/10/17.
 */
abstract class DialogHelper {

    companion object {
        fun confirmRemovePlayer(activity: MainActivity, context: Context, players: MutableList<Player>, player: Player, adapter: PlayerAdapter) {
            val builder = AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
            with(builder) {
                setTitle("Confirmar exclusão")
                setMessage("Deseja remover o jogador ${player.name} ?")
                setNegativeButton("Não") { dialogInterface, _ ->  }
                setPositiveButton("Sim") { dialogInterface, _ ->
                    players.remove(player)
                    adapter.notifyDataSetChanged()
                    ViewHelper.updateMain(activity, players)
                }
            }

            with(builder.create()) {
                show()
                getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ColorType.ERROR.get())
                getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ColorType.SUCCESS.get())
            }
        }

        fun infoPlayer(context: Context, player: Player, adapter : PlayerAdapter, activity: MainActivity) {
            val builder = AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                builder.setView(R.layout.player_info)
            val alert = builder.create()
            alert.show()
            configureAlert(alert, player, adapter, activity)
        }

        private fun configureAlert(alert: AlertDialog, player: Player, adapter: PlayerAdapter, activity: MainActivity) {
            setPosition(alert, player)
            setName(alert, player)
            setScore(alert, player)

            setUpdateAction(alert, player, adapter, activity)
        }

        private fun setName(alert: AlertDialog, player: Player) = alert.etPlayerInfo_name.setText(player.name.toString().trim())
        private fun setPosition(alert: AlertDialog, player: Player) = alert.etPlayerInfo_position.setText(player.position.toString())
        private fun setScore(alert: AlertDialog, player: Player) = alert.etPlayerInfo_score.setText(player.score.toString())

        private fun setUpdateAction(alert: AlertDialog, player: Player, adapter: PlayerAdapter, activity: MainActivity) {
            val btnPlayerInfo_update : Button = alert.btnPlayerInfo_update
            btnPlayerInfo_update.setOnClickListener {
                val etPlayerInfo_name : EditText = alert.etPlayerInfo_name
                player.name = StringHelper.capitalizeEveryWord(etPlayerInfo_name.text.toString())
                adapter.notifyDataSetChanged()
                alert.hide()

                ViewHelper.hideKeyboard(activity)
            }
        }
    }

}