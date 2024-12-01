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

fun main(args: Array<String>) {
    val firstColumn = 2
    val lastColumn = 55 //firstColumn + 26 - 1
    val row = 3
    val tableName = "asp_migra_period"

    print("=CONCATENAR(\"INSERT INTO $tableName (\";")
    for (i in firstColumn..lastColumn) {
        print(getColumn(i, true))
        print("\$")
        print(row)
        print(
            if (i != lastColumn)
                 ";\", \";"
            else
                ";\") VALUES ('\";"
        )
    }
    for (i in firstColumn..lastColumn) {
        print(getColumn(i, false))
        print(row+1)
        print(
            if (i != lastColumn)
                ";\"', '\";"
            else
                ";\"');\")"
        )
    }
    println()
}