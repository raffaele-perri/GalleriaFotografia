package com.example.galleria.framework.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.app_domain.model.Beer

@Entity(tableName = "Beer")
data class BeerDatabaseModel (
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "tagline") val tagLine: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "image_url") val imageUrl: String?

)
fun List<BeerDatabaseModel>.toDomainModel() = this.map { it.toDomainModel() }

fun BeerDatabaseModel.toDomainModel() = Beer(
    id = this.id,
    name = this.name.orEmpty(),
    tagLine = this.tagLine.orEmpty(),
    description = this.description.orEmpty(),
    imageUrl = this.imageUrl.orEmpty()
)

fun List<Beer>.toDatabaseModel() = this.map { it.toDatabaseModel() }

fun Beer.toDatabaseModel() = BeerDatabaseModel(
    id = this.id,
    name = this.name,
    tagLine = this.tagLine,
    description = this.description,
    imageUrl = this.imageUrl
)

