package ru.samsung.smartintercom.utils

import androidx.compose.runtime.Stable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import ru.samsung.smartintercom.ui.screen.ScreenBaseData

@OptIn(ExperimentalComposeUiApi::class)
@Stable
fun Modifier.setupScreenData(screenBaseData: ScreenBaseData) = semantics {
    testTagsAsResourceId = true
}
    .testTag(tag = screenBaseData.name)

