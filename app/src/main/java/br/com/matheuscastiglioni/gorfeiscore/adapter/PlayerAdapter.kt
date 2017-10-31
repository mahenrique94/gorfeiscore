package br.com.matheuscastiglioni.gorfeiscore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import br.com.matheuscastiglioni.gorfeiscore.MainActivity
import br.com.matheuscastiglioni.gorfeiscore.R
import br.com.matheuscastiglioni.gorfeiscore.helper.DialogHelper
import br.com.matheuscastiglioni.gorfeiscore.helper.ViewHelper
import br.com.matheuscastiglioni.gorfeiscore.model.Player
import br.com.matheuscastiglioni.gorfeiscore.type.ColorType
import br.com.matheuscastiglioni.gorfeiscore.validator.PlayerValidator
import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by matheus on 26/10/17.
 */
class PlayerAdapter(val activity : MainActivity, val context : Context, val players : MutableList<Player>) : BaseAdapter() {

    @BindView(R.id.tvPlayerAdapter_name)
    lateinit var tvPlayerAdapter_name : TextView
    @BindView(R.id.tvPlayerAdapter_position)
    lateinit var tvPlayerAdapter_position : TextView
    @BindView(R.id.etPlayerAdapter_score)
    lateinit var etPlayerAdapter_score : EditText
    @BindView(R.id.btnPlayerAdapter_removeScore)
    lateinit var btnPlayerAdapter_removeScore : Button
    @BindView(R.id.btnPlayerAdapter_addScore)
    lateinit var btnPlayerAdapter_addScore : Button
    @BindView(R.id.btnPlayerAdapter_removePlayer)
    lateinit var btnPlayerAdapter_removePlayer : Button

    private val POSITION_FIRST = 1

    override fun getCount(): Int {
        return this.players.size
    }

    override fun getItem(index : Int): Any {
        return this.players.get(index)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(index : Int, view : View?, viewGroup : ViewGroup?): View {
        val player = this.players.get(index)
        val inflater = LayoutInflater.from(this.context)
        var novaView = view


        if (view == null)
            novaView = inflater.inflate(R.layout.player_adpter, viewGroup, false)

        ButterKnife.bind(this, novaView!!)

        val position = (index + 1)
        player.position = position
        this.tvPlayerAdapter_position.setText(player.position.toString())
        checkColorIndex(player.position)

        this.tvPlayerAdapter_name.setText(player.name)

        val score : EditText = novaView.findViewById(R.id.etPlayerAdapter_score)
        this.btnPlayerAdapter_removeScore.setOnClickListener {
            if (PlayerValidator.validatingScore(score)) {
                player.removeScore(score.text.toString().toInt())
                clearScore(score)
                notifyDataSetChanged()
                ViewHelper.hideKeyboard(this.activity)
                Toast.makeText(this.context, "Pontos removidos com sucesso", Toast.LENGTH_SHORT).show()
                score.clearFocus()
            }
        }

        this.btnPlayerAdapter_addScore.setOnClickListener {
            if (PlayerValidator.validatingScore(score)) {
                player.addScore(score.text.toString().toInt())
                clearScore(score)
                notifyDataSetChanged()
                ViewHelper.hideKeyboard(this.activity)
                Toast.makeText(this.context, "Pontos adicionados com sucesso", Toast.LENGTH_SHORT).show()
                score.clearFocus()
            }
        }

        this.btnPlayerAdapter_removePlayer.setOnClickListener {
            DialogHelper.confirmRemovePlayer(this.activity, this.context, this.players, player, this@PlayerAdapter)
        }

        this.tvPlayerAdapter_name.setOnClickListener { DialogHelper.infoPlayer(this.context, player, this@PlayerAdapter, this.activity) }

        return novaView!!
    }

    private fun checkColorIndex(position : Int) {
        if (isFirst(position))
            this.tvPlayerAdapter_position.setTextColor(ColorType.SUCCESS.get())

        if (isLast(position))
            this.tvPlayerAdapter_position.setTextColor(ColorType.ERROR.get())
    }

    private fun isFirst(position : Int) : Boolean {
        return position == POSITION_FIRST;
    }

    private fun isLast(position : Int) : Boolean {
        return position == this.players.size;
    }

    private fun clearScore(score : EditText) {
        score.setText(null)
    }

}