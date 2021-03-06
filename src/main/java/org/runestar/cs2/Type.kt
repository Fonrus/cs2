package org.runestar.cs2

enum class Type(
        val desc: Char? = null
) {

    INT('i'),
    STRING('s'),
    COMPONENT('I'),
    BOOLEAN('1'),
    OBJ('o'),
    ENUM('g'),
    STAT('S'),
    GRAPHIC('d'),
    INV('v'),
    MODEL('m'),
    COORDGRID('c'),
    CATEGORY('y'),
    LOC('l'),
    AREA('R'),
    MAPAREA('`'),
    NAMEDOBJ('O'),
    FONTMETRICS('f'),
    CHAR('z'),

    TYPE(),
    COLOUR(),
    IFTYPE(),
    SETSIZE(),
    SETPOSH(),
    SETPOSV(),
    SETTEXTALIGNH(),
    SETTEXTALIGNV(),
    ;

    val nameLiteral: String = name.toLowerCase()

    val typeLiteral: String get() = if (desc == null) INT.nameLiteral else nameLiteral

    val topType: Type get() = if (this == STRING) Type.STRING else Type.INT

    companion object {

        private val map = values().filter { it.desc != null }.associateBy { it.desc }

        fun of(desc: Char): Type = map.getValue(desc)

        fun of(desc: Int): Type = of(desc.toChar())

        fun bottom(a: Type, b: Type): Type {
            if (a == b) return a
            require(a.topType == b.topType)
            if (a == a.topType) return b
            if (b == b.topType) return a
            if ((a == OBJ || a == NAMEDOBJ) && (b == OBJ || b == NAMEDOBJ)) return OBJ
            if ((a == FONTMETRICS || a == GRAPHIC) && (b == FONTMETRICS || b == GRAPHIC)) return FONTMETRICS
            throw IllegalArgumentException("$a $b")
        }
    }
}