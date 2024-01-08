package ru.samsung.smartintercom.ui.screen.main.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import dagger.Component
import ru.samsung.smartintercom.App
import ru.samsung.smartintercom.di.AppComponent
import ru.samsung.smartintercom.ui.screen.main.MainViewModel

@Component(dependencies = [AppComponent::class])
@MainScreenScope
interface MainScreenComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): MainScreenComponent
    }

    val viewModel: MainViewModel

    companion object {
        @Composable
        fun build(): MainScreenComponent {
            return DaggerMainScreenComponent.factory().create(
                (LocalContext.current.applicationContext as App).appComponent
            )
        }
    }
}
