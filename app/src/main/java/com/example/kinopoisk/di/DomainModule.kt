package com.example.kinopoisk.di

import com.example.kinopoisk.domain.usecases.GetDynamicGenreCountryPagedUseCase
import com.example.kinopoisk.domain.usecases.GetDynamicGenreCountryPagedUseCaseImpl
import com.example.kinopoisk.domain.usecases.GetDynamicGenreCountryUseCase
import com.example.kinopoisk.domain.usecases.GetDynamicGenreCountryUseCaseImpl
import com.example.kinopoisk.domain.usecases.GetMovieDetails
import com.example.kinopoisk.domain.usecases.GetMovieDetailsByMovieIdImpl
import com.example.kinopoisk.domain.usecases.GetPopularPagedUseCase
import com.example.kinopoisk.domain.usecases.GetPopularPagedUseCaseImpl
import com.example.kinopoisk.domain.usecases.GetPopularUseCase
import com.example.kinopoisk.domain.usecases.GetPopularUseCaseImpl
import com.example.kinopoisk.domain.usecases.GetPremieresPagedUseCase
import com.example.kinopoisk.domain.usecases.GetPremieresPagedUseCaseImpl
import com.example.kinopoisk.domain.usecases.GetPremieresUseCase
import com.example.kinopoisk.domain.usecases.GetPremieresUseCaseImpl
import com.example.kinopoisk.domain.usecases.GetSeriesPagedUseCase
import com.example.kinopoisk.domain.usecases.GetSeriesPagedUseCaseImpl
import com.example.kinopoisk.domain.usecases.GetSeriesUseCase
import com.example.kinopoisk.domain.usecases.GetSeriesUseCaseImpl
import com.example.kinopoisk.domain.usecases.GetTopListPagedUseCase
import com.example.kinopoisk.domain.usecases.GetTopListPagedUseCaseImpl
import com.example.kinopoisk.domain.usecases.GetTopListUseCase
import com.example.kinopoisk.domain.usecases.GetTopListUseCaseImpl
import com.example.kinopoisk.domain.usecases.RefreshCountryGenreSettingsUseCase
import com.example.kinopoisk.domain.usecases.RefreshCountryGenreSettingsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun  bindGetDynamicCountryPageUseCase(
        impl: GetDynamicGenreCountryPagedUseCaseImpl
    ): GetDynamicGenreCountryPagedUseCase

    @Binds
    @Singleton
    abstract fun bindGetDynamicCountryUseCase(
        impl: GetDynamicGenreCountryUseCaseImpl
    ): GetDynamicGenreCountryUseCase

    @Binds
    @Singleton
    abstract fun bindGetPopularPagedUseCase(
        impl: GetPopularPagedUseCaseImpl
    ): GetPopularPagedUseCase

    @Binds
    @Singleton
    abstract fun bindGetPopularUseCase(
        impl: GetPopularUseCaseImpl
    ): GetPopularUseCase

    @Binds
    @Singleton
    abstract fun bindGetPremieresPagedUseCase(
        impl: GetPremieresPagedUseCaseImpl
    ): GetPremieresPagedUseCase

    @Binds
    @Singleton
    abstract fun bindGetPremieresUseCase(
        impl: GetPremieresUseCaseImpl
    ): GetPremieresUseCase

    @Binds
    @Singleton
    abstract fun bindGetSeriesPagedUseCase(
        impl: GetSeriesPagedUseCaseImpl
    ): GetSeriesPagedUseCase

    @Binds
    @Singleton
    abstract fun bindGetSeriesUseCase(
        impl: GetSeriesUseCaseImpl
    ): GetSeriesUseCase

    @Binds
    @Singleton
    abstract fun bindGetTopListPagedUseCase(
        impl: GetTopListPagedUseCaseImpl
    ): GetTopListPagedUseCase

    @Binds
    @Singleton
    abstract fun bindGetTopListUseCase(
        impl: GetTopListUseCaseImpl
    ): GetTopListUseCase

    @Binds
    @Singleton
    abstract fun bindRefreshCountryGenreSettingsUseCase(
        impl: RefreshCountryGenreSettingsUseCaseImpl
    ): RefreshCountryGenreSettingsUseCase

    @Binds
    @Singleton
    abstract fun bindGetMovieByMovieIdUseCase(
        impl: GetMovieDetailsByMovieIdImpl
    ): GetMovieDetails
}