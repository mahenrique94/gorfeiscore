package br.com.matheuscastiglioni.gorfeiscore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.com.matheuscastiglioni.gorfeiscore.R
import br.com.matheuscastiglioni.gorfeiscore.model.Player
import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by matheus on 26/10/17.
 */
class ListaPlayerAdapter(context : Context, players : List<Player>) : BaseAdapter() {

    @BindView(R.id.tvListaPlayerAdapter_name)
    lateinit var tvListaPlayerAdapter_name : TextView

    private val players = players
    private val context = context

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
            novaView = inflater.inflate(R.layout.lista_player_adpter, viewGroup, false)

        ButterKnife.bind(this, novaView!!)

        this.tvListaPlayerAdapter_name.setText(player.name)
        return novaView!!
    }

}