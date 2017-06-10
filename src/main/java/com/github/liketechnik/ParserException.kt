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
 * An exception for errors in the parsing process.
 *
 * @author Florian Warzecha
 * @version 1.0
 * @since 1.0-dev
 * @date 07 of June 2017
 * @see Parser
 */
class ParserException(reason: String) : Exception(reason) {
    /**
     * States that the parameters name was found in the arguments but did not match the prefix.
     * @param argument The part of the [Parser.arguments] that contains the [parameter].
     * @param parameter The name of the [Parameter] that was matched.
     * @see Parameter.name
     * @see Parser
     */
    constructor(argument: String, parameter: String) : this(
            "Argument '$argument' contains parameter '$parameter but does not have any matching prefix."
    )
}