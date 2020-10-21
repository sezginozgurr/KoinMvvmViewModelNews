package com.example.corotinestest.core.model.trnews

data class TurkeyNewsModel(
    val status: String,
    val totalResults: Int,
    val articles: ArrayList<Article>
)

data class Article(
    val source: Source,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
)


data class Source(
    val id: String? = null,
    val name: String
)