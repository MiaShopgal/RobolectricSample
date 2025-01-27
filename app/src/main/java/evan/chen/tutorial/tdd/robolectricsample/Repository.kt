package evan.chen.tutorial.tdd.robolectricsample

import android.content.Context

class Repository(private val context: Context) {

    fun saveUserId(id: String) {
        val sharedPreference = context.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        sharedPreference.edit().putString("USER_ID", id).apply()
    }
}