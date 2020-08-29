/**
 * OpenAPI Petstore
 * This is a sample server Petstore server. For this sample, you can use the api key `special-key` to test the authorization filters.
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.openapitools.client.apis

import org.openapitools.client.models.Order

import org.openapitools.client.infrastructure.*
import io.ktor.client.request.forms.formData
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.forms.FormPart
import io.ktor.client.utils.EmptyContent
import kotlinx.serialization.json.Json
import io.ktor.http.ParametersBuilder
import kotlinx.serialization.Serializable
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.list
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class StoreApi constructor(
    baseUrl: kotlin.String = "http://petstore.swagger.io/v2",
    httpClientEngine: HttpClientEngine? = null,
    json: Json = Json {},
) : ApiClientBase(baseUrl, httpClientEngine, json) {
    /**
     * Delete purchase order by ID
     * For valid response try integer IDs with value &lt; 1000. Anything above 1000 or nonintegers will generate API errors
     * @param orderId ID of the order that needs to be deleted 
     * @return void
     */
    suspend fun deleteOrder(
        orderId: kotlin.String
    ): HttpResponse<Unit> {
        val authNamesOag = listOf<String>()

        val bodyOag = 
            EmptyContent

        val queriesOag = Queries {
        }

        val headersOag = mutableMapOf<String, String?>(
        )

        val configOag = RequestConfig(
            RequestMethod.DELETE,
            "/store/order/{orderId}".replace("{" + "orderId" + "}", orderId.toString()),
            queries = queriesOag,
            headers = headersOag
        )

        return request(
            configOag,
            bodyOag,
            authNamesOag
        ).wrap()
    }
    /**
     * Returns pet inventories by status
     * Returns a map of status codes to quantities
     * @return kotlin.collections.Map<kotlin.String, kotlin.Int>
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun getInventory(
    ): HttpResponse<kotlin.collections.Map<kotlin.String, kotlin.Int>> {
        val authNamesOag = listOf<String>("api_key")

        val bodyOag = 
            EmptyContent

        val queriesOag = Queries {
        }

        val headersOag = mutableMapOf<String, String?>(
        )

        val configOag = RequestConfig(
            RequestMethod.GET,
            "/store/inventory",
            queries = queriesOag,
            headers = headersOag
        )

        return request(
            configOag,
            bodyOag,
            authNamesOag
        ).wrap<GetInventoryResponse>().map { value }
    }
    @Serializable
    private class GetInventoryResponse(val value: Map<kotlin.String, kotlin.Int>) {
        @Serializer(GetInventoryResponse::class)
        companion object : KSerializer<GetInventoryResponse> {
            private val serializer: KSerializer<Map<kotlin.String, kotlin.Int>> = (kotlin.String.serializer() to kotlin.Int.serializer()).map
                override val descriptor = PrimitiveDescriptor("GetInventoryResponse", PrimitiveKind.STRING)
                override fun serialize(encoder: Encoder, value: GetInventoryResponse) = serializer.serialize(encoder, value.value)
                override fun deserialize(decoder: Decoder) = GetInventoryResponse(serializer.deserialize(decoder))
        }
    }
    /**
     * Find purchase order by ID
     * For valid response try integer IDs with value &lt;&#x3D; 5 or &gt; 10. Other values will generated exceptions
     * @param orderId ID of pet that needs to be fetched 
     * @return Order
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun getOrderById(
        orderId: kotlin.Long
    ): HttpResponse<Order> {
        val authNamesOag = listOf<String>()

        val bodyOag = 
            EmptyContent

        val queriesOag = Queries {
        }

        val headersOag = mutableMapOf<String, String?>(
        )

        val configOag = RequestConfig(
            RequestMethod.GET,
            "/store/order/{orderId}".replace("{" + "orderId" + "}", orderId.toString()),
            queries = queriesOag,
            headers = headersOag
        )

        return request(
            configOag,
            bodyOag,
            authNamesOag
        ).wrap()
    }
    /**
     * Place an order for a pet
     * 
     * @param body order placed for purchasing the pet 
     * @return Order
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun placeOrder(
        body: Order
    ): HttpResponse<Order> {
        val authNamesOag = listOf<String>()

        val bodyOag = body

        val queriesOag = Queries {
        }

        val headersOag = mutableMapOf<String, String?>(
        )

        val configOag = RequestConfig(
            RequestMethod.POST,
            "/store/order",
            queries = queriesOag,
            headers = headersOag
        )

        return jsonRequest(
            configOag,
            bodyOag,
            authNamesOag
        ).wrap()
    }


}
