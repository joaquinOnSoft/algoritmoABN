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

import android.os.Build;

import com.joaquinonsoft.algoritmoabn.AbstractABNInteractiveOperation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
public class ABNInteractiveSum extends AbstractABNInteractiveOperation {

    public static final int COLUMN_TAKE = 0;
    public static final int COLUMN_SUM = 1;
    public static final int COLUMN_REMAINS = 2;

    public ABNInteractiveSum(int operand1, int operand2) {
        super(operand1, operand2);
    }

    @Override
    protected void initialize() {
        steps[0][COLUMN_TAKE] = 0;
        steps[0][COLUMN_SUM] = operand1;
        steps[0][COLUMN_REMAINS] = operand2;

        currentRow = 1;
        currentCol = 0;
    }

    @Override
    protected int getNumRows() {
        return 32;
    }

    @Override
    public List<Integer> getValidValues() {
        List<Integer> values = new LinkedList<>();

        //TODO here
        return values;
    }

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public int[][] getSteps() {
        return steps;
    }

    @Override
    public int getResult() {
        return operand1 + operand2;
    }

}
