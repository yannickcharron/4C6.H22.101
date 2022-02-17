package ca.qc.cstj.s02constraintlayout

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.notify() {
    this.value = this.value
}

fun String.test() : String {
    return "fdff"
}