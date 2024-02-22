package com.bed.ohhferta.framework.clients

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import kotlinx.serialization.json.Json
import kotlinx.serialization.ExperimentalSerializationApi

import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode

import io.ktor.serialization.kotlinx.json.json

import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO

import io.ktor.client.HttpClientConfig
import io.ktor.client.HttpClient as KtorClient

import io.ktor.client.engine.cio.endpoint
import io.ktor.client.engine.cio.CIOEngineConfig

import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.request.HttpRequestBuilder

import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation

import com.bed.ohhferta.domain.result.Result

import com.bed.ohhferta.framework.clients.paths.Paths

interface HttpClient {
    val http: KtorClient
}

class HttpClientImpl : HttpClient {

    private val timeout = 15000L

    @OptIn(ExperimentalSerializationApi::class)
    private val configureJson get() = Json {
        explicitNulls = false
        encodeDefaults = false

        isLenient = true
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    override val http get() = KtorClient(CIO) {
        configureLogging()
        configureRequestDefault()
        configureResponseTimeout()
        configureResponseObserver()
        configureContentNegotiation()
    }

    private fun HttpClientConfig<CIOEngineConfig>.configureLogging() {
        install(Logging) {
            level = LogLevel.INFO
            level = LogLevel.HEADERS
            filter { it.url.host.contains("http") }
        }
    }

    private fun HttpClientConfig<CIOEngineConfig>.configureRequestDefault() {
        install(DefaultRequest) {
            url(Paths.API.value)
            headers {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }

    private fun HttpClientConfig<CIOEngineConfig>.configureResponseObserver() {
        install(ResponseObserver) {
            onResponse { response ->
                println("\n\n[KTOR HTTP STATUS]: ${response.status.value}\n\n")
                println("\n\n[KTOR HTTP RESPONSE]: ${response.body<String>()}\n\n")
            }
        }
    }

    private fun HttpClientConfig<CIOEngineConfig>.configureResponseTimeout() {
        install(HttpTimeout) {
            socketTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            connectTimeoutMillis = timeout
        }
    }

    private fun HttpClientConfig<CIOEngineConfig>.configureContentNegotiation() {
        install(ContentNegotiation) {
            json(configureJson)

            @Suppress("MagicNumber")
            engine {
                maxConnectionsCount = 1000
                endpoint {
                    connectAttempts = 5
                    pipelineMaxSize = 20
                    keepAliveTime = 5000
                    connectTimeout = 5000
                    maxConnectionsPerRoute = 100
                }
            }
        }
    }
}

suspend inline fun <reified F : Any, reified S : Any> KtorClient.request(
    crossinline block: HttpRequestBuilder.() -> Unit,
): Flow<Result<F, S>> = flow {
    val response = request { block() }

    close()

    when (response.status) {
        HttpStatusCode.OK, HttpStatusCode.Created -> emit(Result.Success(response.body<S>()))
        else -> emit(Result.Failure(response.body<F>()))
    }
}
