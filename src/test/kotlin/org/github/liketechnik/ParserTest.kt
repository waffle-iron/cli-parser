package org.github.liketechnik

import org.junit.Test

import org.junit.Assert.*

/**
 * @author Florian Warzecha
 * *
 * @version 1.0
 * *
 * @date 07 of June 2017
 */
class ParserTest {
    @Test
    fun parseStringArguments() {
        val params: Array<Parameter> = arrayOf(StringTestParam())

        val longFormValue = "b=bac"
        val longFormArgs: Array<String> = arrayOf("--test=$longFormValue", "a", "--test2=c")
        val shortFormValue1: String = "a"
        val shortFormValue2: String = "b"
        val shortFormArgs: Array<String> = arrayOf<String>("-t", shortFormValue1, shortFormValue2, "--test3=hallo")
        assertEquals(Parser(longFormArgs, parameters = params).getStringArgumentValue(StringTestParam().id), longFormValue)
        assertEquals(Parser(shortFormArgs, parameters = params).getStringArgumentValue(StringTestParam().id),
                shortFormValue1 + " " + shortFormValue2)
        assertEquals(Parser(arrayOf(), parameters = params).getStringArgumentValue(StringTestParam().id),
                StringTestParam().defaultValue)
    }

    open class StringTestParam() : Parameter() {
        override val name: String = "test"
        override val shortName: String = "t"
        override val defaultValue: String = "default"
        override val id: String = name + defaultValue
        override val type: ArgumentTypes = ArgumentTypes.STRING
    }
}