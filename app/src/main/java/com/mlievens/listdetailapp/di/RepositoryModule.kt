package com.mlievens.listdetailapp.di

import com.mlievens.listdetailapp.data.repositories.ItemDetailDataRepository
import com.mlievens.listdetailapp.data.repositories.ItemListDataRepository
import com.mlievens.listdetailapp.data.service.MagicItemsService
import com.mlievens.listdetailapp.domain.repositories.ItemDetailRepository
import com.mlievens.listdetailapp.domain.repositories.ItemListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideItemListRepository(magicItemsService: MagicItemsService): ItemListRepository =
        ItemListDataRepository(magicItemsService)

    @Provides
    fun provideItemDetailRepository(magicItemsService: MagicItemsService): ItemDetailRepository =
        ItemDetailDataRepository(magicItemsService)

}