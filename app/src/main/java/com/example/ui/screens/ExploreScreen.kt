package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.ui.theme.SlateVariant
import com.example.ui.theme.StarAmber
import com.example.ui.theme.TerracottaPrimary

data class CategoryCardItem(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val bgColors: List<Color>
)

@Composable
fun ExploreScreen(
    onBookClick: (Book) -> Unit,
    modifier: Modifier = Modifier
) {
    val categoryCards = listOf(
        CategoryCardItem("จิตวิทยา & อารมณ์", "เข้าใจตนเองและผู้อื่น", Icons.Default.Psychology, listOf(Color(0xFFFFE0D6), Color(0xFFFFD1C2))),
        CategoryCardItem("ธุรกิจ & การเงิน", "สร้างความมั่งคั่งยุคใหม่", Icons.Default.BusinessCenter, listOf(Color(0xFFE2F9EE), Color(0xFFC7F3DE))),
        CategoryCardItem("เทคโนโลยี & AI", "ก้าวทันอนาคต", Icons.Default.Memory, listOf(Color(0xFFE5EEFF), Color(0xFFD0E0FF))),
        CategoryCardItem("นิยาย & วรรณกรรม", "เรื่องราวดื่มด่ำหัวใจ", Icons.Default.MenuBook, listOf(Color(0xFFFFF0D6), Color(0xFFFFE3B8))),
        CategoryCardItem("พัฒนาตนเอง", "เป็นคนที่ดีขึ้น 1% ทุกวัน", Icons.Default.AutoAwesome, listOf(Color(0xFFF3E8FF), Color(0xFFE9D5FF))),
        CategoryCardItem("ประวัติศาสตร์ & สังคม", "บทเรียนจากอดีต", Icons.Default.Public, listOf(Color(0xFFDEF8F8), Color(0xFFC4F0F0)))
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("explore_screen"),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        // Title Header
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "สำรวจ & ค้นพบ",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = MidnightSlate
                )
                Text(
                    text = "คัดสรรหมวดหมู่หนังสือชั้นนำและหนังสือเสียงคุณภาพ",
                    fontSize = 12.sp,
                    color = SlateVariant,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }

        // Category Cards (2 columns)
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                val chunks = categoryCards.chunked(2)
                chunks.forEach { pair ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        pair.forEach { item ->
                            Box(modifier = Modifier.weight(1f)) {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(84.dp)
                                        .shadow(2.dp, RoundedCornerShape(14.dp))
                                        .clickable {
                                            val matched = SampleBooksData.getAllCatalog().firstOrNull { it.category.contains(item.title.take(3)) }
                                            if (matched != null) onBookClick(matched)
                                        },
                                    shape = RoundedCornerShape(14.dp),
                                    color = Color.White
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Brush.horizontalGradient(item.bgColors))
                                            .padding(12.dp)
                                    ) {
                                        Column(modifier = Modifier.align(Alignment.CenterStart)) {
                                            Text(
                                                text = item.title,
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Bold,
                                                fontFamily = FontFamily.Serif,
                                                color = MidnightSlate
                                            )
                                            Text(
                                                text = item.subtitle,
                                                fontSize = 10.sp,
                                                color = SlateVariant,
                                                maxLines = 1
                                            )
                                        }
                                        Icon(
                                            imageVector = item.icon,
                                            contentDescription = null,
                                            tint = MidnightSlate.copy(alpha = 0.4f),
                                            modifier = Modifier
                                                .size(26.dp)
                                                .align(Alignment.BottomEnd)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Audiobooks Section
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp)
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
                            imageVector = Icons.Default.Headphones,
                            contentDescription = null,
                            tint = TerracottaPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "หนังสือเสียงแนะนำ (Audiobooks)",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            color = MidnightSlate
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                val audiobooks = listOf(
                    SampleBooksData.bookshelfItems[1], // Sapiens
                    SampleBooksData.continueReadingBook,
                    SampleBooksData.bestSellers[1]
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(audiobooks) { book ->
                        Surface(
                            modifier = Modifier
                                .width(140.dp)
                                .shadow(3.dp, RoundedCornerShape(12.dp))
                                .clickable { onBookClick(book) },
                            shape = RoundedCornerShape(12.dp),
                            color = Color.White
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
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
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.BottomEnd)
                                            .padding(6.dp)
                                            .background(Color.Black.copy(alpha = 0.6f), CircleShape)
                                            .padding(4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Headphones,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(14.dp)
                                        )
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
                                    text = book.author,
                                    fontSize = 10.sp,
                                    color = SlateVariant,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Text(
                                    text = "฿${book.price}",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TerracottaPrimary,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
