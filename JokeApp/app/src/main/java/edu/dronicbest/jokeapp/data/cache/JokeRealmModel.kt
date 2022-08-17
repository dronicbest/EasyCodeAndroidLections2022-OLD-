package edu.dronicbest.jokeapp.data.cache

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
    var punchline: String = ""
    var type: String = ""
}