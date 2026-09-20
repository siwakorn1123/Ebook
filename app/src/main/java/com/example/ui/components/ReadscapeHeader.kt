package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Paid
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.SampleBooksData
import com.example.ui.theme.AmberTertiary
import com.example.ui.theme.MidnightSlate
import com.example.ui.theme.PaperWhite
import com.example.ui.theme.SlateVariant
import com.example.ui.theme.TerracottaPrimary

@Composable
fun ReadscapeHeader(
    coins: Int,
    notificationCount: Int,
    onProfileClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Logo & Brand Name
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            AsyncImage(
                model = SampleBooksData.LOGO_URL,
                contentDescription = "Readscape Logo",
                modifier = Modifier
                    .height(34.dp)
                    .width(34.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Readscape",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = TerracottaPrimary,
                    lineHeight = 22.sp
                )
                Text(
                    text = "ร้านอีบุ๊กสัญชาติไทย",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = SlateVariant,
                    lineHeight = 14.sp
                )
            }
        }

        // Coins Badge
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(
                    color = Color(0xFFF0F3FF),
                    shape = RoundedCornerShape(16.dp)
                )
                .border(
                    width = 0.5.dp,
                    color = Color(0xFFDEE8FF),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Paid,
                contentDescription = "Coins",
                tint = AmberTertiary,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = coins.toString(),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MidnightSlate
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        // Notification Bell
        Box {
            IconButton(
                onClick = onNotificationClick,
                modifier = Modifier
                    .size(40.dp)
                    .testTag("notification_button")
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notifications",
                    tint = SlateVariant,
                    modifier = Modifier.size(22.dp)
                )
            }
            if (notificationCount > 0) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(TerracottaPrimary, CircleShape)
                        .align(Alignment.TopEnd)
                        .padding(top = 8.dp, end = 8.dp)
                )
            }
        }

        // Profile Avatar
        AsyncImage(
            model = SampleBooksData.AVATAR_URL,
            contentDescription = "Profile Avatar",
            modifier = Modifier
                .size(34.dp)
                .shadow(elevation = 2.dp, shape = CircleShape)
                .clip(CircleShape)
                .border(1.dp, Color.White, CircleShape)
                .clickable { onProfileClick() }
                .testTag("profile_avatar"),
            contentScale = ContentScale.Crop
        )
    }
}
