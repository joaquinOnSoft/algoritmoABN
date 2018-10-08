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
package com.joaquinonsoft.algoritmoabn.operations.imp.division

import org.junit.Assert
import org.junit.Test

class ABNDivisionByAFigureTest {
    private var division: ABNDivisionByAFigure = ABNDivisionByAFigure(84, 5)

    @Test
    fun getResult() {
        // Context of the app under test.
        Assert.assertEquals(16, division.result)
    }

    @Test
    fun getRemainder() {
        // Context of the app under test.
        Assert.assertEquals(4, division.remainder)
    }

    @Test
    fun getSteps() {
        val expectedResult = arrayOf(
                intArrayOf(84, 5, 0),
                intArrayOf(84, 50, 10),
                intArrayOf(34, 30, 6),
                intArrayOf(4, 0, 16)
        )

        val rows = expectedResult.size
        val columns = expectedResult[0].size

        val result = division.steps

        for(i in 0 until rows){
            for(j in 0 until columns){
                Assert.assertEquals(expectedResult[i][j], result[i][j])
            }
        }
    }
}