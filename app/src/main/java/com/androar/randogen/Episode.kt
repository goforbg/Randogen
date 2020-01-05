package com.androar.randogen

data class Episode(
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
