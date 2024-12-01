fun main(args: Array<String>) {
    val firstColumn = 2
    val lastColumn = 55 //firstColumn + 26 - 1
    val row = 3
    val tableName = "asp_migra_period"

    print( getConcatString(tableName, firstColumn, lastColumn, row) )
}

fun getConcatString(tableName: String, firstColumn: Int, lastColumn: Int, row: Int): String {
    return "=CONCATENAR(" +
            getInsertPart(tableName, firstColumn, lastColumn, row) +
            " " +
            getValuesPart(firstColumn, lastColumn, row + 1) +
            ")"
}

fun getInsertPart(tableName: String, firstColumn: Int, lastColumn: Int, row: Int): String {
    var s = "\"INSERT INTO $tableName (\";"
    for (i in firstColumn..lastColumn) {
        s += getColumn(i, true)
        s += "\$" + row
        if (i != lastColumn)
            s += ";\", \";"
    }
    s += ";\")"
    return s
}

fun getValuesPart(firstColumn: Int, lastColumn: Int, row: Int): String {
    var s = "VALUES ('\";"
    for (i in firstColumn..lastColumn) {
        s += getColumn(i, false) + row
        if (i != lastColumn)
            s += ";\"', '\";"
    }
    s += ";\"');\""
    return s
}

fun getColumn(i: Int, isAbsRef: Boolean = false): String {
    val n1 = (i - 1) / 26
    val n2 = i - n1 * 26
    val s1: String = if (n1 > 0)
        ('A' + n1 - 1).toString()
    else
        ""
    val s2 = ('A' + n2 - 1).toString()
    return (if (isAbsRef) "\$" else "") + "$s1$s2"
}