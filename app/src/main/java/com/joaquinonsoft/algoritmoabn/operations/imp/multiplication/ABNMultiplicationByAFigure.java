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
package com.joaquinonsoft.algoritmoabn.operations.imp.multiplication;

import com.joaquinonsoft.algoritmoabn.operations.AbstractABNOperation;
import com.joaquinonsoft.algoritmoabn.operations.Decomposition;

import java.util.LinkedList;
import java.util.List;

/**
 * In the ABN algorithm for the multiplication where the multiplier is of a
 * single figure we need three columns and as many rows as decompositions in
 * units have the number that we are going to multiply. In the example 238 x 8
 * would be as follows:
 *
 * <pre>
 *     238 | 8
 *     200 |    |
 *      30 |    |
 *       8 |    |
 * </pre>
 *
 * Where:
 *
 * - The first column can be called "Multiplying in units". It consists of a column
 * in which as many rows are written as decompositions of the number in units we can
 * make. Example in the number 238 is written a first row with 2 hundreds in format
 * units, that is to say 200, another row for the 3 tens, that is to say 30 and another
 * one for the 8 units. (See image above)
 *
 * - The second column can be called "partial products" and is the result of multiplying
 * the multiplier for each one of the decompositions that we have made by rows.
 *
 * - The last column can be called "Accumulated Product" and is the result of successively
 * adding the products of the central column. The first row will remain empty because there
 * are not yet two figures to add and the last one will reflect the total of the multiplication.
 *
 * Then the same operation 328 x 8 is shown in two different formats, in the first one
 * everything is specified, while in the second (more operative) the row with the name
 * of each column is omitted.
 *
 * <pre>
 *     238 |    8
 *     200 | 1600 |
 *      30 |  240 | 1840
 *       8 |   64 | 1904
 * </pre>
 *
 * <pre>
 *    5429 |     6
 *    5000 | 30000 |
 *     400 |  2400 | 32400
 *      20 |   120 | 32520
 *       9 |    54 | 32574
 * </pre>
 * See: http://lapandilladelarejilla.es/operaciones/multiplicacion-abn/
 */
public class ABNMultiplicationByAFigure extends AbstractABNOperation {

    private static final int COLUMN_MULTIPLYING_IN_UNITS = 0;
    private static final int COLUMN_PARTIAL_PRODUCTS = 1;
    private static final int COLUMN_ACCUMULATED_PRODUCT = 2;

    public ABNMultiplicationByAFigure(int operand1, int operand2){
        super(operand1,operand2);
    }

    @Override
    protected void initialize() {
        steps[0][COLUMN_MULTIPLYING_IN_UNITS] = operand1;
        steps[0][COLUMN_PARTIAL_PRODUCTS] = operand2;
    }

    @Override
    protected int getNumRows() {
        return 32;
    }

    @Override
    public int getResult() {
        return operand1 * operand2;
    }

    @Override
    public int[][] getAutoCalculatedSteps() {
        int row = 1;

        int[][] autoCalculatedSteps = new int[getNumRows()][NUM_COLUMNS];
        autoCalculatedSteps[0][COLUMN_MULTIPLYING_IN_UNITS] = operand1;
        autoCalculatedSteps[0][COLUMN_PARTIAL_PRODUCTS] = operand2;

        Decomposition decomp = new Decomposition(operand1);
        if(decomp.getThousands() > 0){
            autoCalculatedSteps[row][COLUMN_MULTIPLYING_IN_UNITS] = decomp.getUnitsInThousands();
            autoCalculatedSteps[row][COLUMN_PARTIAL_PRODUCTS] = autoCalculatedSteps[row][COLUMN_MULTIPLYING_IN_UNITS] * operand2;
            row++;
        }

        if(decomp.getHundreds() > 0){
            autoCalculatedSteps[row][COLUMN_MULTIPLYING_IN_UNITS] = decomp.getUnitsInHundreds();
            autoCalculatedSteps[row][COLUMN_PARTIAL_PRODUCTS] = autoCalculatedSteps[row][COLUMN_MULTIPLYING_IN_UNITS] * operand2;
            row++;
        }

        if(decomp.getTens() > 0){
            autoCalculatedSteps[row][COLUMN_MULTIPLYING_IN_UNITS] = decomp.getUnitsInTens();
            autoCalculatedSteps[row][COLUMN_PARTIAL_PRODUCTS] = autoCalculatedSteps[row][COLUMN_MULTIPLYING_IN_UNITS] * operand2;
            row++;
        }

        autoCalculatedSteps[row][COLUMN_MULTIPLYING_IN_UNITS] = decomp.getOnes();
        autoCalculatedSteps[row][COLUMN_PARTIAL_PRODUCTS] = autoCalculatedSteps[row][COLUMN_MULTIPLYING_IN_UNITS] * operand2;

        int accumulated = 0;
        for(int i=1; i<=row; i++){
            if(i == 1){
                accumulated = autoCalculatedSteps[i][COLUMN_PARTIAL_PRODUCTS];
            }
            else{
                accumulated += autoCalculatedSteps[i ][COLUMN_PARTIAL_PRODUCTS];

            }
            autoCalculatedSteps[i][COLUMN_ACCUMULATED_PRODUCT] = accumulated;
        }

        return autoCalculatedSteps;
    }


    @Override
    public List<Integer> getValidValues() {
        List<Integer> values = new LinkedList<>();

        switch (currentCol){
            case COLUMN_MULTIPLYING_IN_UNITS:
                int max = operand1;

                if(currentRow >= 2){
                     max = steps[currentRow - 1][COLUMN_MULTIPLYING_IN_UNITS] - steps[currentRow][COLUMN_MULTIPLYING_IN_UNITS];
                }

                for(int i=0; i<= max; ++i){
                    values.add(i);
                }
                break;
            case COLUMN_PARTIAL_PRODUCTS:
                values.add(steps[currentRow][COLUMN_MULTIPLYING_IN_UNITS] * operand2);
                break;
            case COLUMN_ACCUMULATED_PRODUCT:
                if(currentRow == 1){
                    values.add(steps[currentRow][COLUMN_MULTIPLYING_IN_UNITS] * operand2);
                }
                else{
                    int partialMult=0;
                    for(int row=1; row<=currentRow; row++){
                        partialMult += steps[row][COLUMN_PARTIAL_PRODUCTS];
                    }
                    values.add(partialMult);
                }
                break;
        }
        return values;
    }

    @Override
    public boolean isSolved() {
        for(int row=1; row<currentRow; row++){
            if(steps[row][COLUMN_MULTIPLYING_IN_UNITS] > steps[row - 1][COLUMN_MULTIPLYING_IN_UNITS]){
                return false;
            }

            if(steps[row][COLUMN_PARTIAL_PRODUCTS] != (steps[row][COLUMN_MULTIPLYING_IN_UNITS] * operand2)){
                return false;
            }

            if(steps[row][COLUMN_ACCUMULATED_PRODUCT] != (steps[row][COLUMN_PARTIAL_PRODUCTS] + steps[row - 1][COLUMN_ACCUMULATED_PRODUCT])){
                return false;
            }
        }

        if(steps[currentRow][COLUMN_ACCUMULATED_PRODUCT] != (operand1 * operand2)){
            return false;
        }

        return true;
    }
}
