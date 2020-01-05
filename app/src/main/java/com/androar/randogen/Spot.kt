package com.yuyakaido.android.cardstackview.sample

data class Spot(
        val id: Long = counter++,
        val t: String,
        val p: String,
        val i: String
)

{
    companion object {
        private var counter = 0L
    }
}
