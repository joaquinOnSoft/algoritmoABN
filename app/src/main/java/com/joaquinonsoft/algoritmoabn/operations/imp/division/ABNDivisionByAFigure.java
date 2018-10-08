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

/**
 * To solve the division using open algorithms, we start with a grid of 3 columns:
 *
 *  - In the first column we will be putting the amount that we have left to distribute at each moment.
 *  - In the second column we will write the amounts that we are taking to distribute.
 *  - Finally, in the third column we will always indicate the amount distributed.
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
    public int getResult() {
        return operand1 / operand2;
    }

    public int getRemainder() {
        return operand1 % operand2;
    }

    @Override
    public int[][] getSteps() {
        int steps[][] = new int[4][3];
        int row = 0;
        int remainder;
        int quotient;

        steps[row][COLUMN_REMAINDER] = operand1;
        steps[row][COLUMN_TAKING] = operand2;
        steps[row][COLUMN_QUOTIENT] = 0;
        row++;


        do{
            if(row == 1){
                remainder = operand1;
            }
            else{
                remainder = steps[row - 1][COLUMN_REMAINDER] % steps[row -1][COLUMN_TAKING];
            }

            steps[row][COLUMN_REMAINDER] = remainder;

            quotient = getEstimation(remainder, operand2);

            steps[row][COLUMN_TAKING] = quotient;
            steps[row][COLUMN_QUOTIENT] = quotient / operand2;

            row++;
        }while(remainder > operand2);

        quotient = 0;
        for(int i=0; i<row; i++){
            quotient += steps[i][COLUMN_QUOTIENT];
        }
        steps[row - 1][COLUMN_QUOTIENT] = quotient;

        return steps;
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
}
