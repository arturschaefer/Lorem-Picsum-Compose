package com.schaefer.lorempicsum.imagelist.di

import com.schaefer.lorempicsum.imagelist.api.ImageListAPI
import com.schaefer.lorempicsum.imagelist.data.datasource.ImageListDataSource
import com.schaefer.lorempicsum.imagelist.data.datasource.ImageListDataSourceImpl
import com.schaefer.lorempicsum.imagelist.data.repository.ImageListRepositoryImpl
import com.schaefer.lorempicsum.imagelist.domain.repository.ImageListRepository
import com.schaefer.lorempicsum.imagelist.domain.usecase.GetImageListUseCase
import com.schaefer.lorempicsum.imagelist.presentation.ImageListViewModel
import com.schaefer.lorempicsum.imagelist.presentation.usecase.GetImageListUseCaseImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val imageListModule = module {
    single {
        get<Retrofit>().create(ImageListAPI::class.java)
    }

    factory<ImageListDataSource> { ImageListDataSourceImpl(imageListAPI = get()) }

    factory<ImageListRepository> { ImageListRepositoryImpl(remoteDataSource = get()) }

    factory<GetImageListUseCase> { GetImageListUseCaseImpl(repository = get()) }

    viewModel { ImageListViewModel(getImageListUseCase = get()) }
}
