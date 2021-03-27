package com.example.nyarticles.Reponse

import com.google.gson.annotations.SerializedName

class PopularArticlesResponse(
        @SerializedName("results") val results: List<PopularArticlesitem>
        )

class PopularArticlesitem (
    @SerializedName("uri") val uri : String,
    @SerializedName("url") val url : String,
    @SerializedName("id") val id : String,
    @SerializedName("asset_id") val asset_id : String,
    @SerializedName("source") val source : String,
    @SerializedName("published_date") val published_date : String,
    @SerializedName("updated") val updated : String,
    @SerializedName("section") val section : String,
    @SerializedName("subsection") val subsection : String,
    @SerializedName("nytdsection") val nytdsection : String,
    @SerializedName("adx_keywords") val adx_adx_keywordswords : String,
    @SerializedName("column") val column : String,
    @SerializedName("byline") val byline : String,
    @SerializedName("type") val type : String,
    @SerializedName("title") val title : String,
    @SerializedName("abstract") val abstract : String,
    @SerializedName("media") val media: List<Media>
)


data class Media (
        @SerializedName("type")
        val type: String,
        @SerializedName("subtype")
        val subtype: String,
        @SerializedName("caption")
        val caption: String,
        @SerializedName("copyright")
        val copyright: String,

        @SerializedName("approved_for_syndication")
        val approved_for_syndication: Long,

        @SerializedName("media-metadata")
        val mediaMetadata: List<MediaMetadatum>
)

data class MediaMetadatum (
        @SerializedName("url")
        val url: String,
        @SerializedName("format")
        val format: String,
        @SerializedName("height")
        val height: Long,
        @SerializedName("width")
        val width: Long
)


