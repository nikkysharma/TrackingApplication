package com.example.trackingapplication.di

import android.content.Context
import androidx.room.Room
import com.example.trackingapplication.TrackingDao
import com.example.trackingapplication.TrackingRoomDataBase
import com.example.trackingapplication.data.TrackingRepositoryImpl
import com.example.trackingapplication.domain.TrackingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TrackModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): TrackingRoomDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            TrackingRoomDataBase::class.java,
            "tracking.db"
        ).build()
    }
    @Provides
    @Singleton
     fun provideRepo(
        trackingRepositoryImpl : TrackingRepositoryImpl
    ): TrackingRepository{
         return trackingRepositoryImpl
     }



    @Provides
    @Singleton
    fun provideTrackDao(db: TrackingRoomDataBase): TrackingDao = db.trackDao()

}