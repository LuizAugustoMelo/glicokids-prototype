package com.glicokids.prototype.domain.repository

interface StorageRepository {
    fun saveString(key: String, value: String)
    fun getString(key: String, defaultValue: String = ""): String
    fun saveInt(key: String, value: Int)
    fun getInt(key: String, defaultValue: Int = 0): Int
    fun remove(key: String)
}
