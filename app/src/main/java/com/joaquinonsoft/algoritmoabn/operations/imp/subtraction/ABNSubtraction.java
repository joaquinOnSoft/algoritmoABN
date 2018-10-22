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
package com.joaquinonsoft.algoritmoabn.operations.imp.subtraction;

import com.joaquinonsoft.algoritmoabn.operations.AbstractABNOperation;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * The subtraction of two numbers with the methodology of Open Algorithms Based
 * on Numbers is to remove the same amount of the two numbers until one of them
 * remains at 0. As happens in the sum, we can adapt the amount to remove depending
 * on the level of the student. We will explain how to subtract two-digit numbers by
 * resorting, again, to an example.
 *
 * If we start with the following problem: Pedro has 76 euros and he has bought a
 * complete case that is worth 39 How many euros do you have left? The operation
 * could be solved in the following way.
 *
 * We start from a grid of 3 columns: in the 1st column we will put the amount we
 * are removing the two numbers and in the 2nd and 3rd columns the amount that
 * I have left after removing the 1st column.
 *
 *     | 76 | -39
 *  30 | 46 |   9
 *   6 | 40 |   3
 *   3 | 37 |   0
 *
 * Another Way to Solve the Subtraction of Two Figures with the Same Method
 *
 *     | 76 | -39
 *  30 | 46 |   9
 *   9 | 37 |   0
 *
 * SEE: http://lapandilladelarejilla.es/operaciones/resta-abn/
 **/
public class ABNSubtraction extends AbstractABNOperation {
    public static final int COLUMN_TAKE = 0;
    public static final int COLUMN_SUBTRACTION = 1;
    public static final int COLUMN_REMAINS = 2;

    public ABNSubtraction(int operand1, int operand2) {
        super(operand1, operand2);
    }

    @Override
    protected void initialize() {
        steps[0][0] = 0;
        steps[0][1] = operand1;
        steps[0][2] = operand2;
    }

    @Override
    protected int getNumRows() {
        return 32;
    }

    @Override
    public int getResult() {
        return operand1 - operand2;
    }

    @Override
    public int[][] getAutoCalculatedSteps() {
        int nSteps = 0;
        int remains;
        int takes;

        int[][] autoCalculatedsteps = new int[getNumRows()][NUM_COLUMNS];

        autoCalculatedsteps[0][0] = 0;
        autoCalculatedsteps[0][1] = operand1;
        autoCalculatedsteps[0][2] = operand2;

        do{
            takes = (autoCalculatedsteps[nSteps][COLUMN_REMAINS] / 10) * 10;
            if(takes == 0){
                takes = autoCalculatedsteps[nSteps][COLUMN_REMAINS];
                remains = 0;
            }
            else {
                remains = autoCalculatedsteps[nSteps][COLUMN_REMAINS] % 10;
            }

            nSteps++;

            autoCalculatedsteps[nSteps][COLUMN_TAKE] = takes;
            autoCalculatedsteps[nSteps][COLUMN_SUBTRACTION] = autoCalculatedsteps[nSteps - 1][COLUMN_SUBTRACTION] - takes;
            autoCalculatedsteps[nSteps][COLUMN_REMAINS] = remains;
        }while(autoCalculatedsteps[nSteps][COLUMN_REMAINS] != 0);

        return autoCalculatedsteps;

    }


    @Override
    public List<Integer> getValidValues() {
        List<Integer> values = new LinkedList<>();

        switch (currentCol){
            case COLUMN_TAKE:
                int remains = steps[currentRow-1][COLUMN_REMAINS];
                for(int i=0; i<= remains; ++i){
                    values.add(i);
                }
                break;
            case COLUMN_SUBTRACTION:
                values.add(steps[currentRow-1][COLUMN_SUBTRACTION] - steps[currentRow][COLUMN_TAKE]);
                break;
            case COLUMN_REMAINS:
                values.add(steps[currentRow-1][COLUMN_REMAINS] - steps[currentRow][COLUMN_TAKE]);
                break;
        }

        return values;
    }

    @Override
    public boolean isSolved() {
        for(int row=1; row<currentRow; row++){
            if(steps[row][COLUMN_TAKE] > steps[row - 1][COLUMN_REMAINS]){
                return false;
            }

            if(steps[row][COLUMN_SUBTRACTION] != (steps[row-1][COLUMN_SUBTRACTION] - steps[row][COLUMN_TAKE])){
                return false;
            }

            if(steps[row][COLUMN_REMAINS] != (steps[row-1][COLUMN_REMAINS] - steps[row][COLUMN_TAKE])){
                return false;
            }
        }

        if(steps[currentRow][COLUMN_SUBTRACTION] != (operand1 - operand2)){
            return false;
        }

        return true;
    }
}
