package com.example.ArfanUAS.vo

// file tambahan dan konten statis yang digunakan kode Anda, seperti bitmap, penetapan tata letak, string antarmuka pengguna, instruksi animasi, dan banyak lagi.
class Resource<T>(val status: Status, val body: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data, null)

        fun <T> error(msg: String?, data: T?): Resource<T> = Resource(Status.ERROR, data, msg)

        fun <T> loading(data: T?): Resource<T> = Resource(Status.LOADING, data, null)
    }

}