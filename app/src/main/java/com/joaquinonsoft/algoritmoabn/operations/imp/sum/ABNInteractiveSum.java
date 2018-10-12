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
package com.joaquinonsoft.algoritmoabn.operations.imp.sum;

/**
 * <strong>Add Numbers of Two Figures with Open Algorithms Based on Numbers</strong>
 *
 * <p>
 * The sum of two numbers with open algorithms based on numbers consists in
 * passing amounts from the lowest number to the highest number. The amount to
 * spend will depend on the domain that each student has, since we are talking
 * about an open methodology that adapts to each level and student. The best way
 * to explain how adding two-digit numbers with the methodology of open algorithms
 * is to set an example.
 * </p>
 * <p>
 * If we start with the following problem: Pedro has 56 euros and Antonio 28
 * How many euros do they have between them? The operation could be solved in
 * the following way:
 * </p>
 * <p>
 * We start from a grid of 3 columns: in the 1st column we will put
 * the amount we are taking, in the 3rd column what remains to be spent and in
 * the 2nd column the cumulative result of what we have taken with what we have.
 * </p>
 * <pre>
 *     | 56 | +28        | 56 | +28        | 56 | +28
 *  20 | 76 |   8     20 | 76 |   8     20 | 76 |   8
 *     |    |          4 | 80 |   4      4 | 80 |   4
 *     |    |            |    |          4 | 84 |   0
 *
 * </pre>
 * SEE: http://lapandilladelarejilla.es/operaciones/suma-abn/
 */
import com.joaquinonsoft.algoritmoabn.operations.AbstractABNOperation;

/**
 * Add Numbers of Two Figures with Open Algorithms Based on Numbers
 *  
 * The sum of two numbers with open algorithms based on numbers consists of
 * go passing amounts from the lowest number to the highest. The amount to spend will depend
 * of the domain that each student has, since we are talking about a methodology
 * Open that adapts to each level and student. The best way to explain how
 * adding two-digit numbers with the methodology of open algorithms is an example.
 *
 * If we start with the following problem: Pedro has 56 euros and Antonio 28
 * How many euros do you have between the two? The operation could be solved in the following way:
 *  
 * We start from a grid of 3 columns: in the 1st column we will put the amount that we are going
 * taking, in the 3rd column, what we have left to pass and in the 2nd column the accumulated result
 * of what we have taken with what we have.
 *
 * SEE: http://lapandilladelarejilla.es/operaciones/suma-abn/
 */
public class ABNInteractiveSum extends AbstractABNOperation {

    public static final int COLUMN_TAKE = 0;
    public static final int COLUMN_SUM = 1;
    public static final int COLUMN_REMAINS = 2;

    public ABNInteractiveSum(int operand1, int operand2) {
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
    public int[][] getSteps() {
        int nSteps = 0;
        int remains;
        int takes;

        do{
            takes = steps[nSteps][COLUMN_REMAINS] % 10;
            if(takes == 0){
                takes = steps[nSteps][COLUMN_REMAINS];
                remains = 0;
            }
            else {
                remains = (steps[nSteps][COLUMN_REMAINS] / 10) * 10;
            }

            nSteps++;

            steps[nSteps][COLUMN_TAKE] = takes;
            steps[nSteps][COLUMN_SUM] = steps[nSteps - 1][COLUMN_SUM] + takes;
            steps[nSteps][COLUMN_REMAINS] = remains;
        }while(steps[nSteps][COLUMN_REMAINS] != 0);

        return steps;
    }

    @Override
    public int getResult() {
        return operand1 + operand2;
    }

}
