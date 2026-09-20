package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.SampleBooksData
import com.example.model.Book
import com.example.model.ReadingStatus
import com.example.ui.theme.MidnightSlate
import com.example.ui.theme.OutlineSlate
import com.example.ui.theme.PrimaryFixed
import com.example.ui.theme.SageSecondary
import com.example.ui.theme.SlateVariant
import com.example.ui.theme.StarAmber
import com.example.ui.theme.TerracottaPrimary

@Composable
fun BookshelfScreen(
    books: List<Book>,
    activeFilter: ReadingStatus?,
    onFilterChange: (ReadingStatus?) -> Unit,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    isGridView: Boolean,
    onToggleViewMode: () -> Unit,
    onBookClick: (Book) -> Unit,
    onToggleFavorite: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val readingCount = books.count { it.status == ReadingStatus.READING }
    val unreadCount = books.count { it.status == ReadingStatus.UNREAD }
    val finishedCount = books.count { it.status == ReadingStatus.FINISHED }

    val filteredBooks = books.filter { book ->
        val matchesStatus = when (activeFilter) {
            ReadingStatus.READING -> book.status == ReadingStatus.READING
            ReadingStatus.UNREAD -> book.status == ReadingStatus.UNREAD
            ReadingStatus.FINISHED -> book.status == ReadingStatus.FINISHED
            else -> true
        }
        val matchesSearch = if (searchQuery.isBlank()) true else {
            book.title.contains(searchQuery, ignoreCase = true) ||
                    book.author.contains(searchQuery, ignoreCase = true)
        }
        matchesStatus && matchesSearch
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("bookshelf_screen"),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        // 1. Header Title & Actions (Image 8.jpeg)
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "มุมอ่านหนังสือส่วนตัว",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = TerracottaPrimary
                )

                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ชั้นหนังสือของฉัน",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = MidnightSlate
                    )

                    // "+ นำเข้า / ค้นหา" button
                    Surface(
                        onClick = { /* Import / Search action */ },
                        shape = RoundedCornerShape(20.dp),
                        color = Color(0xFFFFDBCF),
                        modifier = Modifier.testTag("bookshelf_import_button")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = TerracottaPrimary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "นำเข้า / ค้นหา",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = MidnightSlate
                            )
                        }
                    }
                }

                Text(
                    text = "บันทึกทุกก้าวแห่งการเรียนรู้และความเพลิดเพลินของคุณ",
                    fontSize = 12.sp,
                    color = SlateVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        // 2. Search Input in Bookshelf
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(2.dp, RoundedCornerShape(12.dp)),
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFF0F3FF)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Search in bookshelf",
                            tint = OutlineSlate,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = onSearchChange,
                            placeholder = {
                                Text(
                                    text = "ค้นหาชื่อหนังสือ, นักเขียน ในชั้นของคุณ...",
                                    fontSize = 13.sp,
                                    color = OutlineSlate.copy(alpha = 0.7f),
                                    maxLines = 1
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent
                            ),
                            singleLine = true,
                            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 13.sp, color = MidnightSlate),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("bookshelf_search_input")
                        )
                        Icon(
                            imageVector = Icons.Default.Tune,
                            contentDescription = "Filter",
                            tint = OutlineSlate,
                            modifier = Modifier.size(19.dp)
                        )
                    }
                }
            }
        }

        // 3. Status Filters & View Toggle (กำลังอ่าน, ยังไม่อ่าน, อ่านจบแล้ว)
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    StatusFilterPill(
                        label = "กำลังอ่าน ($readingCount)",
                        isSelected = activeFilter == ReadingStatus.READING,
                        onClick = {
                            onFilterChange(if (activeFilter == ReadingStatus.READING) null else ReadingStatus.READING)
                        }
                    )

                    StatusFilterPill(
                        label = "ยังไม่อ่าน ($unreadCount)",
                        isSelected = activeFilter == ReadingStatus.UNREAD,
                        onClick = {
                            onFilterChange(if (activeFilter == ReadingStatus.UNREAD) null else ReadingStatus.UNREAD)
                        }
                    )

                    StatusFilterPill(
                        label = "อ่านจบแล้ว ($finishedCount)",
                        isSelected = activeFilter == ReadingStatus.FINISHED,
                        onClick = {
                            onFilterChange(if (activeFilter == ReadingStatus.FINISHED) null else ReadingStatus.FINISHED)
                        }
                    )
                }

                // Grid / List View Toggle Icon Button
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Color(0xFFF0F3FF), RoundedCornerShape(8.dp))
                        .clickable { onToggleViewMode() }
                        .padding(4.dp)
                ) {
                    Icon(
                        imageVector = if (isGridView) Icons.Default.ViewList else Icons.Default.GridView,
                        contentDescription = "Toggle View Mode",
                        tint = MidnightSlate,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        // 4. Custom Category Folders ("ชั้นหมวดหมู่ส่วนตัว" + "สร้างใหม่")
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Folder,
                            contentDescription = null,
                            tint = TerracottaPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "ชั้นหมวดหมู่ส่วนตัว",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MidnightSlate
                        )
                    }

                    Text(
                        text = "+ สร้างใหม่",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = TerracottaPrimary,
                        modifier = Modifier.clickable { /* Create new folder */ }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(SampleBooksData.shelfFolders) { folder ->
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color.White,
                            modifier = Modifier
                                .shadow(2.dp, RoundedCornerShape(12.dp))
                                .border(0.5.dp, Color(0xFFDEE8FF), RoundedCornerShape(12.dp))
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .background(Color(folder.colorDotHex), CircleShape)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = folder.name,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MidnightSlate
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "• ${folder.bookCount} เล่ม",
                                    fontSize = 11.sp,
                                    color = SlateVariant
                                )
                            }
                        }
                    }
                }
            }
        }

        // 5. Section Header: "หนังสือทั้งหมดในชั้น" with "จัดเรียงตาม: ล่าสุด"
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "หนังสือทั้งหมดในชั้น",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = MidnightSlate
                    )
                    Text(
                        text = "จัดเรียงตาม: ล่าสุด",
                        fontSize = 11.sp,
                        color = SlateVariant
                    )
                }

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFF0F3FF)
                ) {
                    Text(
                        text = "ล่าสุด ▾",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MidnightSlate,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }

        // 6. Books List or Grid View
        if (isGridView) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    val rows = filteredBooks.chunked(2)
                    rows.forEach { rowBooks ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            rowBooks.forEach { book ->
                                Box(modifier = Modifier.weight(1f)) {
                                    BookshelfGridItem(
                                        book = book,
                                        onClick = { onBookClick(book) },
                                        onFavoriteToggle = { onToggleFavorite(book.id) }
                                    )
                                }
                            }
                            if (rowBooks.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        } else {
            items(filteredBooks) { book ->
                BookshelfListItem(
                    book = book,
                    onClick = { onBookClick(book) },
                    onFavoriteToggle = { onToggleFavorite(book.id) }
                )
            }
        }
    }
}

