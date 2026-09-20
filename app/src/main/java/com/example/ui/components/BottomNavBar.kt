package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.LocalLibrary
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.LocalLibrary
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.AppTab
import com.example.ui.theme.SageSecondary
import com.example.ui.theme.SlateVariant
import com.example.ui.theme.TerracottaPrimary

@Composable
fun ReadscapeBottomNavBar(
    currentTab: AppTab,
    bookshelfCount: Int,
    onTabSelected: (AppTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(20.dp),
                    spotColor = Color(0x331E293B),
                    ambientColor = Color(0x1A1E293B)
                ),
            shape = RoundedCornerShape(20.dp),
            color = Color.White.copy(alpha = 0.96f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavItem(
                    label = "หน้าแรก",
                    selectedIcon = Icons.Filled.AutoStories,
                    unselectedIcon = Icons.Outlined.AutoStories,
                    isSelected = currentTab == AppTab.HOME,
                    testTag = "nav_home",
                    onClick = { onTabSelected(AppTab.HOME) }
                )

                NavItem(
                    label = "สำรวจ & หมวดหมู่",
                    selectedIcon = Icons.Filled.Explore,
                    unselectedIcon = Icons.Outlined.Explore,
                    isSelected = currentTab == AppTab.EXPLORE,
                    testTag = "nav_explore",
                    onClick = { onTabSelected(AppTab.EXPLORE) }
                )

                NavItem(
                    label = "ชั้นหนังสือ",
                    selectedIcon = Icons.Filled.LocalLibrary,
                    unselectedIcon = Icons.Outlined.LocalLibrary,
                    isSelected = currentTab == AppTab.BOOKSHELF,
                    badgeCount = bookshelfCount,
                    testTag = "nav_bookshelf",
                    onClick = { onTabSelected(AppTab.BOOKSHELF) }
                )

                NavItem(
                    label = "บัญชีของฉัน",
                    selectedIcon = Icons.Filled.Person,
                    unselectedIcon = Icons.Outlined.Person,
                    isSelected = currentTab == AppTab.ACCOUNT,
                    testTag = "nav_account",
                    onClick = { onTabSelected(AppTab.ACCOUNT) }
                )
            }
        }
    }
}

@Composable
private fun NavItem(
    label: String,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    isSelected: Boolean,
    badgeCount: Int? = null,
    testTag: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(56.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 8.dp)
            .testTag(testTag)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = if (isSelected) selectedIcon else unselectedIcon,
                contentDescription = label,
                tint = if (isSelected) TerracottaPrimary else SlateVariant,
                modifier = Modifier.size(23.dp)
            )

            if (badgeCount != null && badgeCount > 0) {
                Box(
                    modifier = Modifier
                        .offset(x = 10.dp, y = (-8).dp)
                        .background(SageSecondary, CircleShape)
                        .padding(horizontal = 4.dp, vertical = 1.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = badgeCount.toString(),
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) TerracottaPrimary else SlateVariant,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}
