package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.AppTab
import com.example.ui.ReadscapeViewModel
import com.example.ui.components.ReadscapeBottomNavBar
import com.example.ui.components.ReadscapeHeader
import com.example.ui.screens.AccountScreen
import com.example.ui.screens.BookDetailsScreen
import com.example.ui.screens.BookshelfScreen
import com.example.ui.screens.ExploreScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ReaderDialog
import com.example.ui.theme.ArchivalBackground
import com.example.ui.theme.ReadscapeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReadscapeTheme {
                ReadscapeApp()
            }
        }
    }
}

@Composable
fun ReadscapeApp(
    viewModel: ReadscapeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            viewModel.clearSnackbar()
        }
    }

    // Handle system back button gracefully
    BackHandler(enabled = uiState.selectedBookForDetails != null || uiState.activeReaderBook != null) {
        when {
            uiState.activeReaderBook != null -> viewModel.closeReader()
            uiState.selectedBookForDetails != null -> viewModel.closeBookDetails()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = ArchivalBackground,
        topBar = {
            if (uiState.selectedBookForDetails == null && uiState.activeReaderBook == null) {
                Surface(
                    color = Color.White.copy(alpha = 0.95f),
                    shadowElevation = 1.dp
                ) {
                    Box(modifier = Modifier.statusBarsPadding()) {
                        ReadscapeHeader(
                            coins = uiState.userCoins,
                            notificationCount = uiState.notificationCount,
                            onProfileClick = { viewModel.selectTab(AppTab.ACCOUNT) },
                            onNotificationClick = { /* View notifications */ }
                        )
                    }
                }
            }
        },
        bottomBar = {
            // Show bottom nav bar only when not in reader or detail view
            if (uiState.selectedBookForDetails == null && uiState.activeReaderBook == null) {
                ReadscapeBottomNavBar(
                    currentTab = uiState.currentTab,
                    bookshelfCount = uiState.bookshelf.size,
                    onTabSelected = { tab -> viewModel.selectTab(tab) }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(ArchivalBackground)
        ) {
            // Main Tabs Content
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                when (uiState.currentTab) {
                    AppTab.HOME -> {
                        HomeScreen(
                            searchQuery = uiState.searchQuery,
                            onSearchChange = { viewModel.updateSearchQuery(it) },
                            selectedCategory = uiState.selectedCategory,
                            onCategorySelect = { viewModel.selectCategory(it) },
                            onBookClick = { book -> viewModel.openBookDetails(book) },
                            onReadBook = { book -> viewModel.openReader(book) },
                            onBookmarkBook = { book -> viewModel.addToShelf(book) },
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    AppTab.EXPLORE -> {
                        ExploreScreen(
                            onBookClick = { book -> viewModel.openBookDetails(book) },
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    AppTab.BOOKSHELF -> {
                        BookshelfScreen(
                            books = uiState.bookshelf,
                            activeFilter = uiState.bookshelfFilter,
                            onFilterChange = { viewModel.setBookshelfFilter(it) },
                            searchQuery = uiState.bookshelfSearchQuery,
                            onSearchChange = { viewModel.updateBookshelfSearchQuery(it) },
                            isGridView = uiState.isBookshelfGridView,
                            onToggleViewMode = { viewModel.toggleBookshelfViewMode() },
                            onBookClick = { book -> viewModel.openBookDetails(book) },
                            onToggleFavorite = { bookId -> viewModel.toggleFavorite(bookId) },
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    AppTab.ACCOUNT -> {
                        AccountScreen(
                            userCoins = uiState.userCoins,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            // Book Details Screen (Image 4.jpeg)
            AnimatedVisibility(
                visible = uiState.selectedBookForDetails != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                uiState.selectedBookForDetails?.let { book ->
                    BookDetailsScreen(
                        book = book,
                        onBackClick = { viewModel.closeBookDetails() },
                        onPreviewClick = { viewModel.openReader(it) },
                        onBuyClick = { viewModel.buyBook(it) },
                        onAddToShelfClick = { viewModel.addToShelf(it) },
                        onSelectOtherBook = { viewModel.openBookDetails(it) }
                    )
                }
            }

            // Fullscreen Immersive Reader Dialog
            AnimatedVisibility(
                visible = uiState.activeReaderBook != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                uiState.activeReaderBook?.let { readerBook ->
                    ReaderDialog(
                        book = readerBook,
                        onClose = { viewModel.closeReader() }
                    )
                }
            }
        }
    }
}
