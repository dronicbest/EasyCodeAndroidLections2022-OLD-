package edu.dronicbest.jokeapp.data.cache

import edu.dronicbest.jokeapp.data.JokeDataModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * JokeApp
 * @author dronicbest on 13.07.2022
 */
open class JokeRealmModel : RealmObject() {
    @PrimaryKey
    var id: Int = -1
    var text: String = ""
    var punchLine: String = ""
    var type: String = ""

    fun toJokeDataModel() = JokeDataModel(id, type, text, punchLine)
}