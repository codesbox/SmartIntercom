package ru.samsung.smartintercom.ui.activity.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import dagger.Component
import ru.samsung.smartintercom.App
import ru.samsung.smartintercom.di.AppComponent
import ru.samsung.smartintercom.ui.activity.MainActivityViewModel

@Component(dependencies = [AppComponent::class])
@MainActivityScope
interface MainActivityComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): MainActivityComponent
    }

    val viewModel: MainActivityViewModel

    companion object {
        @Composable
        fun build(): MainActivityComponent {
            return DaggerMainActivityComponent.factory().create(
                (LocalContext.current.applicationContext as App).appComponent
            )
        }
    }
}
