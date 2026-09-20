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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.MenuBook
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.SampleBooksData
import com.example.model.Book
import com.example.ui.theme.AmberTertiary
import com.example.ui.theme.AmberTertiaryFixed
import com.example.ui.theme.CoffeeContainer
import com.example.ui.theme.CoffeeOnContainer
import com.example.ui.theme.MidnightSlate
import com.example.ui.theme.OnPrimaryFixed
import com.example.ui.theme.OutlineSlate
import com.example.ui.theme.PrimaryFixed
import com.example.ui.theme.SageSecondary
import com.example.ui.theme.SlateVariant
import com.example.ui.theme.StarAmber
import com.example.ui.theme.TerracottaPrimary
import com.example.ui.theme.TerracottaPrimaryContainer

@Composable
fun HomeScreen(
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    selectedCategory: String,
    onCategorySelect: (String) -> Unit,
    onBookClick: (Book) -> Unit,
    onReadBook: (Book) -> Unit,
    onBookmarkBook: (Book) -> Unit,
    modifier: Modifier = Modifier
) {
    val allCatalog = SampleBooksData.getAllCatalog()
    val filteredBooks = if (selectedCategory == "ทั้งหมด" && searchQuery.isBlank()) {
        null
    } else {
        allCatalog.filter { book ->
            val matchesCategory = (selectedCategory == "ทั้งหมด" || book.category.contains(selectedCategory.take(4)))
            val matchesSearch = if (searchQuery.isBlank()) true else {
                book.title.contains(searchQuery, ignoreCase = true) ||
                        book.author.contains(searchQuery, ignoreCase = true) ||
                        book.category.contains(searchQuery, ignoreCase = true)
            }
            matchesCategory && matchesSearch
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("home_screen"),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        // 1. Search Bar
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(14.dp),
                            spotColor = Color(0x1A1E293B)
                        ),
                    shape = RoundedCornerShape(14.dp),
                    color = Color.White
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = OutlineSlate,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = onSearchChange,
                            placeholder = {
                                Text(
                                    text = "ค้นหาชื่อหนังสือ, นักเขียน, สำนักพิมพ์...",
                                    fontSize = 13.sp,
                                    color = OutlineSlate.copy(alpha = 0.7f),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent
                            ),
                            singleLine = true,
                            textStyle = androidx.compose.ui.text.TextStyle(
                                fontSize = 14.sp,
                                color = MidnightSlate
                            ),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("home_search_input")
                        )

                        IconButton(
                            onClick = { /* Voice Search */ },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = "Voice Search",
                                tint = OutlineSlate,
                                modifier = Modifier.size(19.dp)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(16.dp)
                                .background(Color(0xFFDEE8FF))
                        )

                        IconButton(
                            onClick = { /* Filter */ },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Tune,
                                contentDescription = "Filters",
                                tint = OutlineSlate,
                                modifier = Modifier.size(19.dp)
                            )
                        }
                    }
                }
            }
        }

        // If actively searching or filtering, show results view
        if (filteredBooks != null) {
            item {
                Text(
                    text = "ผลการค้นหา (${filteredBooks.size} เล่ม)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MidnightSlate,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }
            items(filteredBooks) { book ->
                SearchBookResultItem(
                    book = book,
                    onClick = { onBookClick(book) },
                    onBookmark = { onBookmarkBook(book) }
                )
            }
        } else {
            // Standard Home View matching mockup & HTML

            // 2. Hero Promo Editorial Banner
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 8.dp,
                                shape = RoundedCornerShape(16.dp),
                                spotColor = TerracottaPrimary.copy(alpha = 0.3f)
                            )
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        TerracottaPrimary,
                                        TerracottaPrimaryContainer,
                                        AmberTertiary
                                    )
                                )
                            )
                            .padding(18.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                // Badge
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .background(
                                            color = Color.White.copy(alpha = 0.2f),
                                            shape = RoundedCornerShape(12.dp)
                                        )
                                        .padding(horizontal = 8.dp, vertical = 3.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.LocalFireDepartment,
                                        contentDescription = "Promo",
                                        tint = AmberTertiaryFixed,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "เทศกาลอ่านฟิน",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.White
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "สัปดาห์หนังสือ E-book",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Serif,
                                    color = Color.White,
                                    lineHeight = 26.sp
                                )

                                Text(
                                    text = "ลดสูงสุดถึง 50% มากกว่าหมื่นเล่ม",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White.copy(alpha = 0.9f),
                                    modifier = Modifier.padding(top = 4.dp)
                                )

                                Spacer(modifier = Modifier.height(14.dp))

                                Surface(
                                    onClick = { onBookClick(SampleBooksData.bestSellers[0]) },
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color.White,
                                    modifier = Modifier.testTag("hero_promo_button")
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp)
                                    ) {
                                        Text(
                                            text = "ดูโปรโมชัน",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = TerracottaPrimary
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                            contentDescription = null,
                                            tint = TerracottaPrimary,
                                            modifier = Modifier.size(14.dp)
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            // 3D Tilted Book Cover
                            Surface(
                                modifier = Modifier
                                    .width(88.dp)
                                    .height(120.dp)
                                    .rotate(3f)
                                    .shadow(elevation = 10.dp, shape = RoundedCornerShape(8.dp)),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                AsyncImage(
                                    model = SampleBooksData.bestSellers[0].coverUrl,
                                    contentDescription = "Promo Book",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            }

            // 3. Category Pills (Horizontal Scroll)
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(SampleBooksData.allCategories) { category ->
                        val isSelected = category == selectedCategory
                        Surface(
                            onClick = { onCategorySelect(category) },
                            shape = RoundedCornerShape(20.dp),
                            color = if (isSelected) TerracottaPrimary else Color(0xFFF0F3FF),
                            modifier = Modifier.testTag("cat_pill_$category")
                        ) {
                            Text(
                                text = category,
                                fontSize = 13.sp,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                color = if (isSelected) Color.White else MidnightSlate,
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                            )
                        }
                    }
                }
            }

            // 4. Continue Reading Section ("กำลังอ่านต่อ")
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Outlined.MenuBook,
                                contentDescription = null,
                                tint = SageSecondary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "กำลังอ่านต่อ",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif,
                                color = MidnightSlate
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable { onBookClick(SampleBooksData.continueReadingBook) }
                        ) {
                            Text(
                                text = "ดูทั้งหมด",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = TerracottaPrimary
                            )
                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = null,
                                tint = TerracottaPrimary,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    val continueBook = SampleBooksData.continueReadingBook
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 4.dp,
                                shape = RoundedCornerShape(16.dp),
                                spotColor = Color(0x1A1E293B)
                            )
                            .clickable { onBookClick(continueBook) },
                        shape = RoundedCornerShape(16.dp),
                        color = Color.White
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Cover Thumbnail with EPUB badge
                            Box(
                                modifier = Modifier
                                    .width(64.dp)
                                    .height(92.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .shadow(2.dp)
                            ) {
                                AsyncImage(
                                    model = continueBook.coverUrl,
                                    contentDescription = continueBook.title,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                                Box(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .background(SageSecondary, RoundedCornerShape(4.dp))
                                        .padding(horizontal = 4.dp, vertical = 1.dp)
                                        .align(Alignment.TopStart)
                                ) {
                                    Text(
                                        text = "EPUB",
                                        color = Color.White,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            // Details
                            Column(modifier = Modifier.weight(1f)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = continueBook.lastReadText ?: "บทที่ 14 • อ่านล่าสุดเมื่อวาน",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = SageSecondary
                                    )
                                    Text(
                                        text = "${continueBook.readProgress}%",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = SlateVariant
                                    )
                                }

                                Text(
                                    text = continueBook.title,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Serif,
                                    color = MidnightSlate,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(top = 2.dp)
                                )

                                Text(
                                    text = continueBook.author,
                                    fontSize = 12.sp,
                                    color = SlateVariant,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                // Progress Bar
                                LinearProgressIndicator(
                                    progress = { (continueBook.readProgress ?: 68) / 100f },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(5.dp)
                                        .clip(RoundedCornerShape(3.dp))
                                        .padding(vertical = 1.dp),
                                    color = SageSecondary,
                                    trackColor = Color(0xFFDEE8FF),
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = continueBook.timeRemaining ?: "เหลืออีกประมาณ 42 นาที",
                                        fontSize = 11.sp,
                                        color = OutlineSlate
                                    )

                                    Surface(
                                        onClick = { onReadBook(continueBook) },
                                        shape = RoundedCornerShape(8.dp),
                                        color = SageSecondary,
                                        modifier = Modifier.testTag("continue_reading_button")
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.PlayArrow,
                                                contentDescription = null,
                                                tint = Color.White,
                                                modifier = Modifier.size(15.dp)
                                            )
                                            Spacer(modifier = Modifier.width(2.dp))
                                            Text(
                                                text = "อ่านต่อ",
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.White
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 5. Best Sellers of the Week
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "หนังสือขายดีประจำสัปดาห์",
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Serif,
                                    color = MidnightSlate
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Box(
                                    modifier = Modifier
                                        .background(PrimaryFixed, RoundedCornerShape(10.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "Top 10",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = OnPrimaryFixed
                                    )
                                }
                            }
                            Text(
                                text = "ผลงานที่นักอ่านกำลังหยิบขึ้นมาอ่านมากที่สุด",
                                fontSize = 12.sp,
                                color = SlateVariant,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                onBookClick(SampleBooksData.bestSellers[0])
                            }
                        ) {
                            Text(
                                text = "อันดับทั้งหมด",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = TerracottaPrimary
                            )
                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = null,
                                tint = TerracottaPrimary,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        items(SampleBooksData.bestSellers) { book ->
                            BestSellerCard(
                                book = book,
                                onClick = { onBookClick(book) }
                            )
                        }
                    }
                }
            }

            // 6. Curated Collection Spotlight ("มุมกาแฟยามบ่าย & หนังสือสั้น")
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 2.dp,
                                shape = RoundedCornerShape(16.dp),
                                spotColor = Color(0x1A1E293B)
                            )
                            .clickable {
                                onBookClick(SampleBooksData.bestSellers[2])
                            },
                        shape = RoundedCornerShape(16.dp),
                        color = Color(0xFFF0F3FF)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(44.dp)
                                        .background(CoffeeContainer, RoundedCornerShape(12.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Coffee,
                                        contentDescription = "Coffee Corner",
                                        tint = CoffeeOnContainer,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Column {
                                    Text(
                                        text = "มุมกาแฟยามบ่าย & หนังสือสั้น",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif,
                                        color = MidnightSlate
                                    )
                                    Text(
                                        text = "คัดสรรหนังสืออ่านจบได้ใน 60 นาที",
                                        fontSize = 12.sp,
                                        color = SlateVariant
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(Color.White, CircleShape)
                                    .shadow(elevation = 1.dp, shape = CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = null,
                                    tint = MidnightSlate,
                                    modifier = Modifier.size(17.dp)
                                )
                            }
                        }
                    }
                }
            }

            // 7. Recommended for You ("แนะนำสำหรับคุณ")
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "แนะนำสำหรับคุณ",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif,
                                color = MidnightSlate
                            )
                            Text(
                                text = "คัดเลือกตามรสนิยมการอ่านของคุณ",
                                fontSize = 12.sp,
                                color = SlateVariant
                            )
                        }

                        IconButton(
                            onClick = { /* Refresh recommendations */ },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Sync,
                                contentDescription = "Refresh",
                                tint = SlateVariant,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    SampleBooksData.recommendedBooks.forEach { book ->
                        RecommendedBookCard(
                            book = book,
                            onClick = { onBookClick(book) },
                            onBookmark = { onBookmarkBook(book) }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }

            // 8. Editorial Community Quote
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                Brush.horizontalGradient(
                                    listOf(Color(0xFFF0F3FF), Color(0xFFE7EEFF))
                                )
                            )
                            .padding(20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FormatQuote,
                            contentDescription = null,
                            tint = TerracottaPrimary.copy(alpha = 0.2f),
                            modifier = Modifier
                                .size(36.dp)
                                .align(Alignment.TopStart)
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "“หนังสือคือความฝันที่คุณสามารถถือไว้ในมือได้”",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = FontFamily.Serif,
                                fontStyle = FontStyle.Italic,
                                color = MidnightSlate,
                                lineHeight = 24.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "— นีล ไกแมน (Neil Gaiman)",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = SlateVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BestSellerCard(
    book: Book,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .width(140.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(14.dp),
                spotColor = Color(0x1A1E293B)
            )
            .clickable { onClick() }
            .testTag("bestseller_${book.id}"),
        shape = RoundedCornerShape(14.dp),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            // Book Cover with Rank Badge and Discount
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

                // Rank badge (1, 2, 3...)
                if (book.rank != null) {
                    Box(
                        modifier = Modifier
                            .padding(6.dp)
                            .size(22.dp)
                            .background(
                                color = if (book.rank == 1) TerracottaPrimary else Color(0xFFDEE8FF),
                                shape = CircleShape
                            )
                            .align(Alignment.TopStart),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = book.rank.toString(),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (book.rank == 1) Color.White else MidnightSlate
                        )
                    }
                }

                // Discount badge (-37%)
                if (book.discountPercent != null) {
                    Box(
                        modifier = Modifier
                            .padding(6.dp)
                            .background(
                                color = Color.White.copy(alpha = 0.92f),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                            .align(Alignment.BottomEnd)
                    ) {
                        Text(
                            text = "-${book.discountPercent}%",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = AmberTertiary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = book.title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = MidnightSlate,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 18.sp,
                modifier = Modifier.height(36.dp)
            )

            Text(
                text = book.author,
                fontSize = 11.sp,
                color = SlateVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 2.dp)
            )

            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = StarAmber,
                    modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = "${book.rating}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MidnightSlate
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "(${book.reviewsCount})",
                    fontSize = 10.sp,
                    color = OutlineSlate
                )
            }

            // Price
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.padding(top = 6.dp)
            ) {
                Text(
                    text = "฿${book.price}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TerracottaPrimary
                )
                if (book.originalPrice != null) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "฿${book.originalPrice}",
                        fontSize = 11.sp,
                        color = OutlineSlate,
                        textDecoration = TextDecoration.LineThrough
                    )
                }
            }
        }
    }
}

