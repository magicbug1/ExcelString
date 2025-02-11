fun main() {
    print("Número primera columna [1]: ")
    val firstColumn = getNumber(1)

    print("Cuantas columnas? [10]: ")
    val numberColmns = getNumber(10)
    val lastColumn = firstColumn + numberColmns - 1

    print("Fila cabecera? [1]: ")
    val row = getNumber(1)

    print("Nombre tabla [table]: ")
    var tableName = readln()
    if (tableName == "") tableName = "table"

    print(getConcatString(tableName, firstColumn, lastColumn, row))
}

fun getConcatString(tableName: String, firstColumn: Int, lastColumn: Int, row: Int): String {
    return "=CONCATENAR(" +
            getInsertPart(tableName, firstColumn, lastColumn, row) +
            " " +
            getValuesPart(firstColumn, lastColumn, row + 1) +
            ")"
}

fun getNumber(default: Int = 0): Int {
    return try {
        readln().toInt()
    } catch (e: NumberFormatException) {
        default
    }
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