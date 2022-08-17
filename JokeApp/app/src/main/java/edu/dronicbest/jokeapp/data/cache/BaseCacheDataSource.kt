package edu.dronicbest.jokeapp.data.cache

import edu.dronicbest.jokeapp.data.Result
import edu.dronicbest.jokeapp.domain.Joke
import edu.dronicbest.jokeapp.presentation.JokeUiModel
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * JokeApp
 * @author dronicbest on 13.07.2022
 */
class BaseCacheDataSource(private val realmProvider: RealmProvider) : CacheDataSource {

    override suspend fun getJoke(): Result<Joke, Unit> {
        realmProvider.provide().use {
            val jokes = it.where(JokeRealmModel::class.java).findAll()
            if (jokes.isEmpty())
                return Result.Error(Unit)
            else
                jokes.random().let { jokeRealm ->
                    return Result.Success(
                        Joke(
                            jokeRealm.id,
                            jokeRealm.type,
                            jokeRealm.text,
                            jokeRealm.punchline
                        )
                    )
                }
        }
    }

    override suspend fun addOrRemove(id: Int, joke: Joke): JokeUiModel {
        return withContext(Dispatchers.IO) {
            Realm.getDefaultInstance().use {
                val jokeRealm = it.where(JokeRealmModel::class.java).equalTo("id", id).findFirst()
                if (jokeRealm == null) {
                    it.executeTransaction {
                        val newJoke = joke.toJokeRealm()
                        it.insert(newJoke)
                    }
                    joke.toFavoriteJoke()
                } else {
                    it.executeTransaction {
                        jokeRealm.deleteFromRealm()
                    }
                    joke.toBaseJoke()
                }
            }
        }
    }
}