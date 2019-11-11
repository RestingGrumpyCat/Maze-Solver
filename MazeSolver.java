import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class represents an algorithm to solve a simple hexagon maze
 * @author Shiyu Wang from Computer Science 1027
 */


public class MazeSolver {
	
	/**
	 * This main method shows the maze
	 * @param args[] is the maze file
	 */

      public static void main (String[] args) {
    	  
    	   /* try block to test the algorithm*/
    	  try{
              
       	   /* if statement to make sure command line argument exists */

    		  if(args.length<1){
throw new IllegalArgumentException("No Maze Provided\n");
              }
             
    		  
                 /* create integer stepCounter to keep track of the step*/             
                int stepCounter = 0;
                 /* create integer hexagonOnStack to keep track of the number of Hexagon on the stack*/             
                int hexagonOnStack = 0;
                 /* create a Hexagon type object current*/
                Hexagon current;
                 
                 /*read the file from the command line argument*/                  
                String mazetxt = args[0];
                                
                 /* create a new maze object*/
                Maze maze = new Maze(mazetxt);
				
				 /*reference to the start hexagon tile of the maze*/                 
				Hexagon theStart = maze.getStart();
				 
                 /* create an arraystack called mazeStack*/
				ArrayStack<Hexagon> mazeStack = new ArrayStack<Hexagon>();
				 
				
                 /* push the start hexagon tile to the stack*/		
                mazeStack.push(theStart);
				
                /* increment the number of hexagon on stack by 1*/
                hexagonOnStack ++;
		
				
                 /* set a boolean variable to stop the loop when end is found*/			
				boolean done = false;
				
                 /* execute while loop until find the end and the maze is empty*/
				while(!(done) && ! mazeStack.isEmpty()){
				/* set the current as the hexagon that is popped*/
				 current = mazeStack.pop();
				/* set the current hexagon as processed*/
				 current.setProcessed();			 
				 /* increment steps by 1 and decrement number of hexagon by 1*/
                 hexagonOnStack --; 
				 stepCounter ++;
			
	
				    /*check is current is the end, if it is the end, 
				 	 * print out we have found the maze, stepCounter and hexagonOnStack*/				 
				 if(current.isEnd()){					 
					 System.out.println("We found the end of this maze successfully!\n");
					 System.out.println("We have taken " + stepCounter + " steps.\n");
					 System.out.println("There are still "  + hexagonOnStack + " hexagon on the stack.\n");
				 	 /*use boolean to stop the loop*/
					 done = true;
				 }
                  
                  /* execute for loop to push neighbors of the current to the stack
                   *  if the neighbor exists, is unvisited and not a wall*/
				 
				 else{	for (int i = 0; i <= 5; i++){
					 		if( current.getNeighbour(i) != null)
					 			if( ! current.getNeighbour(i).isWall() & current.getNeighbour(i).isUnvisited()) {
						
					 				mazeStack.push(current.getNeighbour(i));
					 				hexagonOnStack ++;
					 				if(! current.isStart()) {
					 				/*if current tile is not the Start tile, set it to be type pushed*/
					 				current.setPushed();
					 				}
						 
						 }
				 		}
				 /*update the maze with repaint function*/
					 maze.repaint();
					
				 }	 

				 }	

    	  }
    	  
    	  
    	  /* catch blocks to catch and handle possible exceptions*/
    	 
    	  /*catch and handle possible exceptions when the command 
    	   * line argument does not exist*/ 	  
    	  catch (IllegalArgumentException o){
    			System.out.println("No input information" + o.getMessage());
    		}
    	  
    	  /*catch and handle possible exceptions when maze file is missing*/
    	  catch (FileNotFoundException w){
				System.out.println("File Not Found\n" + w.getMessage());
			}
    	  
    	  /*catch and handle possible exceptions when something is wrong 
    	   * with the input or output information*/
			catch (IOException k){				
				System.out.println("Fail To Read File\n" + k.getMessage());
			}
    	  /*catch and handle possible exceptions when Encountered A Character The Maze Cannot Recognize*/
    	    catch (UnknownMazeCharacterException e ) {
    	    System.out.println("Encountered A Character The Maze Cannot Recognize\n" + e.getMessage());
			}
    	  
    	  /*catch and handle possible exceptions when try t peek or pop item from an empty stack*/
    	    catch (EmptyCollectionException p){
    			
    		System.out.println("You Try To Pop Or Peek From An Empty Stack\n" + p.getMessage());
    		}
    	  
    	  /*catch and handle possible exceptions when reference an index of the neighbor out of range*/
    	    catch (InvalidNeighbourIndexException l){
			
			System.out.println("You Try To Access To An Invalid Index\n" + l.getMessage());

		}
               
            }
      }
                  