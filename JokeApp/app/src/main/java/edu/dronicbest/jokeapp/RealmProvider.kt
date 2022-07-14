package edu.dronicbest.jokeapp

import io.realm.Realm

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
interface RealmProvider {
    fun provide(): Realm
}