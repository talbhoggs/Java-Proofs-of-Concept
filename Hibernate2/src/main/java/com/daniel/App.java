package com.daniel;

import com.daniel.service.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        MovieService ms = new MovieService();
        
        //ms.addMovie(1, "a director", "X Men Days of Future Past", "Super great movie I'll watch after I'll come back from Europe");
        ms.addMovieTx(11, "a director 2", "How to Train your Dragon", "Good movie I'll watch after I'll come back from Europe");
        
        
    }
}
