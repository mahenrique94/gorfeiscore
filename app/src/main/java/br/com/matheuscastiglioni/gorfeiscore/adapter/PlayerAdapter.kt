package br.com.matheuscastiglioni.gorfeiscore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import br.com.matheuscastiglioni.gorfeiscore.R
import br.com.matheuscastiglioni.gorfeiscore.helper.DialogHelper
import br.com.matheuscastiglioni.gorfeiscore.model.Player
import br.com.matheuscastiglioni.gorfeiscore.type.ColorType
import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by matheus on 26/10/17.
 */
class PlayerAdapter(val context : Context, val players : MutableList<Player>) : BaseAdapter() {

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
        this.tvPlayerAdapter_position.setText(position.toString())
        checkColorIndex(position)

        this.tvPlayerAdapter_name.setText(player.name)
        updateScore(player.score)
        this.btnPlayerAdapter_removeScore.setOnClickListener {
            player.removeScore()
            notifyDataSetChanged()
        }

        this.btnPlayerAdapter_addScore.setOnClickListener {
            player.addScore()
            notifyDataSetChanged()
        }

        this.btnPlayerAdapter_removePlayer.setOnClickListener { DialogHelper.confirmRemovePlayer(context, players, player) }

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

    fun updateScore(score : Int) {
        this.etPlayerAdapter_score.setText(score.toString())
    }

}