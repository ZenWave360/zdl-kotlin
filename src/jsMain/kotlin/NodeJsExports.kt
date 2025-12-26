@file:JsExport

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

/**
 * API for parsing ZDL content
 *
 * Usage:
 * ```javascript
 * import { parseZdl } from '@zenwave360/zdl';
 * const model = parseZdl(zdlContent);
 * console.log(JSON.stringify(model, null, 2));
 * ```
 */
@OptIn(ExperimentalJsExport::class)
@JsExport
fun parseZdl(input: String): Any? {
    val parser = io.zenwave360.zdl.ZdlParser()
    val model = parser.parseModel(input)
    return convertToPlain(model as Map<*, *>)
}

private fun convertToPlain(value: Any?): Any? {
    return when (value) {
        null -> null
        is Map<*, *> -> {
            val result = js("{}")
            value.forEach { (k, v) ->
                result[k as String] = convertToPlain(v)
            }
            result
        }
        is List<*> -> {
            val result = js("[]")
            value.forEachIndexed { index, item ->
                result[index] = convertToPlain(item)
            }
            result
        }
        else -> value
    }
}
