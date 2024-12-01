import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MainKtTest {

    @Test
    fun getColumn_returnsColumnLetter() {
        assertEquals("B", getColumn(2))
    }

    @Test
    fun getColumn_returnsTwoColumnLetters() {
        assertEquals("AA", getColumn(27))
    }

    @Test
    fun getValuesPart_returnValuesString() {
        assertEquals("VALUES ('\";A3;\"', '\";B3;\"');\"", getValuesPart(1, 2, 3))
    }

    @Test
    fun getInsertPart_returnsInsetrtString() {
        assertEquals("\"INSERT INTO xxx (\";\$A\$2;\", \";\$B\$2;\")", getInsertPart("xxx", 1, 2, 2))
    }

    @Test
    fun getConcatString_returnsConcatString() {
        assertEquals("=CONCATENAR(\"INSERT INTO STD_HR_PERIOD (\";\$B\$2;\") VALUES ('\";B3;\"');\")", getConcatString("STD_HR_PERIOD", 2, 2, 2))
    }
    @Test
    fun getConcatString_returnsConcatString2() {
        assertEquals("=CONCATENAR(\"INSERT INTO STD_HR_PERIOD (\";\$B\$2;\", \";\$C\$2;\") VALUES ('\";B3;\"', '\";C3;\"');\")", getConcatString("STD_HR_PERIOD", 2, 3, 2))
    }
}