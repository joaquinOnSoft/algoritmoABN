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
 * Sumar Números de Dos Cifras con Algoritmos Abiertos Basados en Números
 *
 * La suma de dos números con algoritmos abiertos basados en números consiste en
 * ir pasando cantidades del número menor al mayor. La cantidad a pasar dependerá
 * del dominio que tenga cada alumno, ya que estamos hablando de una metodología
 * abierta que se adapta a cada nivel y alumno. La mejor manera de explicar cómo
 * sumar números de dos cifras con la metodología de algoritmos abiertos es poniendo un ejemplo.
 *
 * Si partimos del siguiente problema: Pedro tiene 56 euros y Antonio 28
 * ¿Cuántos euros tienen entre los dos? La operación podría resolverse de la siguiente manera:
 *
 * Partimos de una rejilla de 3 columnas: en la 1º columna iremos poniendo la cantidad que vamos
 * cogiendo, en la 3º columna lo que nos queda por pasar y en la 2º columna el resultado acumulado
 * de lo que hemos cogido con lo que tenemos.
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
    public int[][] getSteps() {
        int nSteps = 0;
        int steps[][] = new int[32][3];
        steps[0][0] = 0;
        steps[0][1] = operand1;
        steps[0][2] = operand2;

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
