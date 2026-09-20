package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme =
  lightColorScheme(
    primary = TerracottaPrimary,
    onPrimary = TerracottaOnPrimary,
    primaryContainer = TerracottaPrimaryContainer,
    onPrimaryContainer = TerracottaOnPrimaryContainer,
    secondary = SageSecondary,
    onSecondary = SageOnSecondary,
    secondaryContainer = SageSecondaryContainer,
    onSecondaryContainer = SageOnSecondaryContainer,
    tertiary = AmberTertiary,
    onTertiary = AmberOnTertiary,
    tertiaryContainer = AmberTertiaryContainer,
    background = ArchivalBackground,
    onBackground = MidnightSlate,
    surface = ArchivalSurface,
    onSurface = MidnightSlate,
    surfaceVariant = SurfaceContainerHighest,
    onSurfaceVariant = SlateVariant,
    outline = OutlineSlate,
    outlineVariant = OutlineVariant,
  )

private val DarkColorScheme =
  darkColorScheme(
    primary = PrimaryFixed,
    onPrimary = OnPrimaryFixed,
    primaryContainer = TerracottaPrimaryContainer,
    secondary = SageSecondaryContainer,
    onSecondary = SageOnSecondaryContainer,
    tertiary = AmberTertiaryFixed,
    background = MidnightSlate,
    surface = MidnightSlate,
    onBackground = PaperWhite,
    onSurface = PaperWhite,
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }

      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}

@Composable
fun ReadscapeTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) = MyApplicationTheme(darkTheme, dynamicColor, content)

