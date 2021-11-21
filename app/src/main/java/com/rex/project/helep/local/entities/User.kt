package com.rex.project.helep.local.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user")
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
    val passwordConfirm: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val image: ByteArray,
): Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        return image.contentHashCode()
    }
}