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
package com.joaquinonsoft.algoritmoabn.operations.imp.division;

import com.joaquinonsoft.algoritmoabn.operations.AbstractABNOperation;
import com.joaquinonsoft.algoritmoabn.operations.Decomposition;

import java.util.List;

/**
 * To solve the division using open algorithms, we start with a grid of 3 columns:
 *
 * <ul>
 *  <li>In the first column we will be putting the amount that we have left to distribute at each moment.</li>
 *  <li>In the second column we will write the amounts that we are taking to distribute.</li>
 *  <li>Finally, in the third column we will always indicate the amount distributed.</li>
 * </ul>
 *  
 *
 * As always we will illustrate what we have just explained by following an example problem.
 * The problem would say: Pedro had € 84 and he wants to distribute them in five piggy banks.
 * How much money should you put in each piggy bank?
 *
 * <pre>
 *     84 |  5 |          84 |  5 |           84 |  5 |
 *        |    |          84 | 50 | 10        84 | 50 | 10
 *        |    |             |    |        R = 4 |    | 16
 * </pre>
 *
 * <pre>
 *   3859 |   7 |        3859 |   7 | 500      3859 |   7 | 500
 *        |     |         359 | 350 | 50        359 | 350 | 50
 *        |     |             |     |             9 |   7 | 1
 *        |     |             |     |         R = 2 |     | 551
 * </pre>
 * SEE: http://lapandilladelarejilla.es/operaciones/division-abn/
 */
public class ABNDivisionByAFigure extends AbstractABNOperation {

    private static final int COLUMN_REMAINDER = 0;
    private static final int COLUMN_TAKING = 1;
    private static final int COLUMN_QUOTIENT = 2;


    public ABNDivisionByAFigure(int operand1, int operand2) {
        super(operand1, operand2);
    }

    @Override
    protected void initialize() {
        steps[0][COLUMN_REMAINDER] = operand1;
        steps[0][COLUMN_TAKING] = operand2;
        steps[0][COLUMN_QUOTIENT] = 0;
    }

    @Override
    protected int getNumRows() {
        return 4;
    }

    @Override
    public int getResult() {
        return operand1 / operand2;
    }

    public int getRemainder() {
        return operand1 % operand2;
    }

    @Override
    public int[][] getAutoCalculatedSteps() {
        int[][] autoCalculatedSteps = new int[getNumRows()][NUM_COLUMNS];

        autoCalculatedSteps[0][COLUMN_REMAINDER] = operand1;
        autoCalculatedSteps[0][COLUMN_TAKING] = operand2;
        autoCalculatedSteps[0][COLUMN_QUOTIENT] = 0;

        int row = 1;
        int remainder;
        int quotient;


        do{
            if(row == 1){
                remainder = operand1;
            }
            else{
                remainder = autoCalculatedSteps[row - 1][COLUMN_REMAINDER] % autoCalculatedSteps[row -1][COLUMN_TAKING];
            }

            autoCalculatedSteps[row][COLUMN_REMAINDER] = remainder;

            quotient = getEstimation(remainder, operand2);

            autoCalculatedSteps[row][COLUMN_TAKING] = quotient;
            autoCalculatedSteps[row][COLUMN_QUOTIENT] = quotient / operand2;

            row++;
        }while(remainder > operand2);

        quotient = 0;
        for(int i=0; i<row; i++){
            quotient += autoCalculatedSteps[i][COLUMN_QUOTIENT];
        }
        autoCalculatedSteps[row - 1][COLUMN_QUOTIENT] = quotient;

        return autoCalculatedSteps;
    }


    private int getEstimation(int pOperand1, int pOperand2){
        int estimation = Integer.MAX_VALUE;
        int start=0;
        int dec=0;
        Decomposition decomp = new Decomposition(pOperand1);


        if(decomp.getThousands() > 0){
            start = 1000;
            dec = 100;
        }
        else if(decomp.getHundreds() > 0){
            start = 100;
            dec = 10;
        }
        else if(decomp.getTens() > 0){
            start = 10;
            dec = 1;
        }
        else if(decomp.getOnes() > 0){
            start = pOperand2;
            dec = 1;
        }

        for(int i=start; pOperand1 < estimation ;i-=dec){
            estimation =pOperand2 * i;
        }

        return estimation;
    }

    @Override
    public List<Integer> getValidValues() {
        //TODO implement
        return null;
    }

    @Override
    public boolean isSolved() {
        //TODO implement
        return false;
    }
}
