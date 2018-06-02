package com.takeiteasy.vip.artistslist.di.components

import com.takeiteasy.vip.artistslist.App
import com.takeiteasy.vip.artistslist.di.modules.AppModule
import com.takeiteasy.vip.artistslist.di.modules.BuildersModule
import com.takeiteasy.vip.artistslist.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, NetworkModule::class, BuildersModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder
        fun build(): AppComponent
    }

    fun inject(application: App)
}