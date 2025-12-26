package io.zenwave360.zfl

import io.zenwave360.antlr.ZflLexer
import io.zenwave360.zfl.internal.ZflListenerImpl
import io.zenwave360.zfl.internal.ZflModel
import org.antlr.v4.kotlinruntime.CharStreams
import org.antlr.v4.kotlinruntime.CommonTokenStream
import org.antlr.v4.kotlinruntime.tree.ParseTreeWalker

class ZflParser {

    fun parseModel(model: String): ZflModel {
        val zfl = CharStreams.fromString(model)
        val lexer = ZflLexer(zfl)
        val tokens = CommonTokenStream(lexer)
        val parser = io.zenwave360.antlr.ZflParser(tokens)
        val listener = ZflListenerImpl()
        val zflRoot = parser.zfl()
        ParseTreeWalker.DEFAULT.walk(listener, zflRoot)

        return listener.model
    }
}

