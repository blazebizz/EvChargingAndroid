package ai.heart.data.route.di


import ai.heart.data.route.repositories.RouteRepo
import ai.heart.data.route.repositories.RouteRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RouteStartUp {
    @Provides
    @Singleton
    fun provideRouteRepository(
        client: HttpClient
    ): RouteRepo {
        return RouteRepoImpl(
            client = client
        )
    }
}