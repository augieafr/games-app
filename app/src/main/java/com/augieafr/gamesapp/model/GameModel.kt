package com.augieafr.gamesapp.model

import com.google.gson.annotations.SerializedName

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
	val backgroundImage: Any? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class Platform(

	@field:SerializedName("image")
	val image: Any? = null,

	@field:SerializedName("games_count")
	val gamesCount: Int? = null,

	@field:SerializedName("year_end")
	val yearEnd: Any? = null,

	@field:SerializedName("year_start")
	val yearStart: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("image_background")
	val imageBackground: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class PlatformsItem(

	@field:SerializedName("requirements_ru")
	val requirementsRu: Any? = null,

	@field:SerializedName("requirements_en")
	val requirementsEn: Any? = null,

	@field:SerializedName("released_at")
	val releasedAt: String? = null,

	@field:SerializedName("platform")
	val platform: Platform? = null
)

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
)
