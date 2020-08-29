package org.openapitools.client.infrastructure

fun collectionDelimiter(collectionFormat: String) = when(collectionFormat) {
    "csv" -> ","
    "tsv" -> "\t"
    "pipe" -> "|"
    "space" -> " "
    else -> ""
}

sealed class QueryParam {
    data class Single(val value: String) : QueryParam()
    data class Multi(val values: Iterable<String>) : QueryParam()
}

class Queries(val queries: MutableMap<String, QueryParam> = mutableMapOf()) {
    constructor(queries: MutableMap<String, QueryParam> = mutableMapOf(), config: Queries.() -> Unit): this(queries) {
        config(this)
    }

    // Single
    fun add(name: String, value: String?) {
        if (value != null) {
            queries[name] = QueryParam.Single(value)
        }
    }

    fun add(name: String, value: Any?) {
        if (value != null) {
            queries[name] = QueryParam.Single(value.toString())
        }
    }

    // For Iterables
    fun addMulti(name: String, values: Iterable<Any?>, collectionFormat: String) {
        val transformed = values.filterNotNull().map { it.toString() }
        queries[name] = when (collectionFormat) {
            "multi" -> QueryParam.Multi(transformed)
            else -> QueryParam.Single(transformed.joinToString(separator = collectionDelimiter(collectionFormat)))
        }
    }

    // For Arrays
    fun addMulti(name: String, values: Array<Any?>, collectionFormat: String) =
        addMulti(name, values.asIterable(), collectionFormat)

    // For Maps
    fun addMap(values: Map<String, Any?>) {
        queries.putAll(values.filterValues { it != null }.mapValues { QueryParam.Single(it.value.toString()) })
    }
}
