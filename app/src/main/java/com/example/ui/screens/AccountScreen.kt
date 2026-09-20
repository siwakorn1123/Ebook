package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.SampleBooksData
import com.example.ui.theme.AmberTertiary
import com.example.ui.theme.MidnightSlate
import com.example.ui.theme.OutlineSlate
import com.example.ui.theme.SageSecondary
import com.example.ui.theme.SlateVariant
import com.example.ui.theme.TerracottaPrimary

@Composable
fun AccountScreen(
    userCoins: Int,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("account_screen"),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        // User Profile Header
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(4.dp, RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                color = Color.White
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(
                            model = SampleBooksData.AVATAR_URL,
                            contentDescription = "User Avatar",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .border(2.dp, TerracottaPrimary.copy(alpha = 0.3f), CircleShape)
                        )

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "ศรุดา พัฒนพาณิชย์",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif,
                                color = MidnightSlate
                            )
                            Text(
                                text = "saruda.p@readscape.co.th",
                                fontSize = 12.sp,
                                color = SlateVariant
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFFFFDBCF), RoundedCornerShape(6.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "Silver Reader Member",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TerracottaPrimary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = Color(0xFFF0F3FF))
                    Spacer(modifier = Modifier.height(14.dp))

                    // Coins Wallet Section
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF0F3FF), RoundedCornerShape(12.dp))
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Paid,
                                contentDescription = null,
                                tint = AmberTertiary,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = "เหรียญสะสมของคุณ",
                                    fontSize = 11.sp,
                                    color = SlateVariant
                                )
                                Text(
                                    text = "$userCoins เหรียญ",
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MidnightSlate
                                )
                            }
                        }

                        Surface(
                            onClick = { /* Top up coins */ },
                            shape = RoundedCornerShape(8.dp),
                            color = TerracottaPrimary
                        ) {
                            Text(
                                text = "+ เติมเหรียญ",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }
        }

        // Reading Stats Summary (3 columns)
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StatCard(
                    icon = Icons.Outlined.MenuBook,
                    value = "14",
                    label = "เล่มที่อ่านจบ",
                    iconColor = SageSecondary,
                    modifier = Modifier.weight(1f)
                )

                StatCard(
                    icon = Icons.Default.LocalFireDepartment,
                    value = "18 วัน",
                    label = "สตรีคการอ่าน",
                    iconColor = TerracottaPrimary,
                    modifier = Modifier.weight(1f)
                )

                StatCard(
                    icon = Icons.Default.Timer,
                    value = "57 ชม.",
                    label = "เวลาอ่านรวม",
                    iconColor = AmberTertiary,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Annual Reading Goal Tracker
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(3.dp, RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                color = Color.White
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Outlined.EmojiEvents,
                                contentDescription = null,
                                tint = AmberTertiary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "เป้าหมายการอ่านปี 2026",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif,
                                color = MidnightSlate
                            )
                        }
                        Text(
                            text = "14 / 20 เล่ม (70%)",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = SageSecondary
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    LinearProgressIndicator(
                        progress = { 0.7f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = SageSecondary,
                        trackColor = Color(0xFFDEE8FF)
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "เหลืออีกเพียง 6 เล่ม จะบรรลุเป้าหมายประจำปีของคุณแล้ว!",
                        fontSize = 11.sp,
                        color = SlateVariant
                    )
                }
            }
        }

        // Account Setting Items
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .shadow(2.dp, RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                color = Color.White
            ) {
                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                    AccountMenuItem(icon = Icons.Default.ReceiptLong, title = "ประวัติการสั่งซื้อหนังสือ")
                    HorizontalDivider(color = Color(0xFFF0F3FF))
                    AccountMenuItem(icon = Icons.Default.Settings, title = "การตั้งค่าขนาดตัวอักษรและแอป")
                    HorizontalDivider(color = Color(0xFFF0F3FF))
                    AccountMenuItem(icon = Icons.Default.HelpOutline, title = "ศูนย์ช่วยเหลือ & สอบถามสำนักพิมพ์")
                }
            }
        }
    }
}

@Composable
private fun StatCard(
    icon: ImageVector,
    value: String,
    label: String,
    iconColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.shadow(2.dp, RoundedCornerShape(14.dp)),
        shape = RoundedCornerShape(14.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = value,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MidnightSlate
            )
            Text(
                text = label,
                fontSize = 10.sp,
                color = SlateVariant
            )
        }
    }
}

@Composable
private fun AccountMenuItem(
    icon: ImageVector,
    title: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = SlateVariant,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = MidnightSlate
            )
        }
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = OutlineSlate,
            modifier = Modifier.size(18.dp)
        )
    }
}
