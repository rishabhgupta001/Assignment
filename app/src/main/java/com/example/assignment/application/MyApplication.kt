package com.example.assignment.application

import android.app.Application
import com.example.assignment.data.network.ApiService
import com.example.assignment.data.network.NetworkConnectionInterceptor
import com.example.assignment.data.repository.RemoteDataSource
import com.example.assignment.data.repository.TodoRepository
import com.example.assignment.ui.TodoViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
/**
 *
​
 * Purpose – Singleton class of Application Level
 *
 * @author ​Rishabh Gupta
​
 * Created on Feburary 13, 2020
​
 * Modified on Feburary 21, 2020
 *
 * */
class MyApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))

        //use operator fun in singleton classes mostly act as a constructor
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiService(instance()) }
        bind() from singleton {
            TodoRepository(
                instance(), instance()
            )
        }

        bind() from singleton { RemoteDataSource(instance()) }
        bind() from provider { TodoViewModelFactory(instance()) }
    }
}