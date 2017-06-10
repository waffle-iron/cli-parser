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



/**
 * This class parses the passed arguments (from the cli) for the specified [parameters] and returns them on request.
 *
 * The values are internally stored and can be retrieved via their [id][Parameter.id] with the correct methods for the
 * value's data type (e. g. [getStringArgumentValue] for [Strings][String]).
 *
 * @author Florian Warzecha
 * @version 1.0
 * @since 1.0-dev
 * @date 07 of June 2017
 * @param arguments The arguments to parse (usually from cli).
 * @param argumentPrefix The prefix for long (default) argument forms (for example '--file=test.txt').
 * @param argumentSecondaryPrefix The prefix for short argument forms (for example '-f test.txt').
 * @param argumentValueSeparator The separator between argument and value for the long form.
 * @param parameters The [parameters][Parameter] to parse from the arguments.
 * @see Parameter
 */
class Parser @JvmOverloads constructor(val arguments: Array<String>, val argumentPrefix: String = "--",
                                       val argumentValueSeparator: String = "=", val argumentSecondaryPrefix: String = "-",
                                       val parameters: Array<Parameter>) {

    private val strValues: MutableMap<String, String> = mutableMapOf<String, String>()

    /**
     * Parses the passed arguments for the values for the specified parameters.
     */
    init{
        for (parameter in parameters) {
                if (parameter.type == ArgumentTypes.STRING) {
                    try {
                        strValues.putIfAbsent(parameter.id, parseStringArgument(parameter))
                    } catch (e: ParserException) {
                        strValues.putIfAbsent(parameter.id, parameter.defaultValue as String)
                    }
                } else if (parameter.type == ArgumentTypes.INT) {
                    TODO("No parse method for integers available yet.")
                }
        }
    }

    /**
     * Returns the parsed value for the specified parameter.
     * @param id The id of the parameter whose value is returned.
     * @return The value of the parameter.
     */
    fun getStringArgumentValue(id: String): String {
        val value: String? = strValues[id]
        if (value is String) {
            return value
        } else {
            return getParameterById(id).defaultValue as String
        }
    }

    /**
     * Parses the [cli arguments][arguments] for the value of the specified [parameter].
     *
     * If it finds the long form of the parameter, the value of this is returned. If that is not
     * found, the short form is taken for search and the value is returned. If both long and short form are not found,
     * the [default value][Parameter.defaultValue] of the parameter is returned.
     * @see Parameter
     */
    @Throws(ParserException::class)
    private fun parseStringArgument(parameter: Parameter): String {
        if (parameter.type != ArgumentTypes.STRING) {
            throw IllegalArgumentException("This method is ONLY for parsing strings. Use different method for parsing other" +
                    "data types.")
        }
        val value: String

        for (i in this.arguments.indices) {
            val argument: String = this.arguments[i]
            if (argument.startsWith(argumentPrefix) &&
                    argument.substringAfter(argumentPrefix).substringBefore(argumentValueSeparator) == parameter.name) {
                value = argument.split(argumentValueSeparator, limit = 2)[1]
            } else if (argument.startsWith(argumentSecondaryPrefix) && argument.endsWith(parameter.shortName)) {
                val tmpVal: StringBuilder = StringBuilder()
                this.arguments.copyOfRange(i + 1, this.arguments.lastIndex + 1)
                        .takeWhile { !(it.startsWith(argumentPrefix) || it.startsWith(argumentSecondaryPrefix)) }
                        .forEach { tmpVal.append(it)
                                    tmpVal.append(" ")}
                value = tmpVal.removeRange(tmpVal.lastIndex, tmpVal.lastIndex + 1).toString()
            } else {
                throw ParserException(argument, parameter.name)
            }
            return value
        }
        return parameter.defaultValue as String
    }

    /**
     * Returns the parameter for the passed id.
     * @param id The id of the parameter that is returned.
     * @return The parameter with the specified [id].
     */
    @Throws(IllegalArgumentException::class)
    private fun getParameterById(id: String): Parameter {
        this.parameters
                .filter { it.id == id }
                .forEach { return it }
        throw IllegalArgumentException("No matching parameter found!")
    }
}
