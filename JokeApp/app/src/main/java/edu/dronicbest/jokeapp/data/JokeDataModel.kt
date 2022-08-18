package edu.dronicbest.jokeapp.data

import edu.dronicbest.jokeapp.core.JokeDataModelMapper
import edu.dronicbest.jokeapp.data.cache.ChangeJoke
import edu.dronicbest.jokeapp.data.cache.ChangeJokeStatus

/**
 * JokeApp
 * @author dronicbest on 17.08.2022
 */
class JokeDataModel(
    private val id: Int,
    private val text: String,
    private val punchline: String,
    private val cached: Boolean = false
) : ChangeJoke {

    fun <T> map(mapper: JokeDataModelMapper<T>): T {
        return mapper.map(id, text, punchline, cached)
    }

    override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel = changeJokeStatus.addOrRemove(id, this)

    fun changeCached(cached: Boolean): JokeDataModel {
        return JokeDataModel(id, text, punchline, cached)
    }

//    fun toRealm() = JokeRealmModel().also {
//        it.id = id
//        it.text = text
//        it.punchLine = punchline
//    }
//    fun toJoke() = Joke.Success(text, punchline, cached)
}