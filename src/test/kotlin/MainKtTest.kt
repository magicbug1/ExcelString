import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream

class MainKtTest {

    fun provideInput(data: String) {
        val testIn = ByteArrayInputStream(data.toByteArray())
        System.setIn(testIn)
    }
    @Test
    fun stringToNumber_returnsDefaultValue_onEmptyString() {
        provideInput(" ")
        assertEquals(5, getNumber(5))
    }
    @Test
    fun stringToNumber_returnsDefaultValue_onBadInput() {
        provideInput("two")
        assertEquals(4, getNumber(4))
    }
    @Test
    fun stringToNumber_returnsCorrectValue_onNumberString() {
        provideInput("13")
        assertEquals(13, getNumber(2))
    }

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