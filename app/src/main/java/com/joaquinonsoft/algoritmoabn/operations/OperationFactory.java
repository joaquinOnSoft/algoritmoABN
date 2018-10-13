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

import com.joaquinonsoft.algoritmoabn.operations.imp.division.ABNDivisionByAFigure;
import com.joaquinonsoft.algoritmoabn.operations.imp.multiplication.ABNMultiplicationByAFigure;
import com.joaquinonsoft.algoritmoabn.operations.imp.subtraction.ABNSubtraction;
import com.joaquinonsoft.algoritmoabn.operations.imp.sum.ABNSum;

import java.util.Random;

public class OperationFactory {
    private static final OperationFactory instance = new OperationFactory();

    public static OperationFactory getInstance() {
        return instance;
    }

    private OperationFactory() {
    }

    public ABNOperarion getOperation(Operarion op){
        int operand1;
        int operand2;


        switch (op){
            case SUM:
                operand1 = getNumberInInterval(0,100);
                operand2 = getNumberInInterval(0,100);
                return new ABNSum(operand1, operand2);
            case SUBTRACTION:
                operand1 = getNumberInInterval(1,100);
                operand2 = getNumberInInterval(0,operand1 + 1);
                return new ABNSubtraction(operand1, operand2);
            case MULTIPLICATIONx1DIGIT:
                operand1 = getNumberInInterval(0,100);
                operand2 = getNumberInInterval(0,10);
                return new ABNMultiplicationByAFigure(operand1,operand2);
            case DIVISONx1DIGIT:
                operand1 = getNumberInInterval(10,100);
                operand2 = getNumberInInterval(1,10);
                return new ABNDivisionByAFigure(operand1, operand2);
            default:
                return null;
        }

    }

    private int getNumberInInterval(int minIncluded, int maxExcluded){
        Random r = new Random();
        return r.nextInt(maxExcluded - minIncluded) + minIncluded;
    }
}
