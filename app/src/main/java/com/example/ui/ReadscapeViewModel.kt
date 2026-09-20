package com.example.ui

import androidx.lifecycle.ViewModel
import com.example.data.SampleBooksData
import com.example.model.Book
import com.example.model.ReadingStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class AppTab {
    HOME,
    EXPLORE,
    BOOKSHELF,
    ACCOUNT
}

data class UiState(
    val currentTab: AppTab = AppTab.HOME,
    val selectedBookForDetails: Book? = null,
    val activeReaderBook: Book? = null,
    val searchQuery: String = "",
    val bookshelfSearchQuery: String = "",
    val selectedCategory: String = "ทั้งหมด",
    val bookshelfFilter: ReadingStatus? = ReadingStatus.READING,
    val isBookshelfGridView: Boolean = false,
    val userCoins: Int = 450,
    val bookshelf: List<Book> = SampleBooksData.bookshelfItems,
    val notificationCount: Int = 2,
    val snackbarMessage: String? = null
)

class ReadscapeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun selectTab(tab: AppTab) {
        _uiState.update {
            it.copy(
                currentTab = tab,
                selectedBookForDetails = null // return to tab root
            )
        }
    }

    fun openBookDetails(book: Book) {
        _uiState.update { it.copy(selectedBookForDetails = book) }
    }

    fun closeBookDetails() {
        _uiState.update { it.copy(selectedBookForDetails = null) }
    }

    fun openReader(book: Book) {
        _uiState.update { it.copy(activeReaderBook = book) }
    }

    fun closeReader() {
        _uiState.update { it.copy(activeReaderBook = null) }
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun updateBookshelfSearchQuery(query: String) {
        _uiState.update { it.copy(bookshelfSearchQuery = query) }
    }

    fun selectCategory(category: String) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun setBookshelfFilter(status: ReadingStatus?) {
        _uiState.update { it.copy(bookshelfFilter = status) }
    }

    fun toggleBookshelfViewMode() {
        _uiState.update { it.copy(isBookshelfGridView = !it.isBookshelfGridView) }
    }

    fun toggleFavorite(bookId: String) {
        _uiState.update { state ->
            val updated = state.bookshelf.map { book ->
                if (book.id == bookId) book.copy(isFavorite = !book.isFavorite) else book
            }
            state.copy(bookshelf = updated)
        }
    }

    fun addToShelf(book: Book) {
        _uiState.update { state ->
            if (state.bookshelf.any { it.id == book.id }) {
                state.copy(snackbarMessage = "หนังสือ \"${book.title}\" อยู่ในชั้นแล้ว")
            } else {
                val newBook = book.copy(
                    isInShelf = true,
                    status = ReadingStatus.UNREAD,
                    shelfCategory = "คัดสรรสำหรับทำงาน"
                )
                state.copy(
                    bookshelf = listOf(newBook) + state.bookshelf,
                    snackbarMessage = "เพิ่ม \"${book.title}\" เข้าชั้นหนังสือเรียบร้อยแล้ว"
                )
            }
        }
    }

    fun buyBook(book: Book) {
        _uiState.update { state ->
            val newCoins = state.userCoins + (book.price / 10) // reward points on purchase
            val newBook = book.copy(
                isInShelf = true,
                status = ReadingStatus.UNREAD,
                shelfCategory = "คัดสรรสำหรับทำงาน"
            )
            val updatedShelf = if (state.bookshelf.none { it.id == book.id }) {
                listOf(newBook) + state.bookshelf
            } else {
                state.bookshelf
            }
            state.copy(
                userCoins = newCoins,
                bookshelf = updatedShelf,
                snackbarMessage = "สั่งซื้อสำเร็จ! ได้รับโบนัสเหรียญ +${book.price / 10}"
            )
        }
    }

    fun clearSnackbar() {
        _uiState.update { it.copy(snackbarMessage = null) }
    }
}
