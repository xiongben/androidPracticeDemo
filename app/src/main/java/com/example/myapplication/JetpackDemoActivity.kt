package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.Transformation
import android.widget.Button
import android.widget.TextView
import androidx.core.content.edit
import androidx.lifecycle.*
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit


class MyObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart() {
        Log.d("MyObserver", "activityStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop() {
        Log.d("MyObserver", "activityStop")
    }
}

data class User(var firstName: String, var lastName: String, var age:Int)

object Repository {
    fun getUser(userId: String): LiveData<User> {
        val liveData = MutableLiveData<User>()
        liveData.value = User(userId, userId, 0)
        return liveData
    }
}


class MainViewModel(countReserved: Int) : ViewModel() {
    private val userLiveData = MutableLiveData<User>()
    private val userIdLiveData = MutableLiveData<String>()
    var counter = MutableLiveData<Int>()
    val userName: LiveData<String> = Transformations.map(userLiveData) { user ->
        "${user.firstName} ${user.lastName}"
    }

    val user: LiveData<User> = Transformations.switchMap(userIdLiveData){userId ->
        Repository.getUser(userId)
    }

    init {
        counter.value = countReserved
    }

    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }

    fun plusOne () {
        val count = counter.value ?: 0
        counter.value = count + 1
    }

    fun clear() {
        counter.value = 0
    }
}

class MainViewModelFactory(private val countReserved: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(countReserved) as T
    }
}

class JetpackDemoActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack_demo)

        lifecycle.addObserver(MyObserver())

        val infoText: TextView = findViewById(R.id.infoText)
        val plusOneBtn: Button = findViewById(R.id.plusOneBtn)
        val clearBtn: Button = findViewById(R.id.clearBtn)
        val getUserBtn: Button = findViewById(R.id.getUserBtn)
        val doWorkBtn: Button = findViewById(R.id.doWorkBtn)

        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)
        viewModel = ViewModelProviders.of(this, MainViewModelFactory(countReserved)).get(MainViewModel::class.java)
        clearBtn.setOnClickListener {
            viewModel.clear()
        }
        plusOneBtn.setOnClickListener {
            viewModel.plusOne()
        }

        getUserBtn.setOnClickListener {
            val userId = (0..1000).random().toString()
            viewModel.getUser(userId)
        }

        doWorkBtn.setOnClickListener {
            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
                .setInitialDelay(5, TimeUnit.MINUTES)
                .addTag("simple")
                .build()
            WorkManager.getInstance(this).enqueue(request)
        }

        viewModel.counter.observe(this, Observer { count ->
            infoText.text = count.toString()
        })

        viewModel.user.observe(this, Observer { user ->
            infoText.text = user.firstName
        })
        refreshCounter()
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
    }

    private fun refreshCounter() {
        val infoText: TextView = findViewById(R.id.infoText)
        infoText.text = viewModel.counter.toString()
    }
}