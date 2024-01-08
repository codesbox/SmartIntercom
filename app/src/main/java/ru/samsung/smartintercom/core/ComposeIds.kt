/* НЕ ИЗМЕНЯЙТЕ ФАЙЛ! */
/* ВСЕ ИЗМЕНЕНИЯ ПРИ ТЕСТИРОВАНИИ БУДУТ ОТМЕНЕНЫ */
package ru.samsung.smartintercom.core

typealias ComposeIds = String

object MainScreenId {
    const val screenId: ComposeIds = "main_screen"
    const val buttonStart: ComposeIds = "button_start"
    const val buttonRetry: ComposeIds = "button_retry"
    const val textIntercomModel: ComposeIds = "text_intercom_model"
    const val buttonTakePhoto: ComposeIds = "button_take_photo"
    const val imageIntercom: ComposeIds = "image_intercom"
    const val textError: ComposeIds = "text_error"
    const val buttonSettings: ComposeIds = "button_settings"
    const val buttonHistory: ComposeIds = "button_history"
}

object SettingScreenId {
    const val screenId: ComposeIds = "setting_screen"
    const val inputHouse: ComposeIds = "input_house"
    const val inputFlat: ComposeIds = "input_flat"
    const val buttonSave: ComposeIds = "button_save"
}

object CallScreenId {
    const val screenId: ComposeIds = "call_screen"
    const val buttonOpen: ComposeIds = "button_open"
    const val buttonClose: ComposeIds = "button_close"
}

object CallHistoryId {
    const val screenId: ComposeIds = "history_screen"
    const val recycler: ComposeIds = "recycler"
    const val textDate: ComposeIds = "text_date"
    const val textStatus: ComposeIds = "text_status"
}
