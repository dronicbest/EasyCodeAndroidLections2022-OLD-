package edu.dronicbest.jokeapp.core

import edu.dronicbest.jokeapp.data.cache.JokeRealmModel

/**
 * JokeApp
 * @author dronicbest on 17.08.2022
 */
class JokeRealmMapper : JokeDataModelMapper<JokeRealmModel> {
    override fun map(id: Int, text: String, punchLine: String, cached: Boolean): JokeRealmModel {
        return JokeRealmModel().also {
            it.id = id
            it.text = text
            it.punchLine = punchLine
        }
    }
}