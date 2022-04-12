package com.example.myapplication.di

import com.example.mytesting.data.local.Dao.ItemsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import myappnew.com.conserve.data.AppDatabase


@Module
@InstallIn(ViewModelComponent::class)
object RoomModel {

    @Provides
    @ViewModelScoped
    fun provideItemDao(appDatabase: AppDatabase): ItemsDao {
        return appDatabase.itemDao()
    }

//    @Provides
//    @ViewModelScoped
//    fun provideAppDatabase(@ApplicationContext appContext: Context): NoteDataBase {
//        return Room.databaseBuilder(
//            appContext,
//            NoteDataBase::class.java,
//            "note_DB"
//        ).build()
//    }

//    @Provides
//    @ViewModelScoped
//    fun provideDefaultHomeRepository(dao:NoteDao): DefaultHomeRepository {
//        return DefaultHomeRepository(dao)
//    }


}