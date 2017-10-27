package br.com.matheuscastiglioni.gorfeiscore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import br.com.matheuscastiglioni.gorfeiscore.adapter.ListaPlayerAdapter
import br.com.matheuscastiglioni.gorfeiscore.model.Player
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class MainActivity : AppCompatActivity() {

    @BindView(R.id.tvMain_placeholder)
    lateinit var tvMain_placeholder : TextView
    @BindView(R.id.etMain_playerName)
    lateinit var etMain_playerName : EditText
    @BindView(R.id.lvMain_lista)
    lateinit var lvMain_lista : ListView

    private val players : MutableList<Player>? = ArrayList<Player>()
    private lateinit var adapter: ListaPlayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        adapter = ListaPlayerAdapter(this, this.players!!)
    }

    @OnClick(R.id.btnMain_addPlayer)
    fun addPlayer(btnMain_addPlayer : Button) {
        this.players?.add(Player(this.etMain_playerName.text.toString()))
        updateView()
        updateList()
    }

    fun updateList() {
        this.lvMain_lista.adapter = adapter
    }

    fun updateView() {
        if (this.players!!.size > 0)
            this.tvMain_placeholder.visibility = View.INVISIBLE
        this.etMain_playerName.setText(null)
    }

}
