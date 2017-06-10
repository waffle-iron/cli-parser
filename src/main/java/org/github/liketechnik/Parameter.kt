/*
 * Copyright (C) 2017  Florian Warzecha <flowa2000@gmail.com>
 *
 * This file is part of infoDisplay.
 *
 * infoDisplay is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * infoDisplay is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * infoDisplay uses TelegramBots Java API <https://github.com/rubenlagus/TelegramBots> by Ruben Bermudez.
 * TelegramBots API is licensed under GNU General Public License version 3 <https://www.gnu.org/licenses/gpl-3.0.de.html>.
 *
 * infoDisplay uses parts of the Apache Commons project <https://commons.apache.org/>.
 * Apache commons is licensed under the Apache License Version 2.0 <http://www.apache.org/licenses/>.
 *
 * infoDisplay uses vlcj library <http://capricasoftware.co.uk/#/projects/vlcj>.
 * vlcj is licensed under GNU General Public License version 3 <https://www.gnu.org/licenses/gpl-3.0.de.html>.
 *
 * Thanks to all the people who contributed to the projects that make this
 * program possible.
 */

package org.github.liketechnik

/**
 * This class represents a command line parameter.
 *
 * [name] and [shortName] are used to identify the passed arguments and match their values to the correct parameters.
 * [defaultValue] must have the same type as [type] is stating; it is used when the parameter is not specified on the cli.
 * [type] defines which (java/kotlin) data type the value of the parameter should have.
 * [id] identifies the parameter amongst others in a parser and must be unique
 *
 * @author Florian Warzecha
 * @version 1.0
 * @since 1.0-dev
 * @date 07 of June 2017
 * @see Parser
 * @see ArgumentTypes
 */
abstract class Parameter {
    /**
     * The long (default) version of the parameter on the cli (e. g. ```version```).
     */
    abstract val name: String
    /**
     * The short (abbreviated) version of the parameter on the cli (e. g. ```v```).
     */
    abstract val shortName: String
    /**
     * When overriding please choose an appropriate type for the default value, in most cases it must be the same as
     * specified by [type]. The value of this property is used when the parameter was not found on the cli by the parser
     * or it was unable to convert it to the [type] requested.
     */
    abstract val defaultValue: kotlin.Any
    /**
     * The data type of the [parameter's][Parameter] value.
     * @see ArgumentTypes
     */
    abstract val type: ArgumentTypes
    /**
     * A unique id for identifying the parameter amongst others.
     */
    abstract val id: String
}


/**
 * The possible data types a [parameter's][Parameter] value can have.
 * @author Florian Warzecha
 * @version 1.0
 * @date 07 of June 2017
 * @see Parameter
 * @see Parameter.type
 */
enum class ArgumentTypes() {
    STRING, INT
}