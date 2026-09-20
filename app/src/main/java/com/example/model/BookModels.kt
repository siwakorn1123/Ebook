package com.example.model

data class Chapter(
    val number: Int,
    val title: String,
    val durationMinutes: Int,
    val previewSnippet: String
)

data class Review(
    val author: String,
    val rating: Float,
    val date: String,
    val comment: String,
    val likes: Int = 12
)

data class Book(
    val id: String,
    val title: String,
    val subtitle: String = "",
    val author: String,
    val publisher: String = "สำนักพิมพ์ Readscape",
    val price: Int,
    val originalPrice: Int? = null,
    val discountPercent: Int? = null,
    val rating: Float,
    val reviewsCount: String = "1.2k",
    val category: String,
    val matchPercent: Int? = null,
    val coverUrl: String,
    val synopsis: String,
    val pagesCount: Int = 245,
    val isbn: String = "978-616-XXX-XXX-X",
    val language: String = "ภาษา: ไทย",
    val formats: String = "EPUB & PDF",
    val isInShelf: Boolean = false,
    val shelfCategory: String? = null,
    val status: ReadingStatus = ReadingStatus.NOT_STARTED,
    val readProgress: Int = 0,
    val lastReadText: String? = null,
    val timeRemaining: String? = null,
    val isAudio: Boolean = false,
    val audioChapterText: String? = null,
    val isFavorite: Boolean = false,
    val rank: Int? = null,
    val chapters: List<Chapter> = emptyList(),
    val reviews: List<Review> = emptyList()
)

enum class ReadingStatus {
    READING,
    UNREAD,
    FINISHED,
    NOT_STARTED
}

data class ShelfFolder(
    val id: String,
    val name: String,
    val bookCount: Int,
    val colorDotHex: Long
)
