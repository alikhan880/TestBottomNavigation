package kz.kbtu.bottomnavigationapplication.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    val instanceLiveData: MutableLiveData<Int> = MutableLiveData()
    val counterLiveData: MutableLiveData<Int> = MutableLiveData<Int>().apply {
        value = 0
    }

    fun getCounterCount(): Int = counterLiveData.value ?: 0

    fun setInstanceCount(count: Int) {
        instanceLiveData.value = count
    }

    fun getInstanceCount(): Int = instanceLiveData.value ?: 0

    fun increment() {
        counterLiveData.value = (counterLiveData.value ?: 0) + 1
    }
}