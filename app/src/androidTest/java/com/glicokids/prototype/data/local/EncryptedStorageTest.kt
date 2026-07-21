package com.glicokids.prototype.data.local

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EncryptedStorageTest {

    private lateinit var storage: EncryptedStorage
    private val context: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setup() {
        storage = EncryptedStorage(context)
    }

    @Test
    fun `when saving a string, should retrieve the same string`() {
        val key = "test_key"
        val value = "hello_glico"
        
        storage.saveString(key, value)
        val retrieved = storage.getString(key)
        
        assertThat(retrieved).isEqualTo(value)
    }

    @Test
    fun `when saving an int, should retrieve the same int`() {
        val key = "test_int"
        val value = 1234
        
        storage.saveInt(key, value)
        val retrieved = storage.getInt(key)
        
        assertThat(retrieved).isEqualTo(value)
    }

    @Test
    fun `when getting non-existent key, should return default value`() {
        val retrieved = storage.getString("non_existent", "default")
        assertThat(retrieved).isEqualTo("default")
    }

    @Test
    fun `when removing a key, should return default value`() {
        val key = "to_remove"
        storage.saveString(key, "temp")
        storage.remove(key)
        
        val retrieved = storage.getString(key, "deleted")
        assertThat(retrieved).isEqualTo("deleted")
    }
}
