package ru.samsung.smartintercom.ui.screen.call.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import dagger.Component
import ru.samsung.smartintercom.App
import ru.samsung.smartintercom.di.AppComponent
import ru.samsung.smartintercom.ui.screen.call.CallViewModel

@Component(dependencies = [AppComponent::class])
@CallScreenScope
interface CallScreenComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): CallScreenComponent
    }

    val viewModel: CallViewModel

    companion object {
        @Composable
        fun build(): CallScreenComponent {
            return DaggerCallScreenComponent.factory().create(
                (LocalContext.current.applicationContext as App).appComponent
            )
        }
    }
}
