package br.com.matheuscastiglioni.gorfeiscore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import br.com.matheuscastiglioni.gorfeiscore.adapter.PlayerAdapter
import br.com.matheuscastiglioni.gorfeiscore.model.Player
import br.com.matheuscastiglioni.gorfeiscore.validator.PlayerValidator
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.OnItemClick

class MainActivity : AppCompatActivity() {

    @BindView(R.id.tvMain_placeholder)
    lateinit var tvMain_placeholder : TextView
    @BindView(R.id.etMain_playerName)
    lateinit var etMain_playerName : EditText
    @BindView(R.id.lvMain_lista)
    lateinit var lvMain_lista : ListView

    private val players : MutableList<Player>? = ArrayList<Player>()
    private lateinit var adapter: PlayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        adapter = PlayerAdapter(this, this.players!!)
    }

    @OnClick(R.id.btnMain_addPlayer)
    fun addPlayer(btnMain_addPlayer : Button) {
        if (PlayerValidator.validatingName(this.etMain_playerName)) {
            this.players?.add(Player(this.etMain_playerName.text.toString()))
            updateView()
            updateList()
        } else {

        }
    }

    private fun hasName(name : EditText) : Boolean {
        return name.text.toString().length > 0
    }

    private fun listHasPlayer() : Boolean {
        return this.players!!.size > 0
    }

    private fun updateList() {
        this.lvMain_lista.adapter = adapter
    }

    private fun updateView() {
        if (listHasPlayer())
            this.tvMain_placeholder.visibility = View.INVISIBLE
        else
            this.tvMain_placeholder.visibility = View.VISIBLE

        this.etMain_playerName.setText(null)
    }

}
