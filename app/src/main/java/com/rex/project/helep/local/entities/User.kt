package com.rex.project.helep.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
    val passwordConfirm: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val image: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (username != other.username) return false
        if (email != other.email) return false
        if (phoneNumber != other.phoneNumber) return false
        if (password != other.password) return false
        if (passwordConfirm != other.passwordConfirm) return false
        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + username.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + phoneNumber.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + passwordConfirm.hashCode()
        result = 31 * result + image.contentHashCode()
        return result
    }
}