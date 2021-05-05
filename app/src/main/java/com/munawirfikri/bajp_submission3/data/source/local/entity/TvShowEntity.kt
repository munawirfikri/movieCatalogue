package com.munawirfikri.bajp_submission3.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowentities")
data class TvShowEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        var id: String,

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "description")
        var description: String,

        @ColumnInfo(name = "category")
        var category: String,

        @ColumnInfo(name = "releaseDate")
        var releaseDate: String,

        @ColumnInfo(name = "score")
        var score: Double,

        @ColumnInfo(name = "poster")
        var poster: String,

        @ColumnInfo(name = "type")
        var type: String,

        @ColumnInfo(name="favorite")
        var favorite: Boolean = false
        )