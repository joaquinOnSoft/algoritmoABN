/*
 * (C) Copyright 2018 Joaquín On Software (http://joaquinonsoft.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Joaquín Garzón - initial implementation
 *
 */

package com.joaquinonsoft.algoritmoabn.operations

import org.junit.Test

import org.junit.Assert.*

abstract class ABNOperationTest {

    protected abstract var operation: ABNOperation


    abstract fun getExpectedPos(): Array<IntArray>

    abstract fun getExpectedValues(): IntArray

    @Test
    fun interactiveOperation(){
        var expectedPos = getExpectedPos()

        var expectedValues = getExpectedValues()

        var size:Int = expectedPos.size


        var currentPos:IntArray
        var validValues:List<Int>

        for (i in 0 until size){
            currentPos = operation.currentPos

            assertArrayEquals(expectedPos[i], currentPos)

            validValues = operation.validValues

            operation.currentValue = expectedValues[i]

            assertNotEquals(-1, validValues.indexOf(expectedValues[i]))

            if(operation.hasNext()){
                assertFalse(operation.isSolved)
                operation.next()
            }
        }

        assertTrue(operation.isSolved)
    }
}