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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkAdded
import androidx.compose.material.icons.outlined.LibraryBooks
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.ui.theme.MidnightSlate
import com.example.ui.theme.OutlineSlate
import com.example.ui.theme.PaperWhite
import com.example.ui.theme.SageSecondary
import com.example.ui.theme.SlateVariant
import com.example.ui.theme.StarAmber
import com.example.ui.theme.TerracottaPrimary

@Composable
fun BookDetailsScreen(
    book: Book,
    onBackClick: () -> Unit,
    onPreviewClick: (Book) -> Unit,
    onBuyClick: (Book) -> Unit,
    onAddToShelfClick: (Book) -> Unit,
    onSelectOtherBook: (Book) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("เรื่องย่อ", "สารบัญ", "รีวิว (${book.reviewsCount})")

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9FF))
            .testTag("book_details_screen")
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // 1. Top App Bar (Image 4.jpeg)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .height(56.dp)
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.testTag("details_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MidnightSlate
                        )
                    }

                    Box(
                        modifier = Modifier
                            .background(Color(0xFFDEE8FF), RoundedCornerShape(4.dp))
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "img",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = SlateVariant
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Book Details",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = MidnightSlate
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { /* Share book */ }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = MidnightSlate,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    AsyncImage(
                        model = SampleBooksData.AVATAR_URL,
                        contentDescription = "Profile",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.White, CircleShape)
                    )
                }
            }

            // Scrollable Content
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 100.dp)
            ) {
                // 2. Segmented Pill Tabs (เรื่องย่อ | สารบัญ | รีวิว)
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = Color(0xFFDEE8FF).copy(alpha = 0.6f),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                tabs.forEachIndexed { index, tabName ->
                                    val isSelected = selectedTabIndex == index
                                    Surface(
                                        onClick = { selectedTabIndex = index },
                                        shape = RoundedCornerShape(12.dp),
                                        color = if (isSelected) Color.White else Color.Transparent,
                                        modifier = Modifier
                                            .weight(1f)
                                            .shadow(if (isSelected) 2.dp else 0.dp, RoundedCornerShape(12.dp))
                                            .testTag("tab_$index")
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier.padding(vertical = 8.dp)
                                        ) {
                                            Text(
                                                text = tabName,
                                                fontSize = 13.sp,
                                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                                color = if (isSelected) TerracottaPrimary else SlateVariant
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // 3. Tab Content
                when (selectedTabIndex) {
                    0 -> {
                        // เรื่องย่อ (Synopsis) & Book Hero Overview (Image 4.jpeg)
                        item {
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 6.dp)
                                    .shadow(4.dp, RoundedCornerShape(16.dp)),
                                shape = RoundedCornerShape(16.dp),
                                color = Color.White
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    // Book Center Cover
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(240.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Surface(
                                            modifier = Modifier
                                                .width(160.dp)
                                                .height(230.dp)
                                                .shadow(8.dp, RoundedCornerShape(8.dp)),
                                            shape = RoundedCornerShape(8.dp)
                                        ) {
                                            AsyncImage(
                                                model = book.coverUrl,
                                                contentDescription = book.title,
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier.fillMaxSize()
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(14.dp))

                                    Text(
                                        text = book.title,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif,
                                        color = MidnightSlate
                                    )

                                    if (book.subtitle.isNotBlank()) {
                                        Text(
                                            text = book.subtitle,
                                            fontSize = 12.sp,
                                            color = SlateVariant,
                                            modifier = Modifier.padding(top = 2.dp)
                                        )
                                    }

                                    Text(
                                        text = "โดย ${book.author}",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = TerracottaPrimary,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )

                                    HorizontalDivider(
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = Color(0xFFDEE8FF)
                                    )

                                    // Metadata Box
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color(0xFFF0F3FF), RoundedCornerShape(10.dp))
                                            .padding(12.dp)
                                    ) {
                                        Text(
                                            text = "รายละเอียดหนังสือ",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MidnightSlate
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "หมวดหมู่: ${book.category} | จำนวนหน้า: ${book.pagesCount} | ภาษา: ไทย | ISBN: ${book.isbn}",
                                            fontSize = 11.sp,
                                            color = SlateVariant,
                                            lineHeight = 16.sp
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))

                                    // Action buttons: "ทดลองอ่าน" & "เพิ่มเข้าชั้น"
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        OutlinedButton(
                                            onClick = { onPreviewClick(book) },
                                            modifier = Modifier
                                                .weight(1f)
                                                .testTag("details_preview_button"),
                                            shape = RoundedCornerShape(10.dp),
                                            colors = ButtonDefaults.outlinedButtonColors(
                                                contentColor = TerracottaPrimary
                                            )
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.MenuBook,
                                                contentDescription = null,
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = "ทดลองอ่าน",
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }

                                        Button(
                                            onClick = { onAddToShelfClick(book) },
                                            modifier = Modifier
                                                .weight(1f)
                                                .testTag("details_add_shelf_button"),
                                            shape = RoundedCornerShape(10.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = SageSecondary,
                                                contentColor = Color.White
                                            )
                                        ) {
                                            Icon(
                                                imageVector = if (book.isInShelf) Icons.Outlined.BookmarkAdded else Icons.Default.BookmarkAdd,
                                                contentDescription = null,
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = if (book.isInShelf) "อยู่ในชั้นแล้ว" else "เพิ่มเข้าชั้น",
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))

                                    // Rating
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(vertical = 4.dp)
                                    ) {
                                        repeat(5) {
                                            Icon(
                                                imageVector = Icons.Default.Star,
                                                contentDescription = null,
                                                tint = StarAmber,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = "${book.rating}",
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MidnightSlate
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "(${book.reviewsCount} รีวิว)",
                                            fontSize = 12.sp,
                                            color = SlateVariant
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Text(
                                        text = "เรื่องย่อ",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif,
                                        color = MidnightSlate
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = book.synopsis,
                                        fontSize = 13.sp,
                                        color = MidnightSlate.copy(alpha = 0.85f),
                                        lineHeight = 22.sp
                                    )
                                }
                            }
                        }
                    }

                    1 -> {
                        // สารบัญ (Table of Contents)
                        item {
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 6.dp)
                                    .shadow(4.dp, RoundedCornerShape(16.dp)),
                                shape = RoundedCornerShape(16.dp),
                                color = Color.White
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        text = "สารบัญเล่ม",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif,
                                        color = MidnightSlate
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))

                                    val chapters = if (book.chapters.isNotEmpty()) {
                                        book.chapters
                                    } else {
                                        SampleBooksData.detailsFeaturedBook.chapters
                                    }

                                    chapters.forEach { chapter ->
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { onPreviewClick(book) }
                                                .padding(vertical = 10.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .size(28.dp)
                                                    .background(Color(0xFFF0F3FF), CircleShape),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = chapter.number.toString(),
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = TerracottaPrimary
                                                )
                                            }
                                            Spacer(modifier = Modifier.width(12.dp))
                                            Column(modifier = Modifier.weight(1f)) {
                                                Text(
                                                    text = chapter.title,
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = MidnightSlate
                                                )
                                                Text(
                                                    text = chapter.previewSnippet,
                                                    fontSize = 11.sp,
                                                    color = SlateVariant,
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis
                                                )
                                            }
                                            Text(
                                                text = "${chapter.durationMinutes} นาที",
                                                fontSize = 11.sp,
                                                color = OutlineSlate
                                            )
                                        }
                                        HorizontalDivider(color = Color(0xFFF0F3FF))
                                    }
                                }
                            }
                        }
                    }

                    2 -> {
                        // รีวิว (Reviews)
                        item {
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 6.dp)
                                    .shadow(4.dp, RoundedCornerShape(16.dp)),
                                shape = RoundedCornerShape(16.dp),
                                color = Color.White
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "คะแนนและรีวิวจากผู้อ่าน",
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = FontFamily.Serif,
                                            color = MidnightSlate
                                        )
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                imageVector = Icons.Default.Star,
                                                contentDescription = null,
                                                tint = StarAmber,
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Text(
                                                text = " ${book.rating} / 5.0",
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = MidnightSlate
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))

                                    val reviews = if (book.reviews.isNotEmpty()) book.reviews else SampleBooksData.detailsFeaturedBook.reviews
                                    reviews.forEach { review ->
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 8.dp)
                                        ) {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Text(
                                                    text = review.author,
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = MidnightSlate
                                                )
                                                Text(
                                                    text = review.date,
                                                    fontSize = 11.sp,
                                                    color = OutlineSlate
                                                )
                                            }
                                            Row(modifier = Modifier.padding(vertical = 2.dp)) {
                                                repeat(review.rating.toInt()) {
                                                    Icon(
                                                        imageVector = Icons.Default.Star,
                                                        contentDescription = null,
                                                        tint = StarAmber,
                                                        modifier = Modifier.size(13.dp)
                                                    )
                                                }
                                            }
                                            Text(
                                                text = review.comment,
                                                fontSize = 12.sp,
                                                color = MidnightSlate.copy(alpha = 0.85f),
                                                lineHeight = 18.sp
                                            )
                                        }
                                        HorizontalDivider(color = Color(0xFFF0F3FF))
                                    }
                                }
                            }
                        }
                    }
                }

                // 4. "ผู้อ่านเล่มนี้สนใจเพิ่มเติม" (Image 4.jpeg carousel)
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "ผู้อ่านเล่มนี้สนใจเพิ่มเติม",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif,
                                color = MidnightSlate
                            )

                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .background(Color(0xFFF0F3FF), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ChevronRight,
                                    contentDescription = "More",
                                    tint = MidnightSlate,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // 3 Related Books as seen in Image 4.jpeg
                        val relatedBooks = listOf(
                            SampleBooksData.detailsFeaturedBook.copy(price = 245),
                            SampleBooksData.bestSellers[3], // ศิลปะแห่งความเงียบ (฿199)
                            SampleBooksData.bestSellers[4]  // พลังแห่งนิสัย (฿220)
                        )

                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(relatedBooks) { itemBook ->
                                RelatedBookCard(
                                    book = itemBook,
                                    onClick = { onSelectOtherBook(itemBook) }
                                )
                            }
                        }
                    }
                }
            }

            // 5. Sticky Bottom Action Bar (Image 4.jpeg)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .shadow(elevation = 16.dp),
                color = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "฿${book.price}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = TerracottaPrimary
                        )
                        Text(
                            text = book.formats,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = SlateVariant
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        // "ตัวอย่าง" (Preview) Button
                        Surface(
                            onClick = { onPreviewClick(book) },
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFFDEE8FF),
                            modifier = Modifier.testTag("details_sample_button")
                        ) {
                            Text(
                                text = "ตัวอย่าง",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = MidnightSlate,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                            )
                        }

                        // "ซื้อเลย" (Buy Now) Button with Shopping Bag icon
                        Button(
                            onClick = { onBuyClick(book) },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = TerracottaPrimary,
                                contentColor = Color.White
                            ),
                            modifier = Modifier.testTag("details_buy_now_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingBag,
                                contentDescription = null,
                                modifier = Modifier.size(17.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "ซื้อเลย",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RelatedBookCard(
    book: Book,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .width(125.dp)
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
                    .clip(RoundedCornerShape(6.dp))
            ) {
                AsyncImage(
                    model = book.coverUrl,
                    contentDescription = book.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Rating overlay in top left
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .background(Color.White.copy(alpha = 0.9f), RoundedCornerShape(4.dp))
                        .padding(horizontal = 4.dp, vertical = 1.dp)
                        .align(Alignment.TopStart)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = StarAmber,
                            modifier = Modifier.size(10.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "${book.rating}",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = MidnightSlate
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = book.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = MidnightSlate,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "฿${book.price}",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = TerracottaPrimary,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}
