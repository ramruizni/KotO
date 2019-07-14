package listener.morph

import antlr.KotlinParser

import listener.morph.Main.appendIfNotEmpty
import listener.morph.Main.appendReturnIfNotEmpty

public object Class {
    fun myClassDeclaration(input: String, ctx: KotlinParser.ClassDeclarationContext): String {
        var output = appendIfNotEmpty(ctx.modifiers())
        output += if (ctx.modifiers() != null) {
            input.substring(ctx.modifiers().stop.stopIndex + 1, ctx.simpleIdentifier().start.startIndex - 1)
        } else {
            input.substring(ctx.start.startIndex, ctx.simpleIdentifier().start.startIndex - 1)
        }
        output += " " + ctx.simpleIdentifier().text
        output += appendReturnIfNotEmpty(ctx.delegationSpecifiers())

        return "$output{"
    }

}
