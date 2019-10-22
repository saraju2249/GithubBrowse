

package com.example.android.observability.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "users")
class User {

    @PrimaryKey
    @ColumnInfo(name = "userid")
    var id: String
        private set

    @ColumnInfo(name = "username")
    var userName: String? = null
        private set

    @Ignore
    constructor(userName: String) {
        id = UUID.randomUUID().toString()
        this.userName = userName
    }

    constructor(id: String, userName: String) {
        this.id = id
        this.userName = userName
    }
}
