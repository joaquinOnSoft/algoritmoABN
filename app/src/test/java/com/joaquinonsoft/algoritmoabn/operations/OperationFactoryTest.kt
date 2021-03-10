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

class OperationFactoryTest {

    var  factory: OperationFactory = OperationFactory.getInstance()

    @Test
    fun getOperation(){
        var sum: ABNOperation = factory.getOperation(Operation.SUM)
        assertNotNull(sum)
        assertEquals(sum.operand1 + sum.operand2, sum.result)

        var subtraction: ABNOperation = factory.getOperation(Operation.SUBTRACTION)
        assertNotNull(subtraction)
        assertEquals(subtraction.operand1 - subtraction.operand2, subtraction.result)

        var multiplication: ABNOperation = factory.getOperation(Operation.MULTIPLICATIONx1DIGIT)
        assertNotNull(multiplication)
        assertEquals(multiplication.operand1 * multiplication.operand2, multiplication.result)

        var division: ABNOperation = factory.getOperation(Operation.DIVISIONx1DIGIT)
        assertNotNull(division)
        assertEquals(division.operand1 / division.operand2, division.result)
    }
}