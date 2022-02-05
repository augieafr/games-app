package com.augieafr.gamesapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseList(
	@field:SerializedName("results")
	val results: List<GameModel>? = null,
)

@Parcelize
data class GameModel(
	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("platforms")
	val platforms: List<PlatformsItem?>? = null,

	@field:SerializedName("genres")
	val genres: List<GenresItem?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("released")
	val released: String? = null,

	@field:SerializedName("background_image")
	var backgroundImage: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null
) : Parcelable

@Parcelize
data class Platform(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("games_count")
	val gamesCount: Int? = null,

	@field:SerializedName("year_end")
	val yearEnd: String? = null,

	@field:SerializedName("year_start")
	val yearStart: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("image_background")
	val imageBackground: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
) : Parcelable

@Parcelize
data class PlatformsItem(

	@field:SerializedName("requirements_ru")
	val requirementsRu: String? = null,

	@field:SerializedName("requirements_en")
	val requirementsEn: String? = null,

	@field:SerializedName("released_at")
	val releasedAt: String? = null,

	@field:SerializedName("platform")
	val platform: Platform? = null
) : Parcelable

@Parcelize
data class GenresItem(

	@field:SerializedName("games_count")
	val gamesCount: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("image_background")
	val imageBackground: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
) : Parcelable
