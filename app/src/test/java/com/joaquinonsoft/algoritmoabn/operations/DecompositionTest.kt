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
package com.joaquinonsoft.algoritmoabn.operations

import org.junit.Test

import org.junit.Assert.*

class DecompositionTest {
    private var decomp: Decomposition = Decomposition(4356)

    @Test
    fun getThounsands() {
        assertEquals(4, decomp.thousands)
    }

    @Test
    fun getHundreds() {
        assertEquals(3, decomp.hundreds)
    }

    @Test
    fun getTens() {
        assertEquals(5, decomp.tens)
    }

    @Test
    fun getOnes() {
        assertEquals(6, decomp.ones)
    }

    @Test
    fun toStr() {
        assertEquals("4356 = 4000 + 300 + 50 + 6", decomp.toString())
    }

}