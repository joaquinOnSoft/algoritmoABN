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
 *   Joaquín Garzón - initial implementation
 */
package com.joaquinonsoft.algoritmoabn.operations.imp.sum

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ABNSumTest {

    private var sum:ABNSum = ABNSum(54, 27)

    @Test
    fun getResult() {
        // Context of the app under test.
        assertEquals(81, sum.result)
    }

    @Test
    fun manualAddition() {
        var expectedPos = arrayOf(
                intArrayOf(1, 0), intArrayOf(1, 1), intArrayOf(1, 2),
                intArrayOf(2, 0), intArrayOf(2, 1), intArrayOf(2, 2)
        )

        var expectedValues = intArrayOf(
                 7, 61, 20,
                20, 81, 0
        )

        var size:Int = expectedPos.size


        var currentPos:IntArray
        var validValues:List<Int>

        for (i in 0 until size){
            currentPos = sum.currentPos

            assertArrayEquals(expectedPos[i], currentPos)

            validValues = sum.validValues

            sum.currentValue = expectedValues[i]

            assertNotEquals(-1, validValues.indexOf(expectedValues[i]))
            assertTrue(sum.hasNext())

            sum.next()
        }
    }

    @Test
    fun getAutoCalculatedSteps() {
        val expectedResult = arrayOf(
                intArrayOf(0, 54, 27),
                intArrayOf(7, 61, 20),
                intArrayOf(20, 81, 0)
        )

        val rows = expectedResult.size
        val columns = expectedResult[0].size

        val result = sum.autoCalculatedSteps

        for(i in 0 until rows){
            for(j in 0 until columns){
                assertEquals(expectedResult[i][j], result[i][j])
            }
        }
    }


    @Test
    fun toStr() {
        //val str = "[\n" +
        //        "{0,54,27},\n" +
        //        "{7,61,20},\n" +
        //        "{20,81,0}\n" +
        //        "]"

        val str = "[\n" +
                "{0,54,27},\n" +
                "{0,0,0}\n" +
                "]"


        assertEquals(str, sum.toString())
    }
}
