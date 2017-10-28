package br.com.matheuscastiglioni.gorfeiscore.helper

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import br.com.matheuscastiglioni.gorfeiscore.model.Player
import br.com.matheuscastiglioni.gorfeiscore.type.ColorType

/**
 * Created by matheus on 28/10/17.
 */
abstract class DialogHelper {

    companion object {
        fun confirmRemovePlayer(context: Context, players: MutableList<Player>, player: Player) {
            val builder = AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
            builder.setTitle("Remover jogador")
            builder.setMessage("Deseja remover o jogador ${player.name} ?")
            builder.setNegativeButton("Não") { dialogInterface, i ->  println("Não quero remover") }
            builder.setPositiveButton("Sim") { dialogInterface, i -> players.remove(player) }

            val alert = builder.create()
            alert.show()

            alert.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ColorType.ERROR.get())
            alert.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ColorType.SUCCESS.get())
        }
    }

}