@Composable
private fun StatusFilterPill(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) TerracottaPrimary else Color(0xFFF0F3FF),
        modifier = Modifier.testTag("filter_pill_$label")
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) Color.White else MidnightSlate,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
        )
    }
}

@Composable
private fun BookshelfListItem(
    book: Book,
    onClick: () -> Unit,
    onFavoriteToggle: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .shadow(2.dp, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .testTag("bookshelf_item_${book.id}"),
        shape = RoundedCornerShape(12.dp),
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Book Cover
            Box(
                modifier = Modifier
                    .width(52.dp)
                    .height(76.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .shadow(1.dp)
            ) {
                AsyncImage(
                    model = book.coverUrl,
                    contentDescription = book.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Book Information
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = book.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = MidnightSlate,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Subtitle / Author / Status
                val statusText = when {
                    book.isAudio -> "${book.author} • ${book.audioChapterText ?: "เสียง"}"
                    book.status == ReadingStatus.FINISHED -> "${book.author} • อ่านจบแล้ว"
                    book.status == ReadingStatus.READING -> "${book.author} • กำลังอ่าน ${book.readProgress}%"
                    else -> "${book.author} • ยังไม่อ่าน"
                }

                Text(
                    text = statusText,
                    fontSize = 12.sp,
                    color = SlateVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 2.dp)
                )

                if (book.status == ReadingStatus.READING && book.readProgress > 0) {
                    Spacer(modifier = Modifier.height(6.dp))
                    LinearProgressIndicator(
                        progress = { book.readProgress / 100f },
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(4.dp)
                            .clip(RoundedCornerShape(2.dp)),
                        color = SageSecondary,
                        trackColor = Color(0xFFDEE8FF)
                    )
                }
            }

            // Right Action Icon (Audio icon or Favorite Star)
            IconButton(
                onClick = onFavoriteToggle,
                modifier = Modifier.size(36.dp)
            ) {
                if (book.isAudio) {
                    Icon(
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = "Audiobook",
                        tint = SlateVariant,
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Icon(
                        imageVector = if (book.isFavorite) Icons.Default.Star else Icons.Outlined.StarOutline,
                        contentDescription = "Favorite",
                        tint = if (book.isFavorite) StarAmber else OutlineSlate,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun BookshelfGridItem(
    book: Book,
    onClick: () -> Unit,
    onFavoriteToggle: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(12.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                AsyncImage(
                    model = book.coverUrl,
                    contentDescription = book.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                IconButton(
                    onClick = onFavoriteToggle,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(30.dp)
                        .background(Color.Black.copy(alpha = 0.35f), CircleShape)
                        .padding(2.dp)
                ) {
                    Icon(
                        imageVector = if (book.isFavorite) Icons.Default.Star else Icons.Outlined.StarOutline,
                        contentDescription = null,
                        tint = if (book.isFavorite) StarAmber else Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = book.title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = MidnightSlate,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = book.author,
                fontSize = 11.sp,
                color = SlateVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
