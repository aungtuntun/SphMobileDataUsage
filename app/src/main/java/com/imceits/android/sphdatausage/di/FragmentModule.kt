package com.imceits.android.sphdatausage.di

import com.imceits.android.sphdatausage.ui.MobileDataFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMobileDataFragment(): MobileDataFragment

}