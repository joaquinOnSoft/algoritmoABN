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
package com.joaquinonsoft.algoritmoabn.operations;

import java.util.List;

public interface ABNOperation {
    int getOperand1();

    int getOperand2();

    int setOperand1(int operand1);

    int setOperand2(int operand2);

    int getResult();

    /**
     * Provides the steps given by the user to resolve the operation
     * @return array with the steps given to resolve the operation
     **/
    int[][] getSteps();

    /**
     * Provide a valid solution for this operations calculated by the computer
     * @return array with the steps given to resolve the operation
     **/
    int[][] getAutoCalculatedSteps();

    int[] getCurrentPos();

    void setCurrentValue(int value);

    int getCurrentValue();

    List<Integer> getValidValues();

    boolean isSolved();

    boolean hasNext();

    void next();
}
