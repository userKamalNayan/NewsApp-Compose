package com.kamalnayan.newsapp.di

import com.kamalnayan.newsapp.data.datasource.INewsDataSource
import com.kamalnayan.newsapp.data.datasource.impl.NewsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** @Author Kamal Nayan
Created on: 28/12/23
 **/
@Module
@InstallIn(SingletonComponent::class)
abstract class  DatasourceModule {

 @Binds
 @Singleton
 internal abstract fun bindNewsDataSource(newsDataSourceImpl: NewsDataSourceImpl): INewsDataSource

}