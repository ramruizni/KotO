package typealias;

public class Constants {

    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] DIGITS = "0123456789".toCharArray();

    static final char[] TYPEALIAS_CHARS = "-_()*<>|·$%&/=?¿.,:;¨´{}[]".toCharArray();

    static final String[] KOTLIN_HARD_KEYWORDS = {
            "as",
            "as?",
            "break",
            "class",
            "continue",
            "do",
            "else",
            "false",
            "for",
            "fun",
            "if",
            "in",
            "!in",
            "interface",
            "is",
            "!is",
            "null",
            "object",
            "package",
            "return",
            "super",
            "this",
            "throw",
            "true",
            "try",
            "typealias",
            "val",
            "var",
            "when",
            "while"
    };

    public static final String[] KOTLIN_SOFT_KEYWORDS = {
            "by",
            "catch",
            "constructor",
            "delegate",
            "dynamic",
            "field",
            "file",
            "finally",
            "get",
            "import",
            "init",
            "param",
            "property",
            "receiver",
            "set",
            "setparam",
            "where"
    };

    public static final String[] KOTLIN_MODIFIER_KEYWORDS = {
            "actual",
            "abstract",
            "annotation",
            "companion",
            "const",
            "crossinline",
            "data",
            "enum",
            "expect",
            "external",
            "final",
            "infix",
            "inline",
            "inner",
            "internal",
            "lateinit",
            "noinline",
            "open",
            "operator",
            "out",
            "override",
            "private",
            "protected",
            "public",
            "reified",
            "sealed",
            "suspend",
            "tailrec",
            "vararg"
    };

    public static final String[] KOTLIN_SPECIAL_IDENTIFIERS = {
            "field",
            "it"
    };

    public static final String[] KOTLIN_OPERATORS_AND_SPECIAL_SYMBOLS = {
            "+",
            "-",
            "*",
            "/",
            "%",
            "=",
            "+=",
            "-=",
            "*=",
            "/=",
            "%=",
            "++",
            "--",
            "&&",
            "||",
            "!",
            "==",
            "!=",
            "===",
            "!==",
            "<",
            ">",
            "<=",
            ">=",
            "[",
            "]",
            "!!",
            "?.",
            "?:",
            "::",
            "..",
            ":",
            "?",
            "->",
            "@",
            ";",
            "$",
            "_"
    };

}
