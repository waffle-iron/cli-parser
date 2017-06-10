/*
 * Copyright (C) 2017  Florian Warzecha <flowa2000@gmail.com>
 *
 * This file is part of cli-parser.
 *
 * cli-parser is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * cli-parser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.liketechnik

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