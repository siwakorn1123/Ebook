package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Book
import com.example.ui.theme.MidnightSlate
import com.example.ui.theme.SageSecondary
import com.example.ui.theme.SlateVariant
import com.example.ui.theme.TerracottaPrimary

@Composable
fun ReaderDialog(
    book: Book,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    var fontSizeSp by remember { mutableFloatStateOf(16f) }
    var paperMode by remember { mutableIntStateOf(0) } // 0 = Archival Warm Cream, 1 = Pure White, 2 = Night
    var readingProgress by remember { mutableFloatStateOf(book.readProgress.toFloat().coerceAtLeast(10f)) }

    val bgColor = when (paperMode) {
        0 -> Color(0xFFFAF7F2) // Archival warm cream
        1 -> Color(0xFFFFFFFF) // Pure white
        else -> Color(0xFF1E293B) // Dark night
    }

    val textColor = when (paperMode) {
        2 -> Color(0xFFF1F5F9)
        else -> Color(0xFF1E293B)
    }

    val secondaryTextColor = when (paperMode) {
        2 -> Color(0xFF94A3B8)
        else -> Color(0xFF64748B)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(bgColor)
            .testTag("reader_screen")
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Reader Top Bar
            Surface(
                color = bgColor,
                shadowElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .height(56.dp)
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = onClose,
                        modifier = Modifier.testTag("reader_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Close Reader",
                            tint = textColor
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = book.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            color = textColor,
                            maxLines = 1
                        )
                        Text(
                            text = book.author,
                            fontSize = 10.sp,
                            color = secondaryTextColor,
                            maxLines = 1
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Font size toggle
                        IconButton(onClick = {
                            fontSizeSp = if (fontSizeSp >= 22f) 14f else fontSizeSp + 2f
                        }) {
                            Icon(
                                imageVector = Icons.Default.FormatSize,
                                contentDescription = "Font size",
                                tint = textColor,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        // Theme Mode toggle
                        IconButton(onClick = {
                            paperMode = (paperMode + 1) % 3
                        }) {
                            Icon(
                                imageVector = if (paperMode == 2) Icons.Default.WbSunny else Icons.Default.Nightlight,
                                contentDescription = "Reading Theme",
                                tint = textColor,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }

            // Book Content Scrollable
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 20.dp)
            ) {
                Text(
                    text = "บทนำ • สู่การทำความเข้าใจ",
                    fontSize = (fontSizeSp + 4).sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = TerracottaPrimary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Text(
                    text = "ในโลกแห่งการเรียนรู้ที่ไม่มีวันสิ้นสุด ความเงียบสงบและการเปิดใจรับฟังคือจุดเริ่มต้นของปัญญาทั้งมวล เมื่อเราเริ่มพลิกหน้ากระดาษหรือเลื่อนหน้าจอของอีบุ๊กเล่มโปรด ความคิดของเราได้เชื่อมโยงกับประสบการณ์อันล้ำค่าของผู้เขียนที่สั่งสมมาตลอดหลายปี",
                    fontSize = fontSizeSp.sp,
                    color = textColor,
                    lineHeight = (fontSizeSp * 1.65f).sp,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = book.synopsis,
                    fontSize = fontSizeSp.sp,
                    color = textColor,
                    lineHeight = (fontSizeSp * 1.65f).sp,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "“การเปลี่ยนแปลงที่ยิ่งใหญ่ไม่ได้เริ่มจากก้าวที่ยิ่งใหญ่ แต่เริ่มจากความมุ่งมั่นเล็กๆ ในแต่ละวัน ที่เราไม่ยอมละทิ้งมันไปกลางคัน”",
                    fontSize = (fontSizeSp + 1).sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TerracottaPrimary,
                    lineHeight = (fontSizeSp * 1.6f).sp,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .background(
                            color = TerracottaPrimary.copy(alpha = 0.08f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(14.dp)
                )

                Text(
                    text = "บทที่ 1: ความชัดเจนในเป้าหมาย\n\nมนุษย์เรามักสับสนระหว่างความยุ่งเหยิง (Being Busy) กับการสร้างคุณค่าที่แท้จริง (Being Productive) การทำงานหนักโดยไร้ทิศทางเปรียบเหมือนเรือพายที่พายวนอยู่ในสระว่ายน้ำ แม้จะเหนื่อยล้าจนแทบหมดแรง แต่ก็ไม่ได้พาเราออกสู่มหาสมุทรแห่งความสำเร็จที่แท้จริงเลยแม้แต่น้อย\n\nจงจัดสรรเวลาสำหรับการอ่านและการสะท้อนคิดวันละอย่างน้อย 20 นาที เพื่อให้จิตใจของคุณได้มีโอกาสจัดระเบียบข้อมูลและค้นพบคำตอบที่ซ่อนอยู่ภายใน",
                    fontSize = fontSizeSp.sp,
                    color = textColor,
                    lineHeight = (fontSizeSp * 1.65f).sp,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }

            // Reader Bottom Navigation & Progress Slider
            Surface(
                color = bgColor,
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "หน้า ${((readingProgress / 100f) * book.pagesCount).toInt()} / ${book.pagesCount}",
                            fontSize = 11.sp,
                            color = secondaryTextColor
                        )
                        Text(
                            text = "${readingProgress.toInt()}%",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = TerracottaPrimary
                        )
                    }

                    Slider(
                        value = readingProgress,
                        onValueChange = { readingProgress = it },
                        valueRange = 0f..100f,
                        colors = SliderDefaults.colors(
                            thumbColor = TerracottaPrimary,
                            activeTrackColor = TerracottaPrimary,
                            inactiveTrackColor = Color(0xFFDEE8FF)
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
