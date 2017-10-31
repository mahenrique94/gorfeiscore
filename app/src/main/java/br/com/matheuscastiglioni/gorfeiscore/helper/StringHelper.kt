package br.com.matheuscastiglioni.gorfeiscore.helper

/**
 * Created by matheus on 30/10/17.
 */
abstract class StringHelper {

    companion object {
        fun capitalizeEveryWord(s : String) : String {
            val split = s.split(" ")
            var result : String = "";
            split.forEach { s -> result += "${s.capitalize()} " }
            return result
        }
    }

}