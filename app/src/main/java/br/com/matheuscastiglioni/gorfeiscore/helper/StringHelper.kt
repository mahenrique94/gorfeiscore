package br.com.matheuscastiglioni.gorfeiscore.helper

/**
 * Created by matheus on 30/10/17.
 */
abstract class StringHelper {

    companion object {
        fun capitalizeEveryWord(s : String) : String {
            var result : String = ""
            s.split(" ").forEach { result += "${it.capitalize()} " }
            return result
        }
    }

}