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
package com.joaquinonsoft.algoritmoabn.operations.imp.multiplication

import org.junit.Test

import org.junit.Assert.*

class ABNMultiplicationTest {
    private var multiplication: ABNMultiplication = ABNMultiplication(238, 8)

    @Test
    fun getResult() {
        // Context of the app under test.
        assertEquals(1904, multiplication.result)
    }

    @Test
    fun getSteps() {
        val expectedResult = arrayOf(
                intArrayOf(238, 8, 0),
                intArrayOf(200, 1600, 1600),
                intArrayOf(30, 240, 1840),
                intArrayOf(8, 64, 1904)
        )

        val rows = expectedResult.size
        val columns = expectedResult[0].size

        val result = multiplication.steps

        for(i in 0 until rows){
            for(j in 0 until columns){
                assertEquals(expectedResult[i][j], result[i][j])
            }
        }
    }
}