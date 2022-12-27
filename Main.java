import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Main{
    public static String[][] array = new String[10][10];// access anywhere
    public static String[][] gridVisible = new String[10][10]; //for visible
    //store coords for array dig()
    public static ArrayList<Integer> list = new ArrayList<Integer>();
    //store coords for gridVisible uncover() method
    public static ArrayList<Integer> list2 = new ArrayList<Integer>();
    // not used yet???
    public static int xposog;
    public static int yposog;
    public static int flags;


  public static void addFlag(int xpos,int ypos){
      flags--;
      if(gridVisible[xpos][ypos].equals("F")){
        gridVisible[xpos][ypos]="?";
      }
      if(gridVisible[xpos][ypos].equals("?")){
        gridVisible[xpos][ypos]="F";
        array[xpos][ypos]="F";
      }
    
    
  }


    public static boolean checkMines(int x, int y){
        return array[x][y].equals("M");
    }

  //creates model array in background
    public static void dig(int xpos, int ypos){
        
     
        // neighbors checking
      int count =0;
        for(int i = (xpos -1); i <= (xpos+1); i++){
            for(int j = (ypos-1); j <= (ypos+1); j++){
              int countEach = 0;
              
                
                if(i>-1 && j>-1 && i<10 && j<10){
                    if(checkMines(i, j)){
                        count++;
                        countEach++;
                        
                        }
                    else if(!list.contains(i*10+j)){
                        list.add(i*10 + j);
                        dig(i, j);
                        }
                    
                     
                    
                //System.out.println("count " + count + "count each " + countEach);
                if(count > 0) {
                  array[xpos][ypos] = "" + count;
                }
                
                
                }
                
               
                
            }


            
            //test list with points
        //for (int n : list ){
       //     System.out.println(n);
     //  }
        
        
        }
    }
    // grid visible is edited 
    public static void uncover(int xpos, int ypos){
      //origional values
      
      int count =0;
/*
      //see what array is at uncover() FOR TESTING
      System.out.println("array at uncover");
      System.out.println("     0  1  2  3  4  5  6  7  8  9");
        for(int i=0; i<10;i++){
            System.out.print(i + " | ");
            for(int j=0; j<10;j++){
                System.out.print("[" +array[i][j] + "]");
            }
            System.out.println();
        }


*/
      //code
      
        for(int i = (xpos -1); i <= (xpos+1); i++){
            for(int j = (ypos-1); j <= (ypos+1); j++){
              // sideway motion
              if(!(i==xpos-1 && j==ypos-1) && !(i==xpos+1 && j==ypos+1)&&!(i==xpos-1 && j==ypos+1)&&!(i==xpos+1 && j==ypos-1) ){
               if(i>-1 && j>-1 && i<10 && j<10){
              //open area clears
                if(array[xposog][yposog].equals("?")){
                  if(array[i][j].equals("?")){
                    gridVisible[i][j]= "_";
                    if(!list2.contains(i*10+j)){
                        list2.add(i*10 + j);
                        uncover(i, j);
                        }
                    }
                }
                //hints uncover
                /*
                if(gridVisible[i][j].equals("_")){
                    for(int x = (xpos -1); x <= (xpos+1); x++){
                      for(int y = (ypos-1); y <= (ypos+1); y++){
                        // sideway motion
                        if(!(x==xpos-1 && y==ypos-1) && !(x==xpos+1 && y==ypos+1)&&!(x==xpos-1 && y==ypos+1)&&!(x==xpos+1 && y==ypos-1) ){
                            if(x>-1 && y>-1 && x<10 && y<10){
                              gridVisible[x][y] = array[x][y];
                             
                             }
                }
                      }
                    }
                     gridVisible[i][j]="_";
                }
        */
                
                // if land on a hint
                if (array[xposog][yposog].equals("1")||array[xposog][yposog].equals("2")|| array[xposog][yposog].equals("3")||array[xposog][yposog].equals("4")) {
                  gridVisible[xposog][yposog]=array[xposog][yposog];
                  //list2.add(xposog*10+yposog);

                  
                }
                //uncover hinys

                if (array[i][j].equals("1")||array[i][j].equals("2")|| array[i][j].equals("3")||array[i][j].equals("4")) {
                  gridVisible[i][j]=array[i][j];
                  //test and see if needed to add list2

                  
                }


               }
            }
        }
        }

        
               
        for(int i = (xpos -1); i <= (xpos+1); i++){
            for(int j = (ypos-1); j <= (ypos+1); j++){
               if(i>-1 && j>-1 && i<10 && j<10){

                if(array[i][j].equals("M")) {
                       gridVisible[i][j]="?";
                }
               }
            }
        }          
              

      }


    public static void main(String[] args){
      System.out.println("WELCOME TO MINESWEEPER!!!");
      System.out.println("You will be asked to enter coordinates! \n have fun it took me very long to make this :o \n press any key to start!!" );
      Scanner in = new Scanner(System.in);
      String start = in.nextLine();

        //set flags 
        flags = 10;
        //make default board
        for(int i=0; i<10;i++){
            for(int j=0; j<10;j++){
                array[i][j]= "?";
                gridVisible[i][j]="?";
            }
        }
// insert mines
        for(int i = 0; i<10;i++){
            int x = (int) (Math.random()*10);
            int y = (int) (Math.random()*10);
            array[x][y]= "M";
        }

// print
    //20 turns???
     for(int temp = 0; temp <20; temp++){
       //for testing array 
       /*
        System.out.println("     0  1  2  3  4  5  6  7  8  9");
        for(int i=0; i<10;i++){
            System.out.print(i + " | ");
            for(int j=0; j<10;j++){
                System.out.print("[" +array[i][j] + "]");
            }
            System.out.println();
        }
        */

        //VISIBLE 
        System.out.println("     0  1  2  3  4  5  6  7  8  9");
        for(int i=0; i<10;i++){
            System.out.print(i + " | ");
            for(int j=0; j<10;j++){
                System.out.print("[" +gridVisible[i][j] + "]");
            }
            System.out.println();
        }

        //print

        // user input coordinates

        
            // googled inputing coordinates and converting to int
            Scanner input = new Scanner(System.in);
            System.out.println("" + flags + " flags left \n" + "Enter a coordinate: ");

            String coordinate = input.nextLine();
            String[] parts = coordinate.split(",");

            // if input is (x, y)
            // then parts[0] is "(x"
            // and parts[1] is " y)"

            String xin = parts[0];
            String yin = parts[1];

            System.out.println("x: " + xin + "\ny: " + yin);
            int xpos=Integer.parseInt(xin);
            int ypos=Integer.parseInt(yin);

            // if they want to flag ot dig
            System.out.println("Enter 'dig' to uncover and 'f' to flag area: ");
            Scanner input2 = new Scanner(System.in);
            String command = input2.nextLine();

            if(command.equals("f")){
              if(flags>0){
              addFlag(xpos,ypos);

            }
            }

            if(command.equals("dig")){

            

            xposog =xpos;
            yposog = ypos;

            if(checkMines(xpos,ypos)){
              
              System.out.println("     0  1  2  3  4  5  6  7  8  9");
              for(int i=0; i<10;i++){
                System.out.print(i + " | ");
                for(int j=0; j<10;j++){
                System.out.print("[" +array[i][j] + "]");
              }
            System.out.println();
               }
               System.out.println("GAME OVER!!!");
              System.exit(0);
              }

            dig(xpos,ypos);
            uncover(xpos,ypos);
            }

        }
        int win =0;
        for(int i=0;i<10;i++){
          for(int j=0;j<10;j++){
            if(gridVisible[i][j].equals("?")){
              win++;
            }
          }
        }

        if(win==0){
          System.out.println("you won congrats!!!");
        }


    }
}
