/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfourchampionship;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author MarcioNote
 */
public class DummyPlayer implements IPlayer {
    /*Não pode colocoar contantes nos vetores*/

    int nos;
    int adversario;
    int primeirajogada = 1;
    
    @Override
    public int getNextMove(int[][] gameBoard) {
        int tamLinha = gameBoard.length;
        int tamColuna = gameBoard[0].length;

        if(primeirajogada == 1)
        {
            nos = quemsoueu(gameBoard);
    
            if(nos == 1) adversario = -1;
            else adversario = 1;
            
            
            System.out.println("Nós: " + nos + "| Adv: " + adversario + "\n");
            primeirajogada = 0;
        }
        else
        {
            /*Defesa 1 - Percorre a matriz e verifica se tem uma 
             sequencia de 3 números adversários (De baixo para cima )*/
            
            /*Horizontal
             
             * Verificar se tem alguma linha vazia,
             * caso esteja nem precisa verificar o resto!
             */
            
            int retorno = 0;
            
            int sHorizontalAdv = 0;
            int linhaVazia = 1;

            for(int i = tamLinha -1; i >= 0; i--)
            {
                for(int j = tamColuna - 1; j >= 0; j--)
                {        
                    if(gameBoard[i][j] != 0){
                        linhaVazia = 0;
                    }
                    System.out.println("Count Horizontal: " + sHorizontalAdv);
                    // && (gameBoard[i][j - 1] == 0 || gameBoard[i][j + 3] == 0)
                    if(sHorizontalAdv >= 3)
                    {
                        
                        if(isLegalMove(gameBoard, i, j - 1)){
                            if(gameBoard[i][j-1] == 0)
                            {
                                retorno = j -1;
                                if(retorno == -1) retorno = 5;
                                return retorno;
                            }
                        }    
                        else if(isLegalMove(gameBoard, i, j+2) )
                        {
                            if(gameBoard[i][j + 2] == 0)
                            { 
                                System.out.println("HORIZONTAAAAAL!  2");
                                retorno = j + 2;
                                if(retorno == -1) 
                                {
                                    while (true) {
                                        int possiblePlay = new Random().nextInt(gameBoard[0].length);
                                        for (int k = 0; i < gameBoard.length; k++) {
                                            if(isLegalMove(gameBoard, k, possiblePlay)){
                                                return possiblePlay;
                                            }
                                        }  
                                    }
                                }
                            }
                        } 
                    }
                    
                    if(gameBoard[i][j] == adversario)
                        sHorizontalAdv++;
                    else if(gameBoard[i][j] == nos)
                        sHorizontalAdv = 0;
                }
                
                   //JOptionPane.showMessageDialog(null, "Horizontal: " + sHorizontalAdv + "|Nos" + nos);

                if(linhaVazia == 1)
                    break;
                
//                Verifica se a lihna está vazia
//                 ai não precisa ir até o final da matriz
                linhaVazia = 1;
                sHorizontalAdv = 0;
            }
//        
//            --------------------------------------------------------
             //Verifica a vertical   
            int sVerticalAdv = 0;
            
            for(int j = tamColuna -1; j >= 0; j--)
            {
                for(int i = tamLinha - 1; i >= 0; i--)
                {        
                    System.out.println("V[" + i + "][" + j + "] || ");
                    if(sVerticalAdv == 2 && gameBoard[i][j] == 0)
                    {
                        if(isLegalMove(gameBoard, i, j)){
                            return j;
                        }   
                    }
                    
                    if(gameBoard[i][j] == adversario)
                    {
                        sVerticalAdv++;
                    }else if(gameBoard[i][j] == nos){
                        sVerticalAdv = 0;
                    }
                }
                
//                Verifica se a lihna está vazia
//                 ai não precisa ir até o final da matriz

                sVerticalAdv = 0;
            }
        }
        /*Verificar alguns casos que ela está dando  -1 
         * Quando chega em cima ele buga, não sei o porque ?
         * Verificar só com a horizontal e ver o que acontece
         * ???*/
        //Caso não for nada disso!
        
        while (true) {
            int possiblePlay = new Random().nextInt(gameBoard[0].length);
            for (int i = 0; i < gameBoard.length; i++) {
                if(isLegalMove(gameBoard, i, possiblePlay)){
                    return possiblePlay;
                }
            }  
        }
        
    }

    public int quemsoueu(int [][] gB){
        /*Algoritmo verifica a primeira linha
         e verifica quem somos nós 1 ou -1
         */
        int tamColuna = gB[0].length;
        int tamlinha = gB.length;
        
        for (int i = tamlinha - 1; i < tamlinha; i++) {
            for (int j = 0; j < tamColuna; j++){
                if(gB[i][j] != 0) {
                    return -1;
                }
            }
        }
        
        return 1;
        
    }
    
    public boolean isLegalMove(int[][] gB, int row, int col) {
        //for legal move, must satisfy the following
        int ROW_SIZE = gB.length;
        int COL_SIZE = gB[0].length;
        if (row < ROW_SIZE && col < COL_SIZE) {
            if (row >= 0 && col >= 0) {
                if (gB[row][col] == 0) {
                    //needs to be placed at the bottom
                    if (row == ROW_SIZE - 1) {
                        return true;
                    }
                    //or needs a piece below it
                    if (gB[row + 1][col] != 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String getTeamName() {
        return "SlowDown Parsa";
    }

}