@Composable
private fun RecommendedBookCard(
    book: Book,
    onClick: () -> Unit,
    onBookmark: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = Color(0x1A1E293B)
            )
            .clickable { onClick() }
            .testTag("rec_card_${book.id}"),
        shape = RoundedCornerShape(16.dp),
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Book Cover
            Box(
                modifier = Modifier
                    .width(76.dp)
                    .height(108.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shadow(2.dp)
            ) {
                AsyncImage(
                    model = book.coverUrl,
                    contentDescription = book.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                // Match badge & category
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (book.matchPercent != null) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = SageSecondary.copy(alpha = 0.12f),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "ตรงใจคุณ ${book.matchPercent}%",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = SageSecondary
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                    Text(
                        text = book.category,
                        fontSize = 11.sp,
                        color = OutlineSlate
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = book.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = MidnightSlate,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "${book.author} • ${book.publisher}",
                    fontSize = 12.sp,
                    color = SlateVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 1.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "฿${book.price}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = TerracottaPrimary
                        )
                        if (book.originalPrice != null) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "฿${book.originalPrice}",
                                fontSize = 12.sp,
                                color = OutlineSlate,
                                textDecoration = TextDecoration.LineThrough
                            )
                        }
                    }

                    Surface(
                        onClick = onBookmark,
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFFF0F3FF),
                        modifier = Modifier.size(34.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = if (book.isInShelf) Icons.Filled.Bookmark else Icons.Outlined.BookmarkAdd,
                                contentDescription = "Bookmark",
                                tint = TerracottaPrimary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchBookResultItem(
    book: Book,
    onClick: () -> Unit,
    onBookmark: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .shadow(2.dp, RoundedCornerShape(12.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = book.coverUrl,
                contentDescription = book.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(54.dp)
                    .height(78.dp)
                    .clip(RoundedCornerShape(6.dp))
            )
            Spacer(modifier = Modifier.width(10.dp))
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
                Text(
                    text = book.author,
                    fontSize = 12.sp,
                    color = SlateVariant
                )
                Text(
                    text = "฿${book.price} • ${book.category}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TerracottaPrimary,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
            IconButton(onClick = onBookmark) {
                Icon(
                    imageVector = Icons.Outlined.BookmarkAdd,
                    contentDescription = null,
                    tint = TerracottaPrimary
                )
            }
        }
    }
}
