package br.com.matheuscastiglioni.gorfeiscore

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.widget.*
import br.com.matheuscastiglioni.gorfeiscore.adapter.PlayerAdapter
import br.com.matheuscastiglioni.gorfeiscore.helper.StringHelper
import br.com.matheuscastiglioni.gorfeiscore.helper.ViewHelper
import br.com.matheuscastiglioni.gorfeiscore.model.Player
import br.com.matheuscastiglioni.gorfeiscore.validator.PlayerValidator
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
    @BindView(R.id.srMain_refresh)
    lateinit var srMain_refresh : SwipeRefreshLayout

    private val LIST_LIMIT = 8

    private val players : MutableList<Player> = ArrayList<Player>()
    private lateinit var adapter: PlayerAdapter
    private val listHasLimit = this.players.size == LIST_LIMIT
    private val listHasPlayer = this.players.size > 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        adapter = PlayerAdapter(this@MainActivity, this, this.players)

        srMain_refresh.setOnRefreshListener { updateList() }
    }

    override fun onResume() {
        super.onResume()

        adapter.notifyDataSetChanged()
    }

    @OnClick(R.id.btnMain_addPlayer)
    fun addPlayer(btnMain_addPlayer : Button) {
        if (this.listHasLimit) {
            Toast.makeText(this, "Limite máximo de jogadores atingido", Toast.LENGTH_LONG).show()
        } else {
            if (PlayerValidator.validatingName(this.etMain_playerName)) {
                this.players.add(Player(StringHelper.capitalizeEveryWord(this.etMain_playerName.text.toString())))
                updateList()
                this.etMain_playerName.clearFocus()
            }
        }

        ViewHelper.hideKeyboard(this)
    }

    private fun hasName(name : EditText) = name.text.toString().length > 0

    private fun updateList() {
        this.players.sortBy { player -> player.score }
        this.lvMain_lista.adapter = adapter
        this.srMain_refresh.isRefreshing = false
        ViewHelper.updateMain(this, this.players)
    }

}
