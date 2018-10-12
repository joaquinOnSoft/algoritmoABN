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

/**
 *
 * Restar Números de Dos Cifras con Algoritmos Abiertos Basados en Números
 *
 *
 * La resta de dos números con la metodología de Algoritmos Abiertos Basados en
 * Números consiste en ir quitando la misma cantidad de los dos números hasta que uno
 * de ellos se quede en 0. Al igual que nos pasa en el suma, podemos adaptar la cantidad a
 * quitar en función del nivel del alumno. Vamos a explicar cómo restar números de dos
 * cifras recurriendo, de nuevo, a un ejemplo.
 *
 * Si partimos del siguiente problema: Pedro tiene 76 euros y se ha comprado un estuche
 * completo que vale 39 ¿Cuántos euros le quedan?, la operación podría resolverse de la
 * siguiente manera.
 *
 * Partimos de una rejilla de 3 columnas: en la 1º columna iremos poniendo la cantidad
 * que vamos quitando a los dos números y en la 2º y 3º columna la cantidad que me queda
 * tras quitarle lo de la 1º columna.
 *
 *     | 76 | -39
 *  30 | 46 |   9
 *   6 | 40 |   3
 *   3 | 37 |   0
 *
 * Otra Manera de Resolver la Resta de Dos Cifras con el Mismo Método
 *
 *     | 76 | -39
 *  30 | 46 |   9
 *   9 | 37 |   0
 *
 * SEE: http://lapandilladelarejilla.es/operaciones/resta-abn/
 **/
public class ABNSubtraction extends AbstractABNOperation {
    public static final int COLUMN_TAKE = 0;
    public static final int COLUMN_SUM = 1;
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
    public int[][] getSteps() {
        int nSteps = 0;
        int remains;
        int takes;

        do{
            takes = (steps[nSteps][COLUMN_REMAINS] / 10) * 10;
            if(takes == 0){
                takes = steps[nSteps][COLUMN_REMAINS];
                remains = 0;
            }
            else {
                remains = steps[nSteps][COLUMN_REMAINS] % 10;
            }

            nSteps++;

            steps[nSteps][COLUMN_TAKE] = takes;
            steps[nSteps][COLUMN_SUM] = steps[nSteps - 1][COLUMN_SUM] - takes;
            steps[nSteps][COLUMN_REMAINS] = remains;
        }while(steps[nSteps][COLUMN_REMAINS] != 0);

        return steps;

    }
}
