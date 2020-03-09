package com.imceits.android.sphdatausage.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imceits.android.sphdatausage.ui.MobileDataViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(appViewModelFactory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MobileDataViewModel::class)
    abstract fun bindMobileDataViewModel(mobileDataViewModel: MobileDataViewModel): ViewModel
}